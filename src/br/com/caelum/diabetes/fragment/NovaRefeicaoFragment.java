package br.com.caelum.diabetes.fragment;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import br.com.caelum.diabetes.R;
import br.com.caelum.diabetes.calculos.CalculaInsulina;
import br.com.caelum.diabetes.dao.DbHelper;
import br.com.caelum.diabetes.dao.PacienteDao;
import br.com.caelum.diabetes.dao.RefeicaoDao;
import br.com.caelum.diabetes.extras.TipoRefeicao;
import br.com.caelum.diabetes.model.AlimentoVirtual;
import br.com.caelum.diabetes.model.Paciente;
import br.com.caelum.diabetes.model.Refeicao;

public class NovaRefeicaoFragment extends Fragment{
	private Refeicao refeicao;
	private Paciente paciente;
	private View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.nova_refeicao, null);
		
		Bundle bundle = this.getArguments();
	
		TipoRefeicao tipoRefeicao = TipoRefeicao.fromString((String) bundle.get("tipo_refeicao"));
		Refeicao refeicaoBundle = (Refeicao) bundle.get("refeicao");
		
		if (tipoRefeicao != null) {
			refeicao = new Refeicao(tipoRefeicao);
			bundle.remove("tipo_refeicao");
		}
		
		if(refeicaoBundle != null) {
			this.refeicao = refeicaoBundle;
			bundle.remove("refeicao");
		}
		
		DbHelper helper = new DbHelper(getActivity());
		
		PacienteDao pacienteDao = new PacienteDao(helper);
		this.paciente = pacienteDao.getPaciente();
		
		helper.close();
		
		ListView lista = (ListView) view.findViewById(R.id.lista_alimentos);
		
		List<AlimentoVirtual> alimentos = refeicao.getAlimentos();
		ArrayAdapter<AlimentoVirtual> adapter = new ArrayAdapter<AlimentoVirtual>(getActivity(), android.R.layout.simple_list_item_1, alimentos);
		lista.setAdapter(adapter);
		
		EditText totalCHO = (EditText) view.findViewById(R.id.totalCHO);
		totalCHO.setText(String.valueOf(refeicao.getTotalCHO()));
		
		double valorInsulina = new CalculaInsulina(refeicao, paciente).getTotalInsulina();
		
		EditText totalInsulina = (EditText) view.findViewById(R.id.totalInsulina);
		totalInsulina.setText(String.valueOf(valorInsulina));
		
		Button novoAlimento = (Button) view.findViewById(R.id.novo_alimento);
		novoAlimento.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Bundle bundle = new Bundle();
				bundle.putSerializable("refeicao", refeicao);
				
				Fragment fragment = new AdicionaAlimentoFragment();
				fragment.setArguments(bundle);
				
				FragmentTransaction transaction = getFragmentManager().beginTransaction();
				transaction.replace(R.id.main_view, fragment);
				transaction.commit();
			}
		});
		
		Button salvar = (Button) view.findViewById(R.id.salvar_refeicao);
		salvar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				DbHelper helper = new DbHelper(getActivity());
				
				RefeicaoDao refeicaoDao = new RefeicaoDao(helper);
				refeicao.setId(refeicaoDao.salva(refeicao));
				
				helper.close();
				
				FragmentTransaction transaction = getFragmentManager().beginTransaction();
				transaction.replace(R.id.main_view, new ListaRefeicaoFragment());
				transaction.commit();
			}
		});
		
		return view;
	}
}
package br.com.caelum.diabetes.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import br.com.caelum.diabetes.R;
import br.com.caelum.diabetes.dao.AlimentoFisicoDao;
import br.com.caelum.diabetes.dao.DbHelper;
import br.com.caelum.diabetes.model.AlimentoFisico;

public class NovoAlimentoDiferenteFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.novo_alimento_diferente, null);
		
		final EditText nomeAlimento = (EditText) view.findViewById(R.id.nome_alimento);
		final EditText unidadeMedida = (EditText) view.findViewById(R.id.unidade_novo_alimento);
		final EditText carboidrato = (EditText) view.findViewById(R.id.carboidrato_novo_alimento);
		Button salvarAlimento = (Button) view.findViewById(R.id.salvar_alimento);
		
		salvarAlimento.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				AlimentoFisico alimentoFisico = new AlimentoFisico(nomeAlimento.getText().toString(), Double.parseDouble(carboidrato.getText().toString()), 
						unidadeMedida.getText().toString());
				DbHelper helper = new DbHelper(getActivity());
				AlimentoFisicoDao dao = new AlimentoFisicoDao(helper);
				dao.salva(alimentoFisico);
				helper.close();
				
				FragmentTransaction transaction = getFragmentManager().beginTransaction();
				transaction.replace(R.id.main_view, new DashboardCalculadoraFragment());
				transaction.commit();
			}
		});
		
		return view;
	}
}

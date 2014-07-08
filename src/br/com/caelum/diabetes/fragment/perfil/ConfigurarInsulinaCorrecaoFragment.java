package br.com.caelum.diabetes.fragment.perfil;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import br.com.caelum.diabetes.R;
import br.com.caelum.diabetes.dao.DadosMedicosDao;
import br.com.caelum.diabetes.dao.DbHelper;
import br.com.caelum.diabetes.dao.PacienteDao;
import br.com.caelum.diabetes.model.DadosMedicos;
import br.com.caelum.diabetes.model.TipoDadoMedico;
import br.com.caelum.diabetes.util.ValidatorUtils;

public class ConfigurarInsulinaCorrecaoFragment extends Fragment {
	private EditText cafe;
	private EditText almoco;
	private EditText jantar;
	private EditText lancheManha;
	private EditText lancheTarde;
	private EditText ceia;
	private Button salvar;
	private View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.configurar_insulina_correcao, null);

		getValoresGlobais();
		settarTextos();
		validateOnFocusChange(cafe);
		validateOnFocusChange(almoco);
		validateOnFocusChange(jantar);
		validateOnFocusChange(lancheManha);
		validateOnFocusChange(lancheTarde);
		validateOnFocusChange(ceia);

		salvar.setEnabled(ValidatorUtils.checkIfIsValid(cafe, almoco, jantar,
				lancheManha, lancheTarde, ceia));
		salvar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				DadosMedicos dadosMedicos = new DadosMedicos(
						TipoDadoMedico.CORRECAO);
				dadosMedicos.setCafeManha(Double.parseDouble(cafe.getText()
						.toString()));
				dadosMedicos.setLancheManha(Double.parseDouble(lancheManha
						.getText().toString()));
				dadosMedicos.setAlmoco(Double.parseDouble(almoco.getText()
						.toString()));
				dadosMedicos.setLancheTarde(Double.parseDouble(lancheTarde
						.getText().toString()));
				dadosMedicos.setJantar(Double.parseDouble(jantar.getText()
						.toString()));
				dadosMedicos.setCeia(Double.parseDouble(ceia.getText()
						.toString()));

				DbHelper helper = new DbHelper(getActivity());

				PacienteDao pacienteDao = new PacienteDao(helper);
				dadosMedicos.setPaciente(pacienteDao.getPaciente());

				DadosMedicosDao dadosDao = new DadosMedicosDao(helper);
				dadosDao.salva(dadosMedicos);

				helper.close();

				getFragmentManager().popBackStack();
			}
		});

		return view;
	}

	private void validateOnFocusChange(final EditText editText) {

		editText.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				salvar.setEnabled(ValidatorUtils.checkIfIsValid(cafe, almoco,
						jantar, lancheManha, lancheTarde, ceia));
			}

		});

	}

	private void settarTextos() {
		DbHelper helper = new DbHelper(getActivity());
		DadosMedicosDao dao = new DadosMedicosDao(helper);

		DadosMedicos dadosMedicosAntigo = dao
				.getDadosMedicosCom(TipoDadoMedico.CORRECAO);
		if (dadosMedicosAntigo == null)
			return;

		cafe.setText(String.valueOf(dadosMedicosAntigo.getCafeManha()));
		lancheManha
				.setText(String.valueOf(dadosMedicosAntigo.getLancheManha()));
		almoco.setText(String.valueOf(dadosMedicosAntigo.getAlmoco()));
		lancheTarde
				.setText(String.valueOf(dadosMedicosAntigo.getLancheTarde()));
		jantar.setText(String.valueOf(dadosMedicosAntigo.getJantar()));
		ceia.setText(String.valueOf(dadosMedicosAntigo.getCeia()));

		helper.close();
	}

	private void getValoresGlobais() {
		cafe = (EditText) view.findViewById(R.id.valor_cafe_correcao);
		lancheManha = (EditText) view
				.findViewById(R.id.valor_lanche_manha_correcao);
		almoco = (EditText) view.findViewById(R.id.valor_almoco_correcao);
		lancheTarde = (EditText) view
				.findViewById(R.id.valor_lanche_tarde_correcao);
		jantar = (EditText) view.findViewById(R.id.valor_jantar_correcao);
		ceia = (EditText) view.findViewById(R.id.valor_ceia_correcao);
		salvar = (Button) view.findViewById(R.id.salvar_insulina_correcao);
	}
}

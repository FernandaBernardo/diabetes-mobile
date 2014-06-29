package br.com.caelum.diabetes.fragment.perfil;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import br.com.caelum.diabetes.R;
import br.com.caelum.diabetes.dao.DbHelper;
import br.com.caelum.diabetes.dao.PacienteDao;
import br.com.caelum.diabetes.model.Paciente;

public class ConfigurarDadosPessoaisFragment extends Fragment{
	private Paciente paciente;
	private EditText idade;
	private EditText peso;
	private EditText altura;
	private RadioButton feminino;
	private RadioButton masculino;
	private RadioButton tipo1;
	private RadioButton tipo2;
	private PacienteDao dao;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.configurar_dados, null);
		
		idade = (EditText) view.findViewById(R.id.idade);
		peso = (EditText) view.findViewById(R.id.peso);
		altura = (EditText) view.findViewById(R.id.altura);
		feminino = (RadioButton) view.findViewById(R.id.feminino);
		masculino = (RadioButton) view.findViewById(R.id.masculino);
		tipo1 = (RadioButton) view.findViewById(R.id.diabetes1);
		tipo2 = (RadioButton) view.findViewById(R.id.diabetes2);
		
		DbHelper helper = new DbHelper(getActivity());
		dao = new PacienteDao(helper);
		paciente = dao.getPaciente();
		
		idade.setText(String.valueOf(paciente.getIdade()));
		peso.setText(String.valueOf(paciente.getPeso()));
		altura.setText(String.valueOf(paciente.getAltura()));
		if (masculino.getText().toString().equals(paciente.getSexo())) {
			masculino.setChecked(true);
		} else {
			feminino.setChecked(true);
		}
		if (tipo2.getText().toString().equals(paciente.getTipoDiabetes())) {
			tipo2.setChecked(true);
		} else {
			tipo1.setChecked(true);
		}
		
		Button salvar = (Button) view.findViewById(R.id.salvar_dados);
		salvar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				paciente.setIdade(Integer.parseInt(idade.getText().toString()));
				paciente.setPeso(Double.parseDouble(peso.getText().toString()));
				paciente.setAltura(Double.parseDouble(altura.getText().toString()));
				
				if (masculino.isChecked()) {
					paciente.setSexo(masculino.getText().toString());;
				} else {
					paciente.setSexo(feminino.getText().toString());;
				}
				
				if (tipo2.isChecked()) {
					paciente.setTipoDiabetes(tipo2.getText().toString());
				} else {
					paciente.setTipoDiabetes(tipo1.getText().toString());
				}
				
				dao.atualiza(paciente);
				
				getFragmentManager().popBackStack();
			}
		});
		
		return view;
	}
}



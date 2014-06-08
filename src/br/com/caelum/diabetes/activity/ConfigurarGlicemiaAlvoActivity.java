package br.com.caelum.diabetes.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import br.com.caelum.diabetes.R;
import br.com.caelum.diabetes.dao.DadosMedicosDao;
import br.com.caelum.diabetes.dao.DbHelper;
import br.com.caelum.diabetes.dao.PacienteDao;
import br.com.caelum.diabetes.model.DadosMedicos;
import br.com.caelum.diabetes.model.TipoDadoMedico;

public class ConfigurarGlicemiaAlvoActivity extends Activity{
	private EditText cafe;
	private EditText almoco;
	private EditText jantar;
	private Button salvar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.configurar_glicemia);
		
		getValoresGlobais();
		settarTextos();
		
		salvar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				DadosMedicos dadosMedicos = new DadosMedicos(TipoDadoMedico.GLICEMIA_ALVO);
				dadosMedicos.setCafeManha(Double.parseDouble(cafe.getText().toString()));
				dadosMedicos.setAlmoco(Double.parseDouble(almoco.getText().toString()));
				dadosMedicos.setJantar(Double.parseDouble(jantar.getText().toString()));
				
				DbHelper helper = new DbHelper(ConfigurarGlicemiaAlvoActivity.this);
				
				PacienteDao pacienteDao = new PacienteDao(helper);
				dadosMedicos.setPaciente(pacienteDao.getPaciente());
				
				DadosMedicosDao dadosDao = new DadosMedicosDao(helper);
				dadosDao.salva(dadosMedicos);
				
				helper.close();
				
				finish();
			}
		});
	}
	
	private void settarTextos() {
		DbHelper helper = new DbHelper(this);
		DadosMedicosDao dao = new DadosMedicosDao(helper);
		
		DadosMedicos dadosMedicosAntigo = dao.getDadosMedicosCom(TipoDadoMedico.GLICEMIA_ALVO);
		if(dadosMedicosAntigo == null) return;
		
		cafe.setText(String.valueOf(dadosMedicosAntigo.getCafeManha()));
		almoco.setText(String.valueOf(dadosMedicosAntigo.getAlmoco()));
		jantar.setText(String.valueOf(dadosMedicosAntigo.getJantar()));
		
		helper.close();
	}

	private void getValoresGlobais() {
		cafe = (EditText) findViewById(R.id.valor_cafe_glicemia);
		almoco = (EditText) findViewById(R.id.valor_almoco_glicemia);
		jantar = (EditText) findViewById(R.id.valor_jantar_glicemia);
		salvar = (Button) findViewById(R.id.salvar_glicemia);
	}
}

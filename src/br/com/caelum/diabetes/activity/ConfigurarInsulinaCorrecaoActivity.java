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

public class ConfigurarInsulinaCorrecaoActivity extends Activity {
	private EditText cafe;
	private EditText almoco;
	private EditText jantar;
	private EditText lancheManha;
	private EditText lancheTarde;
	private EditText ceia;
	private Button salvar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.configurar_insulina_correcao);
		
		getValoresGlobais();
		settarTextos();
		
		salvar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				DadosMedicos dadosMedicos = new DadosMedicos(TipoDadoMedico.CORRECAO);
				dadosMedicos.setCafeManha(Double.parseDouble(cafe.getText().toString()));
				dadosMedicos.setLancheManha(Double.parseDouble(lancheManha.getText().toString()));
				dadosMedicos.setAlmoco(Double.parseDouble(almoco.getText().toString()));
				dadosMedicos.setLancheTarde(Double.parseDouble(lancheTarde.getText().toString()));
				dadosMedicos.setJantar(Double.parseDouble(jantar.getText().toString()));
				dadosMedicos.setCeia(Double.parseDouble(ceia.getText().toString()));
				
				DbHelper helper = new DbHelper(ConfigurarInsulinaCorrecaoActivity.this);
				
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
		
		DadosMedicos dadosMedicosAntigo = dao.getDadosMedicosCom(TipoDadoMedico.CORRECAO);
		if(dadosMedicosAntigo == null) return;
		
		cafe.setText(String.valueOf(dadosMedicosAntigo.getCafeManha()));
		lancheManha.setText(String.valueOf(dadosMedicosAntigo.getLancheManha()));
		almoco.setText(String.valueOf(dadosMedicosAntigo.getAlmoco()));
		lancheTarde.setText(String.valueOf(dadosMedicosAntigo.getLancheTarde()));
		jantar.setText(String.valueOf(dadosMedicosAntigo.getJantar()));
		ceia.setText(String.valueOf(dadosMedicosAntigo.getCeia()));
		
		helper.close();
	}

	private void getValoresGlobais() {
		cafe = (EditText) findViewById(R.id.valor_cafe_correcao);
		lancheManha = (EditText) findViewById(R.id.valor_lanche_manha_correcao);
		almoco = (EditText) findViewById(R.id.valor_almoco_correcao);
		lancheTarde = (EditText) findViewById(R.id.valor_lanche_tarde_correcao);
		jantar = (EditText) findViewById(R.id.valor_jantar_correcao);
		ceia = (EditText) findViewById(R.id.valor_ceia_correcao);
		salvar = (Button) findViewById(R.id.salvar_insulina_correcao);
	}
}

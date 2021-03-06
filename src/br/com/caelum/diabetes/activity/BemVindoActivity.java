package br.com.caelum.diabetes.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import br.com.caelum.diabetes.R;
import br.com.caelum.diabetes.dao.DbHelper;
import br.com.caelum.diabetes.dao.PacienteDao;
import br.com.caelum.diabetes.model.Paciente;

public class BemVindoActivity extends Activity {
	
	private Paciente pacienteBanco;
	private PacienteDao dao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
		setContentView(R.layout.bem_vindo);
		
		DbHelper helper = new DbHelper(BemVindoActivity.this);
		dao = new PacienteDao(helper);
		pacienteBanco = dao.getPaciente();
		
		if(pacienteBanco != null) {
			Intent intent = new Intent(BemVindoActivity.this, MainActivity.class);
			intent.putExtra("paciente", pacienteBanco);
			startActivity(intent);
		}
		
		Button botao = (Button) findViewById(R.id.botao_proximo);
		botao.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				EditText nomePessoa = (EditText) findViewById(R.id.nome_pessoa);
				Paciente paciente = new Paciente();
				paciente.setNome(nomePessoa.getText().toString());
				dao.salva(paciente);
				Intent intent = new Intent(BemVindoActivity.this, MainActivity.class);
				intent.putExtra("paciente", paciente);
				startActivity(intent);
			}
		});
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}
}

package br.com.caelum.diabetes.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import br.com.caelum.diabetes.R;
import br.com.caelum.diabetes.dao.DbHelper;
import br.com.caelum.diabetes.dao.PacienteDao;
import br.com.caelum.diabetes.model.Paciente;

public class BemVindoActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bem_vindo);
		
		Button botao = (Button) findViewById(R.id.botao_proximo);
		botao.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				EditText nomePessoa = (EditText) findViewById(R.id.nome_pessoa);
				
				Paciente paciente = Paciente.getinstance();
				paciente.setNome(nomePessoa.getText().toString());
				
				DbHelper helper = new DbHelper(BemVindoActivity.this);
				
				PacienteDao dao = new PacienteDao(helper);
				dao.salva(paciente);
				
				helper.close();
				
				Intent intent = new Intent(BemVindoActivity.this, HomeActivity.class);
				startActivity(intent);
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_principal, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		
		int itemId = item.getItemId();
		
		if(itemId == R.id.novo_refeicao) {
			Intent intent = new Intent(BemVindoActivity.this, SelecionaRefeicaoActivity.class);
			startActivityForResult(intent, 0);
			return true;
		}
		
		if(itemId == R.id.perfil) {
			Intent intent = new Intent(BemVindoActivity.this, ConfigurarPerfilActivity.class);
			startActivity(intent);
			return true;
		}
		return false;
	}
}

package br.com.caelum.diabetes.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import br.com.caelum.diabetes.R;

public class BemVindoActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bem_vindo);
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
		
		if(itemId == R.id.novo_alimento) {
			Intent intent = new Intent(BemVindoActivity.this, AdicionaAlimentoActivity.class);
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

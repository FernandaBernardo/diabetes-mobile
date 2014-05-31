package br.com.caelum.diabetes;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import br.com.caelum.diabetes.model.Alimento;

public class MontaRefeicaoActivity extends Activity{
	private Refeicao refeicao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.monta_refeicao);
		
		refeicao = new Refeicao();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		Bundle bundle = getIntent().getExtras();
		Alimento alimento = (Alimento) bundle.get("alimento");
		refeicao.adicionaAlimento(alimento);
		
		ListView lista = (ListView) findViewById(R.id.lista_alimentos);
		
		List<Alimento> alimentos = refeicao.getAlimentos();
		ArrayAdapter<Alimento> adapter = new ArrayAdapter<Alimento>(this, android.R.layout.simple_list_item_1, alimentos);
		lista.setAdapter(adapter);
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
			Intent intent = new Intent(MontaRefeicaoActivity.this, AdicionaAlimentoActivity.class);
			startActivityForResult(intent, itemId);
			return true;
		}
			
		return false;
	}
}

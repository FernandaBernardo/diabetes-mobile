package br.com.caelum.diabetes;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import br.com.caelum.diabetes.model.Alimento;

public class MontaRefeicaoActivity extends Activity{
	private Refeicao refeicao = new Refeicao();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.monta_refeicao);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if(requestCode == 0 && resultCode == RESULT_OK) {
			Alimento alimento = (Alimento) data.getSerializableExtra("alimento");
			refeicao.adicionaAlimento(alimento);
			
			ListView lista = (ListView) findViewById(R.id.lista_alimentos);
			
			List<Alimento> alimentos = refeicao.getAlimentos();
			ArrayAdapter<Alimento> adapter = new ArrayAdapter<Alimento>(this, android.R.layout.simple_list_item_1, alimentos);
			lista.setAdapter(adapter);
			
			EditText totalCHO = (EditText) findViewById(R.id.totalCHO);
			totalCHO.setText(String.valueOf(refeicao.getTotalCHO()));
		}
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
			intent.putExtra("refeicao", refeicao);
			startActivityForResult(intent, 0);
			return true;
		}
			
		return false;
	}
}

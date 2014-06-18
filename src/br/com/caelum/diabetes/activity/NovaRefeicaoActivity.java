package br.com.caelum.diabetes.activity;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import br.com.caelum.diabetes.R;
import br.com.caelum.diabetes.dao.DbHelper;
import br.com.caelum.diabetes.dao.PacienteDao;
import br.com.caelum.diabetes.dao.RefeicaoDao;
import br.com.caelum.diabetes.extras.TipoRefeicao;
import br.com.caelum.diabetes.model.AlimentoVirtual;
import br.com.caelum.diabetes.model.Paciente;
import br.com.caelum.diabetes.model.Refeicao;

public class NovaRefeicaoActivity extends Activity{
	private Refeicao refeicao;
	private Paciente paciente;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nova_refeicao);
		
		Bundle bundle = getIntent().getExtras();
		TipoRefeicao tipoRefeicao = TipoRefeicao.fromString((String) bundle.get("tipo_refeicao"));
		refeicao = new Refeicao(tipoRefeicao);
		
		DbHelper helper = new DbHelper(this);
		
		RefeicaoDao refeicaoDao = new RefeicaoDao(helper);
		refeicao.setId(refeicaoDao.salva(refeicao));
		
		PacienteDao pacienteDao = new PacienteDao(helper);
		paciente = pacienteDao.getPaciente();
		
		helper.close();
		
		Button salvar = (Button) findViewById(R.id.salvar_refeicao);
		salvar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(NovaRefeicaoActivity.this, ListaRefeicaoActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if(requestCode == 0 && resultCode == RESULT_OK) {
			refeicao = (Refeicao) data.getSerializableExtra("refeicao");
			
			ListView lista = (ListView) findViewById(R.id.lista_alimentos);
			
			List<AlimentoVirtual> alimentos = refeicao.getAlimentos();
			ArrayAdapter<AlimentoVirtual> adapter = new ArrayAdapter<AlimentoVirtual>(this, android.R.layout.simple_list_item_1, alimentos);
			lista.setAdapter(adapter);
			
			EditText totalCHO = (EditText) findViewById(R.id.totalCHO);
			totalCHO.setText(String.valueOf(refeicao.getTotalCHO()));
			
			double valorInsulina = new CalculaInsulina(refeicao, paciente).getTotalInsulina();
			
			EditText totalInsulina = (EditText) findViewById(R.id.totalInsulina);
			totalInsulina.setText(String.valueOf(valorInsulina));
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
		
		if(itemId == R.id.novo_refeicao) {
			Intent intent = new Intent(NovaRefeicaoActivity.this, AdicionaAlimentoActivity.class);
			intent.putExtra("refeicao", refeicao);
			startActivityForResult(intent, 0);
			return true;
		}
		
		if(itemId == R.id.perfil) {
			Intent intent = new Intent(NovaRefeicaoActivity.this, ConfigurarPerfilActivity.class);
			startActivity(intent);
			return true;
		}
		return false;
	}
}
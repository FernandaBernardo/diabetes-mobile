package br.com.caelum.diabetes.activity;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import br.com.caelum.diabetes.R;
import br.com.caelum.diabetes.dao.DbHelper;
import br.com.caelum.diabetes.dao.RefeicaoDao;
import br.com.caelum.diabetes.model.Refeicao;

public class ListaRefeicaoActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lista_refeicao);
		
		DbHelper helper = new DbHelper(this);
		RefeicaoDao dao = new RefeicaoDao(helper);
		
		List<Refeicao> refeicoes = dao.getRefeicoes();
		
		helper.close();
		
		ArrayAdapter<Refeicao> adapter = new ArrayAdapter<Refeicao>(this, android.R.layout.simple_list_item_1, refeicoes);
		
		ListView listaRefeicoes = (ListView) findViewById(R.id.lista_refeicoes);
		listaRefeicoes.setAdapter(adapter);
	}
}

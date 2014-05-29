package br.com.caelum.diabetes;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import br.com.caelum.diabetes.model.Alimento;

public class ListaAlimentosActivity extends Activity{
	private ListView lista;
	private ListaAlimentos listaAlimentos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lista_alimentos);
		
		lista = (ListView) findViewById(R.id.cho);
		registerForContextMenu(lista);
	}

	@Override
	protected void onResume() {
		super.onResume();
		carregaLista();
	}

	private void carregaLista() {
		listaAlimentos = new ListaAlimentos();
		Bundle bundle = getIntent().getExtras();
		
		String cho = (String) bundle.get("carboidrato");
		
		List<Alimento> carboidratos = listaAlimentos.getAlimentos(cho);
		ListaAlimentoAdapter adapter = new ListaAlimentoAdapter(carboidratos, ListaAlimentosActivity.this);
		lista.setAdapter(adapter);
	}
}

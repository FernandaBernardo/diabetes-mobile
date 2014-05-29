package br.com.caelum.diabetes;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import br.com.caelum.diabetes.model.Alimento;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ListaAlimentos listaAlimentos = new ListaAlimentos();
		List<Alimento> alimentos = listaAlimentos.getAlimentos();
//		String[] alimentos = {"arroz branco", "arroz integral", "batata", "feijão", "pão francês", "pão de forma", "chocolate", "bala"}; 
		ArrayAdapter<Alimento> adapter = new ArrayAdapter<Alimento>(this, android.R.layout.simple_dropdown_item_1line, alimentos);
		
		AutoCompleteTextView busca = (AutoCompleteTextView) findViewById(R.id.busca);
		
		busca.setAdapter(adapter);
	}
}

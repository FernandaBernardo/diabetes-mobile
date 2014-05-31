package br.com.caelum.diabetes;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import br.com.caelum.diabetes.model.Alimento;

public class MainActivity extends Activity {

	private EditText carboidrato;
	protected Alimento alimentoAtual;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		ListaAlimentos listaAlimentos = new ListaAlimentos();
		final List<Alimento> alimentos = listaAlimentos.getAlimentos();
		
		ArrayAdapter<Alimento> adapter = new ArrayAdapter<Alimento>(this, android.R.layout.simple_dropdown_item_1line, alimentos);
		AutoCompleteTextView buscaAlimento = (AutoCompleteTextView) findViewById(R.id.busca);
		buscaAlimento.setAdapter(adapter);
		
		carboidrato = (EditText) findViewById(R.id.carboidrato_alimento);
		
		buscaAlimento.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int pos, long arg3) {
				alimentoAtual = (Alimento) adapter.getAdapter().getItem(pos);
				
				carboidrato.setText(String.valueOf(alimentoAtual.getCarboidrato()));
			}
		});
		
		EditText valor = (EditText) findViewById(R.id.valor);
		valor.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {	
				if(s != null && s.length()>0) {
					double carboidratoPorValor = alimentoAtual.getCarboidratoPorValor(Double.parseDouble(s.toString()));
					carboidrato.setText(String.valueOf(carboidratoPorValor));
				} else{
					carboidrato.setText("0.0");
				}
			}

			@Override
			public void afterTextChanged(Editable s) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}
		});
	}
}

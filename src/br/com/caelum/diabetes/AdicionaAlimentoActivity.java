package br.com.caelum.diabetes;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import br.com.caelum.diabetes.dao.AlimentoDao;
import br.com.caelum.diabetes.model.Alimento;

public class AdicionaAlimentoActivity extends Activity {

	private EditText carboidrato;
	private Alimento alimentoAtual;
	private EditText valor;
	private EditText unidade;
	private Button botao;
	private AlimentoDao alimentoDao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.adiciona_alimento);
		
		alimentoDao = new AlimentoDao(this);
		
		carboidrato = (EditText) findViewById(R.id.carboidrato_alimento);
		valor = (EditText) findViewById(R.id.valor);
		unidade = (EditText) findViewById(R.id.unidade);
		botao = (Button) findViewById(R.id.adicionar_alimento);
		
		final List<Alimento> alimentos = alimentoDao.getAlimentos();
		
		ArrayAdapter<Alimento> adapter = new ArrayAdapter<Alimento>(this, android.R.layout.simple_dropdown_item_1line, alimentos);
		AutoCompleteTextView buscaAlimento = (AutoCompleteTextView) findViewById(R.id.busca);
		buscaAlimento.setAdapter(adapter);
		
		
		buscaAlimento.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int pos, long arg3) {
				alimentoAtual = (Alimento) adapter.getAdapter().getItem(pos);
				
				carboidrato.setText(String.valueOf(alimentoAtual.getCarboidrato()));
				valor.setText("1");
				unidade.setText(alimentoAtual.getUnidadeDeMedida());
			}
		});
		
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
			public void afterTextChanged(Editable arg0) {
			}
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
			}
		});
		
		botao.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(AdicionaAlimentoActivity.this, MontaRefeicaoActivity.class);
				intent.putExtra("alimento", alimentoAtual);
				startActivity(intent);
			}
		});
	}
}

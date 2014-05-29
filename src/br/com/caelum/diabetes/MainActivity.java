package br.com.caelum.diabetes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button botao = (Button) findViewById(R.id.buscar);
		
		botao.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText busca = (EditText) findViewById(R.id.alimento);
				String alimentoBuscado = busca.getText().toString();
				
				Intent intent = new Intent(MainActivity.this, ListaAlimentosActivity.class);
				intent.putExtra("carboidrato", alimentoBuscado);
				startActivity(intent);
			}
		});
	}
}

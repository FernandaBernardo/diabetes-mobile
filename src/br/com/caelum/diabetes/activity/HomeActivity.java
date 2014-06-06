package br.com.caelum.diabetes.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import br.com.caelum.diabetes.R;

public class HomeActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		
		Button calculadora = (Button) findViewById(R.id.main_calculadora);
		calculadora.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(HomeActivity.this, SelecionaRefeicaoActivity.class);
				startActivity(intent);
			}
		});
		
		Button perfil = (Button) findViewById(R.id.main_perfil);
		perfil.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(HomeActivity.this, ConfigurarPerfilActivity.class);
				startActivity(intent);
			}
		});
	}
}

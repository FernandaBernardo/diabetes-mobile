package br.com.caelum.diabetes.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import br.com.caelum.diabetes.R;
import br.com.caelum.diabetes.extras.Extras;

public class SelecionaRefeicaoActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.seleciona_refeicao);
		
		startActivityWithButton((Button) findViewById(R.id.tipo_cafe_manha), Extras.cafeDaManha);
		startActivityWithButton((Button) findViewById(R.id.tipo_lanche_manha), Extras.lancheDaManha);
		startActivityWithButton((Button) findViewById(R.id.tipo_almoco), Extras.almoco);
		startActivityWithButton((Button) findViewById(R.id.tipo_lanche_tarde), Extras.lancheDaTarde);
		startActivityWithButton((Button) findViewById(R.id.tipo_jantar), Extras.jantar);
		startActivityWithButton((Button) findViewById(R.id.tipo_ceia), Extras.ceia);
	}

	private void startActivityWithButton(Button botao, final String tipoRefeicao) {
		botao.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SelecionaRefeicaoActivity.this, MontaRefeicaoActivity.class);
				intent.putExtra("tipo_refeicao", tipoRefeicao);
				startActivity(intent);
			}
		});
	}
}

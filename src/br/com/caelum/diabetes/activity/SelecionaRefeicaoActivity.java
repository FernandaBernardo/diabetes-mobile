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
		
		startActivityWithButton((Button) findViewById(R.id.tipo_cafe_manha), Extras.CAFE_DA_MANHA);
		startActivityWithButton((Button) findViewById(R.id.tipo_lanche_manha), Extras.LANCHE_DA_MANHA);
		startActivityWithButton((Button) findViewById(R.id.tipo_almoco), Extras.ALMOCO);
		startActivityWithButton((Button) findViewById(R.id.tipo_lanche_tarde), Extras.LANCHE_DA_TARDE);
		startActivityWithButton((Button) findViewById(R.id.tipo_jantar), Extras.JANTAR);
		startActivityWithButton((Button) findViewById(R.id.tipo_ceia), Extras.CEIA);
	}

	private void startActivityWithButton(Button botao, final String tipoRefeicao) {
		botao.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SelecionaRefeicaoActivity.this, NovaRefeicaoActivity.class);
				intent.putExtra("tipo_refeicao", tipoRefeicao);
				startActivity(intent);
			}
		});
	}
}

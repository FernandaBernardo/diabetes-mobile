package br.com.caelum.diabetes.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import br.com.caelum.diabetes.R;


public class ConfigurarPerfilActivity extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState ){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.configurar_perfil);
		
		Button botaoDados = (Button) findViewById(R.id.perfil_dados);
		Button botaoBasal = (Button) findViewById(R.id.perfil_basal);
		Button botaoBolus = (Button) findViewById(R.id.perfil_bolus);
		Button botaoGlicemiaAlvo = (Button) findViewById(R.id.perfil_glicemia_alvo);
		
		Button botaoVoltar = (Button) findViewById(R.id.voltar);
		
		onClickBotao(botaoDados, ConfigurarDadosPessoaisActivity.class);
		onClickBotao(botaoBasal, ConfigurarInsulinaContinuaActivity.class);
		onClickBotao(botaoBolus, ConfigurarInsulinaCorrecaoActivity.class);
		onClickBotao(botaoGlicemiaAlvo, ConfigurarGlicemiaAlvoActivity.class);
		
		onClickBotao(botaoVoltar, HomeActivity.class);
	}

	private void onClickBotao(Button botaoDados, final Class class1) {
		botaoDados.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(ConfigurarPerfilActivity.this, class1);
				startActivity(intent);
			}
		});
	}
}

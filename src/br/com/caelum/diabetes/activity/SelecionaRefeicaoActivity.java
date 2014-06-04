package br.com.caelum.diabetes.activity;

import br.com.caelum.diabetes.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Activity que representa uma sele��o a ser escolhida pelo usu�rio (Caf� da manh�, almo�o, jantar....).
 * <p> Essa escolha influenciar� nos c�lculos a serem realizados em {@link AdicionaAlimentoActivity}
 * @author Johnny Taira
 *
 */
public class SelecionaRefeicaoActivity extends Activity {
	private Spinner spinnerRefeicao;
	private TextView selecioneRefeicao;
	private Button buttonRefeicao;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.seleciona_refeicao);
		spinnerRefeicao = (Spinner)findViewById(R.id.refeicao_spinner);
		
		selecioneRefeicao = (TextView)findViewById(R.id.text_view_refeicao);
		
		buttonRefeicao = (Button)findViewById(R.id.button_refeicao_selecionada);
		buttonRefeicao.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SelecionaRefeicaoActivity.this, MontaRefeicaoActivity.class);
				intent.putExtra("tipo_refeicao", spinnerRefeicao.getSelectedItem().toString());
				startActivity(intent);
			}
		});
	}
}

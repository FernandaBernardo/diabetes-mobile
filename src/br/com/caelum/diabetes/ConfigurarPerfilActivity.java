package br.com.caelum.diabetes;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;


public class ConfigurarPerfilActivity extends Activity{
	
	
	private Button buttonSalvar;
	private TextView textBasal;
	private TextView textPeso;
	private TextView textCorrecao;
	private TextView textContagem;
	private TextView textAlvo;
	
	@Override
	public void onCreate(Bundle savedInstanceState ){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.configurar_perfil);
		//oi eu sou um comentário NÃO ME APAGUE PFVR TENHA PIEDADE
		buttonSalvar = (Button) findViewById(R.id.button_configurar_perfil);
		textBasal = (TextView) findViewById(R.id.text_gli_basal);
		textPeso = (TextView) findViewById(R.id.text_novo_peso);
		textCorrecao = (TextView) findViewById(R.id.text_gli_correcao);
		textContagem = (TextView) findViewById(R.id.text_gli_contagem);
		textAlvo = (TextView) findViewById(R.id.text_gli_alvo);
		
	
	}
	
}

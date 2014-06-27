package br.com.caelum.diabetes.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import br.com.caelum.diabetes.R;

public class DashboardCalculadoraFragment extends Fragment{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.dashboard_calculadora, null);
		
		Button novaRefeicao = (Button) view.findViewById(R.id.nova_refeicao);
		Button adicionarNovoAlimento = (Button) view.findViewById(R.id.adicionar_novo_alimento);
		
		novaRefeicao.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				FragmentTransaction transaction = getFragmentManager().beginTransaction();
				transaction.replace(R.id.main_view, new NovaRefeicaoFragment());
				transaction.commit();
			}
		});
		
		adicionarNovoAlimento.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				FragmentTransaction transaction = getFragmentManager().beginTransaction();
				transaction.replace(R.id.main_view, new NovoAlimentoDiferenteFragment());
				transaction.commit();
			}
		});
		
		return view;
	}
}

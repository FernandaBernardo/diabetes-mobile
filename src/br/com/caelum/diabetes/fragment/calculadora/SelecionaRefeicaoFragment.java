package br.com.caelum.diabetes.fragment.calculadora;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import br.com.caelum.diabetes.R;
import br.com.caelum.diabetes.extras.Extras;

public class SelecionaRefeicaoFragment extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.seleciona_refeicao, null);
		
		startActivityWithButton((Button) view.findViewById(R.id.tipo_cafe_manha), Extras.CAFE_DA_MANHA);
		startActivityWithButton((Button) view.findViewById(R.id.tipo_lanche_manha), Extras.LANCHE_DA_MANHA);
		startActivityWithButton((Button) view.findViewById(R.id.tipo_almoco), Extras.ALMOCO);
		startActivityWithButton((Button) view.findViewById(R.id.tipo_lanche_tarde), Extras.LANCHE_DA_TARDE);
		startActivityWithButton((Button) view.findViewById(R.id.tipo_jantar), Extras.JANTAR);
		startActivityWithButton((Button) view.findViewById(R.id.tipo_ceia), Extras.CEIA);
	
		return view;
	}
	private void startActivityWithButton(Button botao, final String tipoRefeicao) {
		botao.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Bundle bundle = new Bundle();
				bundle.putString("tipo_refeicao", tipoRefeicao);
				
				Fragment fragment = new NovaRefeicaoFragment();
				fragment.setArguments(bundle);
				
				FragmentTransaction transaction = getFragmentManager().beginTransaction();
				transaction.replace(R.id.main_view, fragment);
				transaction.commit();
			}
		});
	}
}

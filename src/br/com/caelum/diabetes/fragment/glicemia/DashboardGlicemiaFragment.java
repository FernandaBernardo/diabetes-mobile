package br.com.caelum.diabetes.fragment.glicemia;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import br.com.caelum.diabetes.R;
import br.com.caelum.diabetes.calculos.CalculaMediaGlicemia;

public class DashboardGlicemiaFragment extends Fragment{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.dashboard_glicemia, null);
		
		Button novaMedicao = (Button) view.findViewById(R.id.nova_medicao);
		
		novaMedicao.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				FragmentTransaction transaction = getFragmentManager().beginTransaction();
				transaction.replace(R.id.main_view, new NovaGlicemiaFragment());
				transaction.commit();
			}
		});
		
		Button glicemias = (Button) view.findViewById(R.id.lista_glicemias);
		glicemias.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				FragmentTransaction transaction = getFragmentManager().beginTransaction();
				transaction.replace(R.id.main_view, new ListaGlicemiaFragment());
				transaction.commit();
			}
		});
		
		EditText glicemiasSemana = (EditText) view.findViewById(R.id.media_glicemia_semana);
		CalculaMediaGlicemia medias = new CalculaMediaGlicemia(getActivity());
		glicemiasSemana.setText(medias.getMediaDaSemana() + "");
		EditText glicemiasMes = (EditText) view.findViewById(R.id.media_glicemia_mes);
		glicemiasMes.setText(medias.getMediaDoMes() + "");
		return view;
	}
}
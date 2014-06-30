package br.com.caelum.diabetes.fragment.glicemia;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import br.com.caelum.diabetes.R;
import br.com.caelum.diabetes.dao.DbHelper;
import br.com.caelum.diabetes.dao.GlicemiaDao;
import br.com.caelum.diabetes.model.Glicemia;

public class ListaGlicemiaFragment extends Fragment{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.lista_glicemia, null);
		
		DbHelper helper = new DbHelper(getActivity());
		GlicemiaDao dao = new GlicemiaDao(helper);
		
		List<Glicemia> glicemias = dao.getGlicemias();
		
		helper.close();
		
		ArrayAdapter<Glicemia> adapter = new ArrayAdapter<Glicemia>(getActivity(), android.R.layout.simple_list_item_1, glicemias);
		
		ListView listaGlicemias = (ListView) view.findViewById(R.id.glicemias);
		listaGlicemias.setAdapter(adapter);
		
		return view;
	}
}

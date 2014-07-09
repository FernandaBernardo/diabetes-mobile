package br.com.caelum.diabetes.fragment.lembretes;

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
import br.com.caelum.diabetes.dao.LembreteDao;
import br.com.caelum.diabetes.model.Lembrete;

public class ListarTodosLembretesFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.listar_lembretes, null);
		DbHelper helper = new DbHelper(getActivity());
		LembreteDao dao = new LembreteDao(helper);

		List<Lembrete> lembretes = dao.getLembretes();

		helper.close();

		ArrayAdapter<Lembrete> adapter = new ArrayAdapter<Lembrete>(
				getActivity(), android.R.layout.simple_list_item_1, lembretes);

		ListView listaLembretes = (ListView) view
				.findViewById(R.id.list_lembretes_all);
		listaLembretes.setAdapter(adapter);

		return view;
	}
}

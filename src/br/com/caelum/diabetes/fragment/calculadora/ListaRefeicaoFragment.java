package br.com.caelum.diabetes.fragment.calculadora;

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
import br.com.caelum.diabetes.dao.RefeicaoDao;
import br.com.caelum.diabetes.model.Refeicao;

public class ListaRefeicaoFragment extends Fragment{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.lista_refeicao, null);
		
		DbHelper helper = new DbHelper(getActivity());
		RefeicaoDao dao = new RefeicaoDao(helper);
		
		List<Refeicao> refeicoes = dao.getRefeicoes();
		
		helper.close();
		
		ArrayAdapter<Refeicao> adapter = new ArrayAdapter<Refeicao>(getActivity(), android.R.layout.simple_list_item_1, refeicoes);
		
		ListView listaRefeicoes = (ListView) view.findViewById(R.id.lista_refeicoes);
		listaRefeicoes.setAdapter(adapter);
		
		return view;
	}
}

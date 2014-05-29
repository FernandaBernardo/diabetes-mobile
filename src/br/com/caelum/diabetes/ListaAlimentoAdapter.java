package br.com.caelum.diabetes;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.com.caelum.diabetes.model.Alimento;

public class ListaAlimentoAdapter extends BaseAdapter{

	List<Alimento> alimentos;
	private Activity activity;
	
	public ListaAlimentoAdapter(List<Alimento> alimentos, Activity activity) {
		this.alimentos = alimentos;
		this.activity = activity;
	}
	
	@Override
	public int getCount() {
		return alimentos.size();
	}

	@Override
	public Object getItem(int pos) {
		return alimentos.get(pos);
	}

	@Override
	public long getItemId(int pos) {
		return alimentos.get(pos).getId();
	}

	@Override
	public View getView(int pos, View arg1, ViewGroup arg2) {
		LayoutInflater inflater = activity.getLayoutInflater();
		View item = inflater.inflate(R.layout.item, null);
		
		Alimento alimento = alimentos.get(pos);
		
		TextView nome = (TextView) item.findViewById(R.id.nome_alimento);
		nome.setText(alimento.getNome());
		
		TextView cho = (TextView) item.findViewById(R.id.carboidrato_alimento);
		cho.setText(alimento.getCarboidrato()+"");
		
		return item;
	}

}

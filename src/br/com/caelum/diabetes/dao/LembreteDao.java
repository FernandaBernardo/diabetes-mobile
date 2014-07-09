package br.com.caelum.diabetes.dao;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import android.content.ContentValues;
import android.database.Cursor;
import br.com.caelum.diabetes.extras.TabelasBD;
import br.com.caelum.diabetes.model.Lembrete;

public class LembreteDao {
	private static final String TABELA = TabelasBD.LEMBRETE;
	private static final String[] COLUNAS = {"id", "data", "atividade", "anotacoes"};
	private DbHelper helper;

	public LembreteDao(DbHelper helper) {
		this.helper = helper;
	}
	
	public int salva(Lembrete lembrete) {
		ContentValues values = toContentValues(lembrete);
		return (int) helper.getWritableDatabase().insert(TABELA, null, values);
	}

	public void deletar(Lembrete lembrete) {
		String[] args = {String.valueOf(lembrete.getId())};
		helper.getWritableDatabase().delete(TABELA, "id=?", args);
	}

	public void atualiza(Lembrete lembrete) {
		String[] args = {String.valueOf(lembrete.getId())};
		ContentValues values = toContentValues(lembrete);
		helper.getWritableDatabase().update(TABELA, values, "id=?", args);
	}
	
	public List<Lembrete> getLembretes() {
		ArrayList<Lembrete> lembretes = new ArrayList<Lembrete>();
		Cursor cursor = helper.getReadableDatabase().query(TABELA, COLUNAS, null, null, null, null, null);

		while(cursor.moveToNext()) {
			Lembrete lembrete = new Lembrete();
			lembrete.setId(cursor.getInt(0));
			lembrete.setData(new DateTime(cursor.getString(1)));
			lembrete.setAtividade(cursor.getString(2));
			lembrete.setAnotacoes(cursor.getString(3));
			lembretes.add(lembrete);
		}
		cursor.close();

		return lembretes;
	}
	
	private ContentValues toContentValues(Lembrete lembrete) {
		ContentValues values = new ContentValues();
		values.put("data", lembrete.getData().toDate().getTime());
		values.put("atividade", lembrete.getAtividade());
		values.put("anotacoes", lembrete.getAnotacoes());
		return values;
	}
}

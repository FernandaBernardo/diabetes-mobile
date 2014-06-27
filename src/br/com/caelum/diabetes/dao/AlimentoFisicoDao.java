package br.com.caelum.diabetes.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.com.caelum.diabetes.extras.TabelasBD;
import br.com.caelum.diabetes.model.AlimentoFisico;

public class AlimentoFisicoDao{

	private static final String TABELA = TabelasBD.ALIMENTO_FISICO;
	private static final String[] COLUNAS = {"id", "nome", "carboidrato", "unidadeDeMedida"};

	private DbHelper helper;

	public AlimentoFisicoDao(DbHelper helper) {
		this.helper = helper;
	}
	
	public void salva(AlimentoFisico alimento) {
		ContentValues values = toContentValues(alimento);
		helper.getWritableDatabase().insert(TABELA, null, values);
	}
	
	public void salvaFirst(AlimentoFisico alimento, SQLiteDatabase db) {
		ContentValues values = toContentValues(alimento);
		db.insert(TABELA, null, values);
	}

	public void deletar(AlimentoFisico alimento) {
		String[] args = {String.valueOf(alimento.getId())};
		helper.getWritableDatabase().delete(TABELA, "id=?", args);
	}

	public void atualiza(AlimentoFisico alimento) {
		String[] args = {String.valueOf(alimento.getId())};
		ContentValues values = toContentValues(alimento);
		helper.getWritableDatabase().update(TABELA, values, "id=?", args);
	}
	
	public List<AlimentoFisico> getAlimentos() {
		ArrayList<AlimentoFisico> alimentos = new ArrayList<AlimentoFisico>();
		Cursor cursor = helper.getReadableDatabase().query(TABELA, COLUNAS, null, null, null, null, null);

		while(cursor.moveToNext()) {
			AlimentoFisico task = new AlimentoFisico(cursor.getString(1), cursor.getDouble(2), cursor.getString(3));
			task.setId(cursor.getInt(0));
			alimentos.add(task);
		}
		cursor.close();

		return alimentos;
	}
	
	private ContentValues toContentValues(AlimentoFisico alimento) {
		ContentValues values = new ContentValues();
		values.put("nome", alimento.getNome());
		values.put("carboidrato", alimento.getCarboidrato());
		values.put("unidadeDeMedida", alimento.getUnidadeDeMedida());
		return values;
	}

}

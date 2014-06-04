package br.com.caelum.diabetes.dao;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import br.com.caelum.diabetes.model.AlimentoFisico;
import br.com.caelum.diabetes.model.AlimentoVirtual;
import br.com.caelum.diabetes.model.Refeicao;

public class AlimentoVirtualDao{

	private static final String TABELA = "AlimentoVirtual";

	private DbHelper helper;

	public AlimentoVirtualDao(DbHelper helper) {
		this.helper = helper;
	}
	
	public void salva(AlimentoVirtual alimento) {
		ContentValues values = toContentValues(alimento);
		helper.getWritableDatabase().insert(TABELA, null, values);
	}
	
	public void salvaFirst(AlimentoVirtual alimento, SQLiteDatabase db) {
		ContentValues values = toContentValues(alimento);
		db.insert(TABELA, null, values);
	}

	public void deletar(AlimentoVirtual alimento) {
		String[] args = {String.valueOf(alimento.getId())};
		helper.getWritableDatabase().delete(TABELA, "id=?", args);
	}

	public void atualiza(AlimentoVirtual alimento) {
		String[] args = {String.valueOf(alimento.getId())};
		ContentValues values = toContentValues(alimento);
		helper.getWritableDatabase().update(TABELA, values, "id=?", args);
	}
	
	private ContentValues toContentValues(AlimentoVirtual alimento) {
		ContentValues values = new ContentValues();
		values.put("id_refeicao", alimento.getRefeicao().getId());
		values.put("quantidade", alimento.getQuantidade());
		values.put("id_alimento", alimento.getAlimento().getId());
		return values;
	}
}

package br.com.caelum.diabetes.dao;

import android.content.ContentValues;
import br.com.caelum.diabetes.extras.TabelasBD;
import br.com.caelum.diabetes.model.Refeicao;

public class RefeicaoDao{

	private static final String TABELA = TabelasBD.REFEICAO;

	private DbHelper helper;

	public RefeicaoDao(DbHelper helper) {
		this.helper = helper;
	}
	
	public int salva(Refeicao refeicao) {
		ContentValues values = toContentValues(refeicao);
		return (int) helper.getWritableDatabase().insert(TABELA, null, values);
	}

	public void deletar(Refeicao refeicao) {
		String[] args = {String.valueOf(refeicao.getId())};
		helper.getWritableDatabase().delete(TABELA, "id=?", args);
	}

	public void atualiza(Refeicao refeicao) {
		String[] args = {String.valueOf(refeicao.getId())};
		ContentValues values = toContentValues(refeicao);
		helper.getWritableDatabase().update(TABELA, values, "id=?", args);
	}
	
	private ContentValues toContentValues(Refeicao refeicao) {
		ContentValues values = new ContentValues();
		values.put("tipoRefeicao", refeicao.getTipoRefeicao().getText());
		return values;
	}

}

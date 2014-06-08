package br.com.caelum.diabetes.dao;

import android.content.ContentValues;
import br.com.caelum.diabetes.extras.TabelasBD;
import br.com.caelum.diabetes.model.AlimentoVirtual;

public class AlimentoVirtualDao{

	private static final String TABELA = TabelasBD.ALIMENTO_VIRTUAL;

	private DbHelper helper;

	public AlimentoVirtualDao(DbHelper helper) {
		this.helper = helper;
	}
	
	public void salva(AlimentoVirtual alimento) {
		ContentValues values = toContentValues(alimento);
		helper.getWritableDatabase().insert(TABELA, null, values);
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

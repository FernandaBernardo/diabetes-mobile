package br.com.caelum.diabetes.dao;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import android.content.ContentValues;
import android.database.Cursor;
import br.com.caelum.diabetes.extras.TabelasBD;
import br.com.caelum.diabetes.extras.TipoRefeicao;
import br.com.caelum.diabetes.model.AlimentoFisico;
import br.com.caelum.diabetes.model.Refeicao;

public class RefeicaoDao{

	private static final String TABELA = TabelasBD.REFEICAO;
	private static final String[] COLUNAS = {"id", "tipoRefeicao", "data"};

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
	
	public List<Refeicao> getRefeicoes() {
		ArrayList<Refeicao> refeicoes = new ArrayList<Refeicao>();
		Cursor cursor = helper.getReadableDatabase().query(TABELA, COLUNAS, null, null, null, null, null);

		while(cursor.moveToNext()) {
			Refeicao refeicao = new Refeicao(cursor.getInt(0), TipoRefeicao.fromString(cursor.getString(1)), new DateTime(cursor.getLong(2)));
			refeicoes.add(refeicao);
		}
		cursor.close();

		return refeicoes;
	}
	
	private ContentValues toContentValues(Refeicao refeicao) {
		ContentValues values = new ContentValues();
		values.put("tipoRefeicao", refeicao.getTipoRefeicao().getText());
		values.put("data", refeicao.getData().toDate().getTime());
		return values;
	}

}

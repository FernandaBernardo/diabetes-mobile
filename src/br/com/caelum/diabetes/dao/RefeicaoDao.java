package br.com.caelum.diabetes.dao;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import android.content.ContentValues;
import android.database.Cursor;
import br.com.caelum.diabetes.extras.TabelasBD;
import br.com.caelum.diabetes.extras.TipoRefeicao;
import br.com.caelum.diabetes.model.AlimentoFisico;
import br.com.caelum.diabetes.model.AlimentoVirtual;
import br.com.caelum.diabetes.model.Refeicao;

public class RefeicaoDao{

	private static final String TABELA = TabelasBD.REFEICAO;
	private static final String[] COLUNAS = {"id", "tipoRefeicao", "data"};

	private DbHelper helper;

	public RefeicaoDao(DbHelper helper) {
		this.helper = helper;
	}
	
	public int salva(Refeicao refeicao) {
		ContentValues values;
		
		values = toContentValues(refeicao);
		int id = (int) helper.getWritableDatabase().insert(TABELA, null, values);
		
		for (AlimentoVirtual alimento : refeicao.getAlimentos()) {
			values = toContentValues(alimento, id);
			helper.getWritableDatabase().insert(TabelasBD.ALIMENTO_VIRTUAL , null, values);
		}
		return id;
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
			String[] args = {String.valueOf(refeicao.getId())};
			Cursor cursor2 = helper.getReadableDatabase().rawQuery("SELECT * from " + TabelasBD.ALIMENTO_VIRTUAL+ " where id_refeicao=?;", args);
			while (cursor2.moveToNext()) {
				String[] args2 = {String.valueOf(cursor2.getInt(cursor2.getColumnIndex("id_alimento")))};
				System.out.println(args2[0]);
				Cursor cursor3 = helper.getReadableDatabase().rawQuery("SELECT * from " + TabelasBD.ALIMENTO_FISICO+ " where id=?;", args2);
				cursor3.moveToFirst();
				refeicao.adicionaAlimento(new AlimentoVirtual(new AlimentoFisico(cursor3.getString(1), cursor3.getDouble(2), cursor3.getString(3)), cursor2.getInt(2), refeicao));
				cursor3.close();
			}
			refeicoes.add(refeicao);
			cursor2.close();
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
	
	private ContentValues toContentValues(AlimentoVirtual alimento, int id) {
		ContentValues values = new ContentValues();
		values.put("id_refeicao", id);
		values.put("quantidade", alimento.getQuantidade());
		values.put("id_alimento", alimento.getAlimento().getId());
		return values;
	}
}
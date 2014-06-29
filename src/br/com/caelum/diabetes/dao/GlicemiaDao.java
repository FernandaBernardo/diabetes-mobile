package br.com.caelum.diabetes.dao;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import android.content.ContentValues;
import android.database.Cursor;
import br.com.caelum.diabetes.extras.TabelasBD;
import br.com.caelum.diabetes.extras.TipoRefeicao;
import br.com.caelum.diabetes.model.Glicemia;

public class GlicemiaDao {
	private static final String TABELA = TabelasBD.GLICEMIA;
	private static final String[] COLUNAS = {"id", "tipoRefeicao", "data", "valorGlicemia"};

	private DbHelper helper;

	public GlicemiaDao(DbHelper helper) {
		this.helper = helper;
	}
	
	public int salva(Glicemia glicemia) {
		ContentValues values = toContentValues(glicemia);
		return (int) helper.getWritableDatabase().insert(TABELA, null, values);
	}

	public void deletar(Glicemia glicemia) {
		String[] args = {String.valueOf(glicemia.getId())};
		helper.getWritableDatabase().delete(TABELA, "id=?", args);
	}

	public void atualiza(Glicemia glicemia) {
		String[] args = {String.valueOf(glicemia.getId())};
		ContentValues values = toContentValues(glicemia);
		helper.getWritableDatabase().update(TABELA, values, "id=?", args);
	}
	
	public List<Glicemia> getGlicemias() {
		ArrayList<Glicemia> glicemias = new ArrayList<Glicemia>();
		Cursor cursor = helper.getReadableDatabase().query(TABELA, COLUNAS, null, null, null, null, null);

		while(cursor.moveToNext()) {
			Glicemia glicemia = new Glicemia();
			glicemia.setId(cursor.getInt(0));
			glicemia.setTipoRefeicao(TipoRefeicao.fromString(cursor.getString(1)));
			glicemia.setData(new DateTime(cursor.getLong(2)));
			glicemia.setValorGlicemia(cursor.getInt(3));
			glicemias.add(glicemia);
		}
		cursor.close();

		return glicemias;
	}
	
	private ContentValues toContentValues(Glicemia glicemia) {
		ContentValues values = new ContentValues();
		values.put("tipoRefeicao", glicemia.getTipoRefeicao().getText());
		values.put("data", glicemia.getData().toDate().getTime());
		values.put("valorGlicemia", glicemia.getValorGlicemia());
		return values;
	}
}

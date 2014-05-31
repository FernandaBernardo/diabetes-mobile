package br.com.caelum.diabetes.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import br.com.caelum.diabetes.model.Alimento;

public class AlimentoDao extends SQLiteOpenHelper{

	private static final String DATABASE = "Diabetes";
	private static final String TABELA = "Alimento";
	private static final String[] COLUNAS= {"id", "nome", "carboidrato", "unidadeDeMedida"};

	public AlimentoDao(Context context) {
		super(context, DATABASE, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + TABELA + " " +
				"(id INTEGER PRIMARY KEY, " +
				"nome TEXT UNIQUE NOT NULL, " +
				"carboidrato DOUBLE, " +
				"unidadeDeMedida TEXT);");
		
		salva(new Alimento(1, "arroz branco", 14, "colher de sopa"), db);
		salva(new Alimento(2, "arroz integral", 10, "colher de sopa"), db);
		salva(new Alimento(3, "coca-cola", 22, "copo médio"), db);
		salva(new Alimento(4, "snickers", 31, "unidade"), db);
		salva(new Alimento(5, "leite", 11, "copo"), db);
		salva(new Alimento(6, "pão francês", 28, "unidade média"), db);
		salva(new Alimento(7, "pão de forma", 28, "fatia"), db);
		salva(new Alimento(8, "bolo", 26, "pedaço"), db);
		salva(new Alimento(9, "feijão", 14, "concha"), db);
		salva(new Alimento(10, "iogurte", 16, "unidade pequena"), db);
		salva(new Alimento(11, "chocolate", 12, "barra"), db);
		salva(new Alimento(12, "bala", 5, "unidade"), db);
		salva(new Alimento(13, "lasanha", 35, "fatia média"), db);
		salva(new Alimento(14, "pizza", 27, "pedaço"), db);
		salva(new Alimento(15, "suco de laranja", 28, "copo médio"), db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("drop table if exists " + TABELA + ";");	
	}
	

	public void salva(Alimento alimento, SQLiteDatabase db) {
		ContentValues values = toContentValues(alimento);
		db.insert(TABELA, null, values);
	}

	public void deletar(Alimento alimento) {
		String[] args = {String.valueOf(alimento.getId())};
		getWritableDatabase().delete(TABELA, "id=?", args);
	}

	public void atualiza(Alimento alimento) {
		String[] args = {String.valueOf(alimento.getId())};
		ContentValues values = toContentValues(alimento);
		getWritableDatabase().update(TABELA, values, "id=?", args);
	}
	
	public List<Alimento> getAlimentos() {
		ArrayList<Alimento> alimentos = new ArrayList<Alimento>();
		Cursor cursor = getReadableDatabase().query(TABELA, COLUNAS, null, null, null, null, null);

		while(cursor.moveToNext()) {
			Alimento task = new Alimento(cursor.getInt(0), cursor.getString(1), cursor.getDouble(2), cursor.getString(3));
			alimentos.add(task);
		}
		cursor.close();

		return alimentos;
	}
	
	private ContentValues toContentValues(Alimento alimento) {
		ContentValues values = new ContentValues();
		values.put("nome", alimento.getNome());
		values.put("carboidrato", alimento.getCarboidrato());
		values.put("unidadeDeMedida", alimento.getUnidadeDeMedida());
		return values;
	}

}

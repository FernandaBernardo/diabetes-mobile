package br.com.caelum.diabetes.dao;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import br.com.caelum.diabetes.extras.TabelasBD;
import br.com.caelum.diabetes.model.Paciente;

public class PacienteDao {
	private static final String TABELA = TabelasBD.PACIENTE;

	private DbHelper helper;

	public PacienteDao(DbHelper helper) {
		this.helper = helper;
	}
	
	public int salva(Paciente paciente) {
		ContentValues values = toContentValues(paciente);
		return (int) helper.getWritableDatabase().insert(TABELA, null, values);
	}

	public void deletar(Paciente paciente) {
		String[] args = {String.valueOf(paciente.getId())};
		helper.getWritableDatabase().delete(TABELA, "id=?", args);
	}

	public void atualiza(Paciente paciente) {
		String[] args = {String.valueOf(paciente.getId())};
		ContentValues values = toContentValues(paciente);
		helper.getWritableDatabase().update(TABELA, values, "id=?", args);
	}
	
	private ContentValues toContentValues(Paciente paciente) {
		ContentValues values = new ContentValues();
		values.put("nome", paciente.getNome());
		values.put("idade", paciente.getIdade());
		values.put("peso", paciente.getPeso());
		values.put("altura", paciente.getAltura());
		values.put("tipoDiabetes", paciente.getTipoDiabetes());
		return values;
	}
}

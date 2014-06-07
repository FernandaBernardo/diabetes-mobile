package br.com.caelum.diabetes.dao;

import android.content.ContentValues;
import android.database.Cursor;
import br.com.caelum.diabetes.extras.Extras;
import br.com.caelum.diabetes.extras.TabelasBD;
import br.com.caelum.diabetes.model.DadosMedicos;
import br.com.caelum.diabetes.model.Paciente;
import br.com.caelum.diabetes.model.TipoDadoMedico;

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
	
	public Paciente getPaciente() {
		Cursor cursor = helper.getReadableDatabase().rawQuery("SELECT * from " + TabelasBD.PACIENTE + ";", null);
		if(cursor.moveToNext()) {
			Paciente paciente = new Paciente();
			paciente.setId(cursor.getInt(0));
			paciente.setNome(cursor.getString(1));
			paciente.setIdade(cursor.getInt(2));
			paciente.setPeso(cursor.getDouble(3));
			paciente.setAltura(cursor.getDouble(4));
			paciente.setSexo(cursor.getString(5));
			paciente.setTipoDiabetes(cursor.getString(6));
			
			paciente.setInsulinaCorrecao(getDadosMedicos(paciente, Extras.CORRECAO));
			paciente.setInsulinaContinua(getDadosMedicos(paciente, Extras.CONTINUO));
			paciente.setGlicemiaAlvo(getDadosMedicos(paciente, Extras.GLICEMIA_ALVO));
			return paciente;
		}
		return null;
	}

	private DadosMedicos getDadosMedicos(Paciente paciente, String tipoDado) {
		DadosMedicos dadosCorrecao = new DadosMedicos();
		String[] args = {tipoDado};
		Cursor cursor = helper.getReadableDatabase().rawQuery("SELECT * from " + TabelasBD.DADOS_MEDICOS+ " where tipoDado=?;", args);
		if(cursor.moveToNext()) {
			dadosCorrecao.setId(cursor.getInt(0));
			dadosCorrecao.setCafeManha(cursor.getDouble(1));
			dadosCorrecao.setLancheManha(cursor.getDouble(2));
			dadosCorrecao.setAlmoco(cursor.getDouble(3));
			dadosCorrecao.setLancheTarde(cursor.getDouble(4));
			dadosCorrecao.setJantar(cursor.getDouble(5));
			dadosCorrecao.setCeia(cursor.getDouble(6));
			dadosCorrecao.setTipo(TipoDadoMedico.fromString(cursor.getString(7)));
			dadosCorrecao.setPaciente(paciente);
		}
		return dadosCorrecao;
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

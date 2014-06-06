package br.com.caelum.diabetes.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import br.com.caelum.diabetes.extras.TabelasBD;
import br.com.caelum.diabetes.model.DadosMedicos;
import br.com.caelum.diabetes.model.TipoDadoMedico;

public class DadosMedicosDao {
	private static final String TABELA = TabelasBD.DADOS_MEDICOS;

	private DbHelper helper;

	public DadosMedicosDao(DbHelper helper) {
		this.helper = helper;
	}
	
	public int salva(DadosMedicos dadosMedicos) {
		ContentValues values = toContentValues(dadosMedicos);
		return (int) helper.getWritableDatabase().insert(TABELA, null, values);
	}

	public void deletar(DadosMedicos dadosMedicos) {
		String[] args = {String.valueOf(dadosMedicos.getId())};
		helper.getWritableDatabase().delete(TABELA, "id=?", args);
	}

	public void atualiza(DadosMedicos dadosMedicos) {
		String[] args = {String.valueOf(dadosMedicos.getId())};
		ContentValues values = toContentValues(dadosMedicos);
		helper.getWritableDatabase().update(TABELA, values, "id=?", args);
	}
	
	public List<DadosMedicos> getDadosMedicos() {
		Cursor cursor = helper.getReadableDatabase().rawQuery("SELECT * from " + TABELA + ";", null);
		List<DadosMedicos> dados = new ArrayList();
		while(cursor.moveToNext()) {
			DadosMedicos dadosMedicos = new DadosMedicos();
			dadosMedicos.setId(cursor.getInt(0));
			dadosMedicos.setCafeManha(cursor.getDouble(1));
			dadosMedicos.setLancheManha(cursor.getDouble(2));
			dadosMedicos.setAlmoco(cursor.getDouble(3));
			dadosMedicos.setLancheTarde(cursor.getDouble(4));
			dadosMedicos.setJantar(cursor.getDouble(5));
			dadosMedicos.setCeia(cursor.getDouble(6));
			dadosMedicos.setTipo(TipoDadoMedico.fromString(cursor.getString(7)));
			dados.add(dadosMedicos);
		}
		return dados;
	}
	
	private ContentValues toContentValues(DadosMedicos dadosMedicos) {
		ContentValues values = new ContentValues();
		values.put("cafeDaManha", dadosMedicos.getCafeManha());
		values.put("lancheDaManha", dadosMedicos.getLancheManha());
		values.put("almoco", dadosMedicos.getAlmoco());
		values.put("lancheDaTarde", dadosMedicos.getLancheTarde());
		values.put("jantar", dadosMedicos.getJantar());
		values.put("ceia", dadosMedicos.getCeia());
		values.put("tipoDado", dadosMedicos.getTipo().getText());
		values.put("id_paciente", dadosMedicos.getPaciente().getId());
		return values;
	}

	public DadosMedicos getDadosMedicosCom(TipoDadoMedico glicemiaAlvo) {
		String[] args = {glicemiaAlvo.getText()};
		Cursor cursor = helper.getReadableDatabase().rawQuery("SELECT * from " + TABELA + " where tipoDado=?", args);
		if(cursor.moveToNext()) {
			DadosMedicos dadosMedicos = new DadosMedicos();
			dadosMedicos.setId(cursor.getInt(0));
			dadosMedicos.setCafeManha(cursor.getDouble(1));
			dadosMedicos.setLancheManha(cursor.getDouble(2));
			dadosMedicos.setAlmoco(cursor.getDouble(3));
			dadosMedicos.setLancheTarde(cursor.getDouble(4));
			dadosMedicos.setJantar(cursor.getDouble(5));
			dadosMedicos.setCeia(cursor.getDouble(6));
			dadosMedicos.setTipo(TipoDadoMedico.fromString(cursor.getString(7)));
			return dadosMedicos;
		}
		return null;
	}
}

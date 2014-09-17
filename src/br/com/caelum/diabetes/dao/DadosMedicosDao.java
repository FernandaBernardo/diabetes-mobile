package br.com.caelum.diabetes.dao;

import java.sql.SQLException;
import java.util.List;

import br.com.caelum.diabetes.model.DadosMedicos;
import br.com.caelum.diabetes.model.TipoDadoMedico;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

public class DadosMedicosDao {
	private RuntimeExceptionDao<DadosMedicos, Integer> dao;

	public DadosMedicosDao(DbHelper helper) {
		dao = helper.getSimpleDataDao(DadosMedicos.class);
	}
	
	public void salva(DadosMedicos dadosMedicos) throws SQLException {
		DadosMedicos dadosMedicosCom = getDadosMedicosCom(dadosMedicos.getTipo());
		if (dadosMedicosCom == null) {
			dao.create(dadosMedicos);
		} else {
			dadosMedicos.setId(dadosMedicosCom.getId());
			dao.update(dadosMedicos);
		}
	}

	public void deletar(DadosMedicos dadosMedicos) {
		dao.delete(dadosMedicos);
	}

	public void atualiza(DadosMedicos dadosMedicos) {
		dao.update(dadosMedicos);
	}
	
	public List<DadosMedicos> getDadosMedicos() throws SQLException {
		QueryBuilder<DadosMedicos,Integer> builder = dao.queryBuilder();
		PreparedQuery<DadosMedicos> prepare = builder.prepare();
		return dao.query(prepare);
	}
	
	public DadosMedicos getDadosMedicosCom(TipoDadoMedico glicemiaAlvo) throws SQLException {
		QueryBuilder<DadosMedicos,Integer> builder = dao.queryBuilder();
		builder.where().eq("tipoDado", glicemiaAlvo);
		PreparedQuery<DadosMedicos> prepare = builder.prepare();
		List<DadosMedicos> list = dao.query(prepare);
		return list.size() == 0 ? null : list.get(0);
	}
}

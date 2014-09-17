package br.com.caelum.diabetes.dao;

import java.sql.SQLException;
import java.util.List;

import br.com.caelum.diabetes.model.Paciente;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

public class PacienteDao {
	private RuntimeExceptionDao<Paciente, Integer> dao;

	public PacienteDao(DbHelper helper) {
		dao = helper.getSimpleDataDao(Paciente.class);
	}
	
	public void salva(Paciente paciente) {
		dao.create(paciente);
	}

	public void deletar(Paciente paciente) {
		dao.delete(paciente);
	}

	public void atualiza(Paciente paciente) {
		dao.update(paciente);
	}
	
	public Paciente getPaciente() throws SQLException {
		QueryBuilder<Paciente,Integer> builder = dao.queryBuilder();
		PreparedQuery<Paciente> prepare = builder.prepare();
		List<Paciente> list = dao.query(prepare);
		return list.size() == 0 ? null : list.get(0);
	}
}
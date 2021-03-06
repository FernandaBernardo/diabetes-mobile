package br.com.caelum.diabetes.dao;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import br.com.caelum.diabetes.model.AlimentoFisico;
import br.com.caelum.diabetes.model.AlimentoVirtual;
import br.com.caelum.diabetes.model.DadosMedicos;
import br.com.caelum.diabetes.model.Glicemia;
import br.com.caelum.diabetes.model.Lembrete;
import br.com.caelum.diabetes.model.Paciente;
import br.com.caelum.diabetes.model.Refeicao;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DbHelper extends OrmLiteSqliteOpenHelper{

	private static final String DATABASE = "Diabetes";
	
	AlimentoFisicoDao alimentoFisicoDao;

	public Context context;

	public DbHelper(Context context) {
		super(context, DATABASE, null, 1);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		try {
			TableUtils.createTable(connectionSource, Paciente.class);
			TableUtils.createTable(connectionSource, DadosMedicos.class);
			TableUtils.createTable(connectionSource, Refeicao.class);
			TableUtils.createTable(connectionSource, AlimentoVirtual.class);
			TableUtils.createTable(connectionSource, AlimentoFisico.class);
			TableUtils.createTable(connectionSource, Lembrete.class);
			TableUtils.createTable(connectionSource, Glicemia.class);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		insereAlimentoFisico(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
		try {
			TableUtils.dropTable(connectionSource, Paciente.class, true);
			onCreate(db, connectionSource);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public <T> RuntimeExceptionDao<T, Integer> getSimpleDataDao(Class<T> clazz) {
		return getRuntimeExceptionDao(clazz);
	}
	
	public void insereAlimentoFisico(SQLiteDatabase db) {
		alimentoFisicoDao = new AlimentoFisicoDao(this);
		
		alimentoFisicoDao.salva(new AlimentoFisico("arroz branco", 14, "colher de sopa"));
		alimentoFisicoDao.salva(new AlimentoFisico("arroz integral", 10, "colher de sopa"));
		alimentoFisicoDao.salva(new AlimentoFisico("coca-cola", 22, "copo médio"));
		alimentoFisicoDao.salva(new AlimentoFisico("snickers", 31, "unidade"));
		alimentoFisicoDao.salva(new AlimentoFisico("leite", 11, "copo"));
		alimentoFisicoDao.salva(new AlimentoFisico("pão francês", 28, "unidade média"));
		alimentoFisicoDao.salva(new AlimentoFisico("pão de forma", 28, "fatia"));
		alimentoFisicoDao.salva(new AlimentoFisico("bolo", 26, "pedaço"));
		alimentoFisicoDao.salva(new AlimentoFisico("feijão", 14, "concha"));
		alimentoFisicoDao.salva(new AlimentoFisico("iogurte", 16, "unidade pequena"));
		alimentoFisicoDao.salva(new AlimentoFisico("chocolate", 12, "barra"));
		alimentoFisicoDao.salva(new AlimentoFisico("bala", 5, "unidade"));
		alimentoFisicoDao.salva(new AlimentoFisico("lasanha", 35, "fatia média"));
		alimentoFisicoDao.salva(new AlimentoFisico("pizza", 27, "pedaço"));
		alimentoFisicoDao.salva(new AlimentoFisico("suco de laranja", 28, "copo médio"));
	}
}
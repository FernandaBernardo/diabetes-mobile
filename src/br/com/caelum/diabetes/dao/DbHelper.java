package br.com.caelum.diabetes.dao;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import br.com.caelum.diabetes.model.AlimentoFisico;
import br.com.caelum.diabetes.model.DadosMedicos;
import br.com.caelum.diabetes.model.Paciente;
import br.com.caelum.diabetes.model.Refeicao;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DbHelper extends OrmLiteSqliteOpenHelper{

	private static final String DATABASE = "Diabetes";
	
	AlimentoFisicoDao alimentoFisicoDao;

	public DbHelper(Context context) {
		super(context, DATABASE, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		try {
			TableUtils.createTable(connectionSource, Refeicao.class);
			TableUtils.createTable(connectionSource, Paciente.class);
			TableUtils.createTable(connectionSource, DadosMedicos.class);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
//		String alimentoFisico = "CREATE TABLE " + TabelasBD.ALIMENTO_FISICO + " " + 
//				"(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
//				"nome TEXT UNIQUE NOT NULL, " +
//				"carboidrato DOUBLE, " +
//				"unidadeDeMedida TEXT);";
//		
//		String alimentoVirtual = "CREATE TABLE " + TabelasBD.ALIMENTO_VIRTUAL + " " + 
//				"(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
//				"id_refeicao INTEGER, " +
//				"quantidade DOUBLE, " +
//				"id_alimento INTEGER," +
//				"FOREIGN KEY (id_alimento) references AlimentoFisico(id), " +
//				"FOREIGN KEY (id_refeicao) references Refeicao(id));";
//		
//		String glicemia = "CREATE TABLE " + TabelasBD.GLICEMIA + " " + 
//				"(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
//				"tipoRefeicao TEXT," + 
//				"data DATE," + 
//				"valorGlicemia INTEGER);";
//		
//		String lembrete = "CREATE TABLE " + TabelasBD.LEMBRETE + " " + 
//				"(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
//				"data DATE," + 
//				"atividade TEXT," + 
//				"anotacoes TEXT);";
//		
//		
//		db.execSQL(alimentoFisico);
//		db.execSQL(alimentoVirtual);
//		db.execSQL(glicemia);
//		db.execSQL(lembrete);
//		insereAlimentoFisico(db);
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
		
		alimentoFisicoDao.salvaFirst(new AlimentoFisico("arroz branco", 14, "colher de sopa"), db);
		alimentoFisicoDao.salvaFirst(new AlimentoFisico("arroz integral", 10, "colher de sopa"), db);
		alimentoFisicoDao.salvaFirst(new AlimentoFisico("coca-cola", 22, "copo médio"), db);
		alimentoFisicoDao.salvaFirst(new AlimentoFisico("snickers", 31, "unidade"), db);
		alimentoFisicoDao.salvaFirst(new AlimentoFisico("leite", 11, "copo"), db);
		alimentoFisicoDao.salvaFirst(new AlimentoFisico("pão francês", 28, "unidade média"), db);
		alimentoFisicoDao.salvaFirst(new AlimentoFisico("pão de forma", 28, "fatia"), db);
		alimentoFisicoDao.salvaFirst(new AlimentoFisico("bolo", 26, "pedaço"), db);
		alimentoFisicoDao.salvaFirst(new AlimentoFisico("feijão", 14, "concha"), db);
		alimentoFisicoDao.salvaFirst(new AlimentoFisico("iogurte", 16, "unidade pequena"), db);
		alimentoFisicoDao.salvaFirst(new AlimentoFisico("chocolate", 12, "barra"), db);
		alimentoFisicoDao.salvaFirst(new AlimentoFisico("bala", 5, "unidade"), db);
		alimentoFisicoDao.salvaFirst(new AlimentoFisico("lasanha", 35, "fatia média"), db);
		alimentoFisicoDao.salvaFirst(new AlimentoFisico("pizza", 27, "pedaço"), db);
		alimentoFisicoDao.salvaFirst(new AlimentoFisico("suco de laranja", 28, "copo médio"), db);
	}
}
package br.com.caelum.diabetes.dao;

import br.com.caelum.diabetes.extras.TabelasBD;
import br.com.caelum.diabetes.model.AlimentoFisico;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper{

	private static final String DATABASE = "Diabetes";
	
	AlimentoFisicoDao alimentoFisicoDao;

	public DbHelper(Context context) {
		super(context, DATABASE, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String refeicao = "CREATE TABLE " + TabelasBD.REFEICAO + " " + 
				"(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
				"tipoRefeicao TEXT);";
		
		String alimentoFisico = "CREATE TABLE " + TabelasBD.ALIMENTO_FISICO + " " + 
				"(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
				"nome TEXT UNIQUE NOT NULL, " +
				"carboidrato DOUBLE, " +
				"unidadeDeMedida TEXT);";
		
		String alimentoVirtual = "CREATE TABLE " + TabelasBD.ALIMENTO_VIRTUAL + " " + 
				"(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
				"id_refeicao INTEGER, " +
				"quantidade DOUBLE, " +
				"id_alimento INTEGER," +
				"FOREIGN KEY (id_alimento) references AlimentoFisico(id), " +
				"FOREIGN KEY (id_refeicao) references Refeicao(id));";
		
		String paciente = "CREATE TABLE " + TabelasBD.PACIENTE + " " + 
				"(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
				"nome TEXT, " +
				"idade INTEGER, " +
				"peso DOUBLE," +
				"altura DOUBLE," +
				"sexo TEXT," +
				"tipoDiabetes TEXT);";
		
		String dadosMedicos = "CREATE TABLE " + TabelasBD.DADOS_MEDICOS+ " " + 
				"(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
				"cafeDaManha DOUBLE, " +
				"lancheDaManha DOUBLE, " +
				"almoco DOUBLE, " +
				"lancheDaTarde DOUBLE, " +
				"jantar DOUBLE, " +
				"ceia DOUBLE, " +
				"tipoDado TEXT," +
				"id_paciente INTEGER," + 
				"FOREIGN KEY (id_paciente) references Paciente(id));";
		
		db.execSQL(refeicao);
		db.execSQL(alimentoFisico);
		db.execSQL(alimentoVirtual);
		db.execSQL(paciente);
		db.execSQL(dadosMedicos);
		insereAlimentoFisico(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}
	
	public void insereAlimentoFisico(SQLiteDatabase db) {
		alimentoFisicoDao = new AlimentoFisicoDao(this);
		
		alimentoFisicoDao.salvaFirst(new AlimentoFisico(1, "arroz branco", 14, "colher de sopa"), db);
		alimentoFisicoDao.salvaFirst(new AlimentoFisico(2, "arroz integral", 10, "colher de sopa"), db);
		alimentoFisicoDao.salvaFirst(new AlimentoFisico(3, "coca-cola", 22, "copo médio"), db);
		alimentoFisicoDao.salvaFirst(new AlimentoFisico(4, "snickers", 31, "unidade"), db);
		alimentoFisicoDao.salvaFirst(new AlimentoFisico(5, "leite", 11, "copo"), db);
		alimentoFisicoDao.salvaFirst(new AlimentoFisico(6, "pão francês", 28, "unidade média"), db);
		alimentoFisicoDao.salvaFirst(new AlimentoFisico(7, "pão de forma", 28, "fatia"), db);
		alimentoFisicoDao.salvaFirst(new AlimentoFisico(8, "bolo", 26, "pedaço"), db);
		alimentoFisicoDao.salvaFirst(new AlimentoFisico(9, "feijão", 14, "concha"), db);
		alimentoFisicoDao.salvaFirst(new AlimentoFisico(10, "iogurte", 16, "unidade pequena"), db);
		alimentoFisicoDao.salvaFirst(new AlimentoFisico(11, "chocolate", 12, "barra"), db);
		alimentoFisicoDao.salvaFirst(new AlimentoFisico(12, "bala", 5, "unidade"), db);
		alimentoFisicoDao.salvaFirst(new AlimentoFisico(13, "lasanha", 35, "fatia média"), db);
		alimentoFisicoDao.salvaFirst(new AlimentoFisico(14, "pizza", 27, "pedaço"), db);
		alimentoFisicoDao.salvaFirst(new AlimentoFisico(15, "suco de laranja", 28, "copo médio"), db);
	}
}

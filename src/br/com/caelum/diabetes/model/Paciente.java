package br.com.caelum.diabetes.model;

public class Paciente {
	private static Paciente instance = null;
	
	private int id;
	private String nome;
	private int idade;
	private double peso;
	private double altura;
	private String tipoDiabetes;
	
	private Paciente() {
	}
	
	public static Paciente getinstance() {
		if (instance == null) {
			instance = new Paciente();
		}
		return instance;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	public double getAltura() {
		return altura;
	}
	public void setAltura(double altura) {
		this.altura = altura;
	}
	public String getTipoDiabetes() {
		return tipoDiabetes;
	}
	public void setTipoDiabetes(String tipoDiabetes) {
		this.tipoDiabetes = tipoDiabetes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}

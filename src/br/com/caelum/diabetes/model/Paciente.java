package br.com.caelum.diabetes.model;

public class Paciente {
	
	private int id;
	private String nome;
	private int idade;
	private double peso;
	private double altura;
	private String sexo;
	private String tipoDiabetes;
	
	private DadosMedicos insulinaContinua;
	private DadosMedicos insulinaCorrecao;
	private DadosMedicos glicemiaAlvo;
	
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

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public DadosMedicos getInsulinaContinua() {
		return insulinaContinua;
	}
	public void setInsulinaContinua(DadosMedicos insulinaContinua) {
		this.insulinaContinua = insulinaContinua;
	}
	public DadosMedicos getInsulinaCorrecao() {
		return insulinaCorrecao;
	}
	public void setInsulinaCorrecao(DadosMedicos insulinaCorrecao) {
		this.insulinaCorrecao = insulinaCorrecao;
	}
	public DadosMedicos getGlicemiaAlvo() {
		return glicemiaAlvo;
	}
	public void setGlicemiaAlvo(DadosMedicos glicemiaAlvo) {
		this.glicemiaAlvo = glicemiaAlvo;
	}
}

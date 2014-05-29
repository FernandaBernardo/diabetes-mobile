package br.com.caelum.diabetes.model;

public class Alimento {
	private Long id;
	private String nome;
	private double carboidrato;
	
	public Alimento(Long id, String nome, double carboidrato) {
		this.id = id;
		this.nome = nome;
		this.carboidrato = carboidrato;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getCarboidrato() {
		return carboidrato;
	}
	public void setCarboidrato(double carboidrato) {
		this.carboidrato = carboidrato;
	}

	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return nome;
	}
}

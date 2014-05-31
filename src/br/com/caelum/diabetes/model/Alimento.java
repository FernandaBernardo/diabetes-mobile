package br.com.caelum.diabetes.model;

import java.io.Serializable;

public class Alimento implements Serializable{
	private Long id;
	private String nome;
	private double carboidrato;
	private String unidadeDeMedida;
	
	public Alimento(Long id, String nome, double carboidrato, String unidadeDeMedida) {
		this.id = id;
		this.nome = nome;
		this.carboidrato = carboidrato;
		this.unidadeDeMedida = unidadeDeMedida;
	}
	
	public double getCarboidratoPorValor(double valor) {
		return valor*carboidrato;
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

	public String getUnidadeDeMedida() {
		return unidadeDeMedida;
	}

	public void setUnidadeDeMedida(String unidadeDeMedida) {
		this.unidadeDeMedida = unidadeDeMedida;
	}
}

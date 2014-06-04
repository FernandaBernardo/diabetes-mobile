package br.com.caelum.diabetes.model;

import java.io.Serializable;

public class AlimentoFisico implements Serializable{
	private int id;
	private String nome;
	private double carboidrato;
	private String unidadeDeMedida;
	
	public AlimentoFisico(int id, String nome, double carboidrato, String unidadeDeMedida) {
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

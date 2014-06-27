package br.com.caelum.diabetes.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class AlimentoVirtual implements Serializable{
	private int id;
	private Refeicao refeicao;
	private double quantidade;
	private AlimentoFisico alimento;
	
	public AlimentoVirtual(AlimentoFisico alimento, double quantidade, Refeicao refeicao) {
		this.quantidade = quantidade;
		this.alimento = alimento;
		this.refeicao = refeicao;
	}
	
	public double getTotalCarboidrato() {
		return alimento.getCarboidrato() * quantidade;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Refeicao getRefeicao() {
		return refeicao;
	}

	public void setRefeicao(Refeicao refeicao) {
		this.refeicao = refeicao;
	}

	public double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}

	public AlimentoFisico getAlimento() {
		return alimento;
	}

	public void setAlimento(AlimentoFisico alimento) {
		this.alimento = alimento;
	}
	
	@Override
	public String toString() {
		return alimento.getNome();
	}
}

package br.com.caelum.diabetes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.caelum.diabetes.model.Alimento;

public class Refeicao implements Serializable{
	private List<Alimento> alimentos;
	private double totalCHO;
	
	public Refeicao() {
		alimentos = new ArrayList<Alimento>();
	}
	
	public void adicionaAlimento(Alimento alimento) {
		alimentos.add(alimento);
	}
	
	public List<Alimento> getAlimentos() {
		return alimentos;
	}

	public void setAlimentos(List<Alimento> alimentos) {
		this.alimentos = alimentos;
	}

	public double getTotalCHO() {
		totalCHO = 0;
		for (Alimento alimento : alimentos) {
			totalCHO += alimento.getCarboidrato();
		}
		return totalCHO;
	}
}

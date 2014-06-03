package br.com.caelum.diabetes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.caelum.diabetes.model.AlimentoFisico;

public class Refeicao implements Serializable{
	private List<AlimentoFisico> alimentos;
	private double totalCHO;
	private TipoRefeicao tipoRefeicao; 
	
	public Refeicao(TipoRefeicao tipoRefeicao) {
		this.tipoRefeicao = tipoRefeicao;
		alimentos = new ArrayList<AlimentoFisico>();
	}
	
	public void adicionaAlimento(AlimentoFisico alimento) {
		alimentos.add(alimento);
	}
	
	public List<AlimentoFisico> getAlimentos() {
		return alimentos;
	}

	public void setAlimentos(List<AlimentoFisico> alimentos) {
		this.alimentos = alimentos;
	}

	public double getTotalCHO() {
		totalCHO = 0;
		for (AlimentoFisico alimento : alimentos) {
			totalCHO += alimento.getCarboidrato();
		}
		return totalCHO;
	}
}

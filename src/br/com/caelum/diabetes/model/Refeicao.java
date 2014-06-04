package br.com.caelum.diabetes.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.caelum.diabetes.extras.TipoRefeicao;

public class Refeicao implements Serializable{
	private int id;
	private List<AlimentoVirtual> alimentos;
	private TipoRefeicao tipoRefeicao; 
	
	public Refeicao(TipoRefeicao tipoRefeicao) {
		this.setTipoRefeicao(tipoRefeicao);
		alimentos = new ArrayList<AlimentoVirtual>();
	}
	
	public void adicionaAlimento(AlimentoVirtual alimento) {
		alimentos.add(alimento);
	}
	
	public List<AlimentoVirtual> getAlimentos() {
		return alimentos;
	}

	public void setAlimentos(List<AlimentoVirtual> alimentos) {
		this.alimentos = alimentos;
	}

	public double getTotalCHO() {
		double totalCHO = 0;
		for (AlimentoVirtual alimento : alimentos) {
			totalCHO += alimento.getTotalCarboidrato();
		}
		return totalCHO;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TipoRefeicao getTipoRefeicao() {
		return tipoRefeicao;
	}

	public void setTipoRefeicao(TipoRefeicao tipoRefeicao) {
		this.tipoRefeicao = tipoRefeicao;
	}
}

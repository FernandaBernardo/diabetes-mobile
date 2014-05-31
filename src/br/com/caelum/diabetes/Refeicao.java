package br.com.caelum.diabetes;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.diabetes.model.Alimento;

public class Refeicao {
	private List<Alimento> alimentos;
	
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
}

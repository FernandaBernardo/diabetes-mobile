package br.com.caelum.diabetes;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.diabetes.model.Alimento;

public class ListaAlimentos {
	
	List<Alimento> alimentos = new ArrayList<Alimento>();

	public ListaAlimentos() {
		populaAlimentos();
	}
	
	void populaAlimentos() {
		alimentos.add(new Alimento(1L, "arroz branco", 14));
		alimentos.add(new Alimento(2L, "arroz integral", 10));
		alimentos.add(new Alimento(3L, "coca-cola", 22));
		alimentos.add(new Alimento(4L, "snickers", 31));
		alimentos.add(new Alimento(5L, "leite", 11));
		alimentos.add(new Alimento(6L, "pão francês", 28));
		alimentos.add(new Alimento(7L, "pão de forma", 28));
		alimentos.add(new Alimento(8L, "bolo", 26));
		alimentos.add(new Alimento(9L, "feijão", 14));
		alimentos.add(new Alimento(10L, "iogurte", 16));
	}
	
	List<Alimento> getAlimentos(String nome) {
		List<Alimento> alimentosBuscados = new ArrayList<Alimento>();
		for (Alimento alimento : alimentos) {
			if((alimento.getNome()).contains(nome)) alimentosBuscados.add(alimento);
		}
		return alimentosBuscados;
	}
}

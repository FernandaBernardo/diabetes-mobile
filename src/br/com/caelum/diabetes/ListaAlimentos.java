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
		alimentos.add(new Alimento(11L, "chocolate", 12));
		alimentos.add(new Alimento(12L, "bala", 5));
		alimentos.add(new Alimento(13L, "lasanha", 35));
		alimentos.add(new Alimento(14L, "pizza", 27));
		alimentos.add(new Alimento(15L, "suco de laranja", 28));
	}
	
	List<Alimento> getAlimentos() {
		return alimentos;
	}
}

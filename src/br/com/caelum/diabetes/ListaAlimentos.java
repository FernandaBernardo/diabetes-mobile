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
		alimentos.add(new Alimento(1L, "arroz branco", 14, "colher de sopa"));
		alimentos.add(new Alimento(2L, "arroz integral", 10, "colher de sopa"));
		alimentos.add(new Alimento(3L, "coca-cola", 22, "copo médio"));
		alimentos.add(new Alimento(4L, "snickers", 31, "unidade"));
		alimentos.add(new Alimento(5L, "leite", 11, "copo"));
		alimentos.add(new Alimento(6L, "pão francês", 28, "unidade média"));
		alimentos.add(new Alimento(7L, "pão de forma", 28, "fatia"));
		alimentos.add(new Alimento(8L, "bolo", 26, "pedaço"));
		alimentos.add(new Alimento(9L, "feijão", 14, "concha"));
		alimentos.add(new Alimento(10L, "iogurte", 16, "unidade pequena"));
		alimentos.add(new Alimento(11L, "chocolate", 12, "barra"));
		alimentos.add(new Alimento(12L, "bala", 5, "unidade"));
		alimentos.add(new Alimento(13L, "lasanha", 35, "fatia média"));
		alimentos.add(new Alimento(14L, "pizza", 27, "pedaço"));
		alimentos.add(new Alimento(15L, "suco de laranja", 28, "copo médio"));
	}
	
	List<Alimento> getAlimentos() {
		return alimentos;
	}
}

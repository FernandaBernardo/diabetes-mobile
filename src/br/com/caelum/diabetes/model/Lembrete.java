package br.com.caelum.diabetes.model;

import java.io.Serializable;

import org.joda.time.DateTime;

public class Lembrete implements Serializable {
	int id;
	DateTime data;
	String atividade;
	String anotacoes;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public DateTime getData() {
		return data;
	}
	public void setData(DateTime data) {
		this.data = data;
	}
	public String getAtividade() {
		return atividade;
	}
	public void setAtividade(String atividade) {
		this.atividade = atividade;
	}
	public String getAnotacoes() {
		return anotacoes;
	}
	public void setAnotacoes(String anotacoes) {
		this.anotacoes = anotacoes;
	}
}
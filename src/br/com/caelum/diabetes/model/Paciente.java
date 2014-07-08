package br.com.caelum.diabetes.model;

import java.io.Serializable;

import static com.google.common.base.Preconditions.*;

@SuppressWarnings("serial")
public class Paciente implements Serializable{
	
	private int id;
	private String nome;
	private Integer idade;
	private Double peso;
	private Double altura;
	private String sexo;
	private String tipoDiabetes;
	
	private DadosMedicos insulinaContinua;
	private DadosMedicos insulinaCorrecao;
	private DadosMedicos glicemiaAlvo;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getIdade() {
		
		return idade;
	}
	public void setIdade(Integer idade) {
		checkNotNull(idade);
		this.idade = idade;
	}
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	public Double getAltura() {
		return altura;
	}
	public void setAltura(Double altura) {
		this.altura = altura;
	}
	public String getTipoDiabetes() {
		return tipoDiabetes;
	}
	public void setTipoDiabetes(String tipoDiabetes) {
		this.tipoDiabetes = tipoDiabetes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public DadosMedicos getInsulinaContinua() {
		return insulinaContinua;
	}
	public void setInsulinaContinua(DadosMedicos insulinaContinua) {
		this.insulinaContinua = insulinaContinua;
	}
	public DadosMedicos getInsulinaCorrecao() {
		return insulinaCorrecao;
	}
	public void setInsulinaCorrecao(DadosMedicos insulinaCorrecao) {
		this.insulinaCorrecao = insulinaCorrecao;
	}
	public DadosMedicos getGlicemiaAlvo() {
		return glicemiaAlvo;
	}
	public void setGlicemiaAlvo(DadosMedicos glicemiaAlvo) {
		this.glicemiaAlvo = glicemiaAlvo;
	}
	
	public boolean hasMedicalInfo(){
		
		if (! (this.getGlicemiaAlvo() == null)){
			if (this.getGlicemiaAlvo().isEmpty()){
				return false;
			}
		}
		
		if (! (this.getInsulinaContinua() == null)){
			if (this.getInsulinaContinua().isEmpty()){
				return false;
			}
		}
		
		if (! (this.getInsulinaCorrecao() == null)){
			if (this.getInsulinaCorrecao().isEmpty()){
				return false;
			}
		}
		
		return !(this.getAltura() == null || 
				this.getGlicemiaAlvo() == null ||
				this.getInsulinaContinua() == null ||
				this.getInsulinaCorrecao() == null ||
				this.getPeso() == null ||
				this.getTipoDiabetes() == null ||
				this.getIdade() == null ||
				this.getSexo() == null
				); 
		
	}
}

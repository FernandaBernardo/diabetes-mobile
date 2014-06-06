package br.com.caelum.diabetes.model;

public class DadosMedicos {
	private int id;
	private double cafeManha;
	private double lancheManha;
	private double almoco;
	private double lancheTarde;
	private double jantar;
	private double ceia;
	
	private TipoDadoMedico tipo;
	private Paciente paciente;
	
	public DadosMedicos() {
	}
	
	public DadosMedicos(TipoDadoMedico tipo) {
		this.tipo = tipo;
	}
		
	public double getCafeManha() {
		return cafeManha;
	}
	public void setCafeManha(double cafeManha) {
		this.cafeManha = cafeManha;
	}
	public double getLancheManha() {
		return lancheManha;
	}
	public void setLancheManha(double lancheManha) {
		this.lancheManha = lancheManha;
	}
	public double getAlmoco() {
		return almoco;
	}
	public void setAlmoco(double almoco) {
		this.almoco = almoco;
	}
	public double getLancheTarde() {
		return lancheTarde;
	}
	public void setLancheTarde(double lancheTarde) {
		this.lancheTarde = lancheTarde;
	}
	public double getJantar() {
		return jantar;
	}
	public void setJantar(double jantar) {
		this.jantar = jantar;
	}
	public double getCeia() {
		return ceia;
	}
	public void setCeia(double ceia) {
		this.ceia = ceia;
	}
	public TipoDadoMedico getTipo() {
		return tipo;
	}
	public void setTipo(TipoDadoMedico tipo) {
		this.tipo = tipo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
}


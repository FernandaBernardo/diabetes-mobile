package br.com.caelum.diabetes;

public enum TipoRefeicao{
	CAFE_DA_MANHA(Extras.cafeDaManha), 
	LANCHE_DA_MANHA(Extras.lancheDaManha),
	ALMOCO(Extras.almoco),
	LANCHE_DA_TARDE(Extras.lancheDaTarde),
	JANTAR(Extras.jantar),
	CEIA(Extras.ceia);
	
	private String text;

	private TipoRefeicao(String text) {
		this.text = text;
	}

	public String getText() {
		return this.text;
	}

	public static TipoRefeicao fromString(String text) {
		if (text != null) {
			for (TipoRefeicao b : TipoRefeicao.values()) {
				if (text.equalsIgnoreCase(b.text)) return b;
			}
		}
		return null;
	}
}


package br.com.caelum.diabetes.extras;


public enum UnidadeMedidaAlimento{
	COLHER_DE_SOPA(Extras.COLHER_DE_SOPA), 
	COLHER_DE_CHA(Extras.COLHER_DE_CHA),
	CONCHA_MEDIA(Extras.CONCHA_MEDIA),
	FATIA_MEDIA(Extras.FATIA_MEDIA),
	UNIDADE_MEDIA(Extras.UNIDADE_MEDIA),
	UNIDADE_PEQUENA(Extras.UNIDADE_PEQUENA),
	ESCUMADEIRA(Extras.ESCUMADEIRA),
	XICARA(Extras.XICARA),
	COPO(Extras.COPO);
	
	private String text;

	private UnidadeMedidaAlimento(String text) {
		this.text = text;
	}

	public String getText() {
		return this.text;
	}

	public static UnidadeMedidaAlimento fromString(String text) {
		if (text != null) {
			for (UnidadeMedidaAlimento b : UnidadeMedidaAlimento.values()) {
				if (text.equalsIgnoreCase(b.text)) return b;
			}
		}
		return null;
	}
}


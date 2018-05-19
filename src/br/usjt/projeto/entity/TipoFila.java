package br.usjt.projeto.entity;

public enum TipoFila {
	
	NORMAL("normal", "N"),
	PREFERENCIAL("preferencial", "P");
	
	private String fila;
	private String sigla;
	
	private TipoFila(String fila, String sigla) {
		this.fila = fila;
		this.sigla = sigla;
	}
	
	public String descricao() {
		return fila;
	}
	
	public String sigla() {
		return sigla;
	}

}

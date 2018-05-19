package br.usjt.projeto.entity;

public enum TipoStatus {
	
	ABERTO("aberto"),
	EM_ANDAMENTO("Em andamento"),
	FINALIZADO("finalizado");
	
	private String status;
	
	private TipoStatus(String status) {
		this.status = status;
	}
	
	public String status() {
		return status;
	}

}

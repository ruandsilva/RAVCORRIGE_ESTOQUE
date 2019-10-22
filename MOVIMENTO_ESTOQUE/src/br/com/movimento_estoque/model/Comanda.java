package br.com.movimento_estoque.model;

public class Comanda {
	
	private Long controle;
	private Long sequencia;
	
	public Comanda() {
		
	}

	public long getControle() {
		return controle;
	}

	public void setControle(long controle) {
		this.controle = controle;
	}

	public long getSequencia() {
		return sequencia;
	}

	public void setSequencia(long sequencia) {
		this.sequencia = sequencia;
	}

}

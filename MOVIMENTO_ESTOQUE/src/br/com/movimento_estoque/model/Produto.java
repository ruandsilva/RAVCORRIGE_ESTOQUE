package br.com.movimento_estoque.model;

public class Produto {
	
	private Long num_cotrole;
	private Long codigo;
	private Long nquant;
	private Long quant;
	private String operacao;
	private Long cancela;
	private Float preco;
	private String de;
	private String ate;
	
	public Produto() {
		
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Long getNquant() {
		return nquant;
	}

	public void setNquant(Long nquant) {
		this.nquant = nquant;
	}

	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}

	public Long getCancela() {
		return cancela;
	}

	public void setCancela(Long cancela) {
		this.cancela = cancela;
	}

	public Long getNum_cotrole() {
		return num_cotrole;
	}

	public void setNum_cotrole(Long num_cotrole) {
		this.num_cotrole = num_cotrole;
	}

	public Float getPreco() {
		return preco;
	}

	public void setPreco(Float preco) {
		this.preco = preco;
	}

	public Long getQuant() {
		return quant;
	}

	public void setQuant(Long quant) {
		this.quant = quant;
	}

	public String getDe() {
		return de;
	}

	public void setDe(String de) {
		this.de = de;
	}

	public String getAte() {
		return ate;
	}

	public void setAte(String ate) {
		this.ate = ate;
	}
}

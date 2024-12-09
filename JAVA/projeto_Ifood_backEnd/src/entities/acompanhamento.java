package entities;

import java.time.LocalDateTime;

public class acompanhamento extends base {
	private double valor;
	private String descricao;

	public acompanhamento() {
		super();
	}

	public acompanhamento(int status, LocalDateTime dataCriacao, String titulo, double valor) {
		super(status, dataCriacao, titulo);
		this.valor = valor;
	}

	public acompanhamento(int id, int status, LocalDateTime dateCriacao, String titulo, double valor,
			String descricao) {
		super(id, status, dateCriacao, titulo);
		this.valor = valor;
		this.descricao = descricao;
	}

	public double getValor() {
		valor = Math.round(valor * 100) / 100;
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return titulo + "- R$ " + valor;
	}

}

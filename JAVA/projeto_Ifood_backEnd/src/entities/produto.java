package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class produto extends base {

	private String descricao;
	private double preco;
	private categoria categoria;
	private restaurante restaurante;
	private List<acompanhamento> ListaAcompanhamento = new ArrayList<acompanhamento>();

	public produto() {
		super();
	}

	public produto(int status, LocalDateTime dataCriacao, String titulo, double preco) {
		super(status, dataCriacao, titulo);
		this.preco = preco;
	}

	public produto(int status, LocalDateTime dataCriacao, String titulo, String descricao, double preco,
			entities.categoria categoria) {
		super(status, dataCriacao, titulo);
		this.descricao = descricao;
		this.preco = preco;
		this.categoria = categoria;
	}

	public produto(int id, int status, LocalDateTime dateCriacao, String titulo, String descricao, double preco,
			entities.categoria categoria, entities.restaurante restaurante, List<acompanhamento> listaAcompanhamento) {
		super(id, status, dateCriacao, titulo);
		this.descricao = descricao;
		this.preco = preco;
		this.categoria = categoria;
		this.restaurante = restaurante;
		ListaAcompanhamento = listaAcompanhamento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getPreco() {
		preco = Math.round(preco * 100) / 100;
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	@Override
	public String toString() {
		return titulo + "- R$ " + String.format("%.2f", preco);
	}

}

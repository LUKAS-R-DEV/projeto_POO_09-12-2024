package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class categoria extends base {
	private String tipo;
	private String descricao;

	public categoria(int idCategoria, String tipo, String descricao) {
		super();
		this.tipo = tipo;
		this.descricao = descricao;
	}

	public categoria() {
		super();
	}

	public categoria(String tipo, String descricao) {
		super();
		this.tipo = tipo;
		this.descricao = descricao;
	}

	public categoria(int id, int status, LocalDateTime dateCriacao, String titulo, String tipo, String descricao,
			List<restaurante> listaRestaurantes) {
		super(id, status, dateCriacao, titulo);
		this.tipo = tipo;
		this.descricao = descricao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}

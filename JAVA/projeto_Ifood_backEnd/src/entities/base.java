package entities;

import java.time.LocalDateTime;

public abstract class base {
	public static final int DESATIVADO = 0;
	public static final int ATIVO = 1;
	public static final int REMOVIDO = 3;
	protected int id;
	protected int status = ATIVO;
	protected LocalDateTime dataCriacao;
	protected String titulo;

	public base() {
		this.dataCriacao = LocalDateTime.now();
	}

	public base(int id, String titulo) {
		super();
		this.id = id;
		this.titulo = titulo;
	}

	public base(int status, LocalDateTime dataCriacao, String titulo) {
		super();
		this.status = status;
		this.dataCriacao = dataCriacao;
		this.titulo = titulo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public boolean isAtivo() {
		return this.status == ATIVO;
	}

	public base(int id, int status, LocalDateTime dateCriacao, String titulo) {
		this.id = id;
		this.status = status;
		this.dataCriacao = dataCriacao;
		this.titulo = titulo;
	}

}

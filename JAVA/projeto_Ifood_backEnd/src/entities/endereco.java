package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class endereco extends base {
	protected String numero;
	protected String complemento;
	protected String bairro;
	protected String cidade;
	protected String estado;
	protected String cep;
	private List<restaurante> listaRestaurantes = new ArrayList<restaurante>();

	public endereco(int idEndereco, String nome, String numero, String rua, String bairro, String cidade, String estado,
			String cep) {
		super();
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.cep = cep;

	}

	public endereco() {
		super();
	}

	public endereco(int status, LocalDateTime dataCriacao, String titulo, String numero, String complemento,
			String bairro, String cidade, String estado, String cep) {
		super(status, dataCriacao, titulo);
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.cep = cep;
	}

	public endereco(int id, int status, LocalDateTime dateCriacao, String titulo, String numero, String complemento,
			String bairro, String cidade, String estado, String cep, List<restaurante> listaRestaurantes) {
		super(id, status, dateCriacao, titulo);
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.cep = cep;
		this.listaRestaurantes = listaRestaurantes;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	@Override
	public String toString() {
		return "Bairro:" + bairro + "\nCidade:" + cidade + "\nEstado:" + estado;
	}

}

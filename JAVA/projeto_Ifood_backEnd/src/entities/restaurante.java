package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class restaurante extends base {
	private String horarioFuncionamento;
	private boolean aceitaRetirada;
	private String telefone;
	private int categoria;
	private int endereco;
	private List<produto> listaProdutos = new ArrayList<produto>();
	private List<acompanhamento> listaAcompanhamentos = new ArrayList<acompanhamento>();

	public restaurante() {
		super();
	}

	public restaurante(int status, LocalDateTime dataCriacao, String titulo, int categoria, int endereco) {
		super(status, dataCriacao, titulo);
		this.categoria = categoria;
		this.endereco = endereco;
	}

	public restaurante(String titulo, List<produto> listaProdutos, List<acompanhamento> listaAcompanhamentos) {
		this.titulo = titulo;
		this.listaProdutos = listaProdutos;
		this.listaAcompanhamentos = listaAcompanhamentos;
	}

	public restaurante(int id, int status, LocalDateTime dateCriacao, String titulo, String horarioFuncionamento,
			boolean aceitaRetirada, String telefone, int categoria, int endereco, List<produto> listaProdutos,
			List<acompanhamento> listaAcompanhamentos) {
		super(id, status, dateCriacao, titulo);
		this.horarioFuncionamento = horarioFuncionamento;
		this.aceitaRetirada = aceitaRetirada;
		this.telefone = telefone;
		this.categoria = categoria;
		this.endereco = endereco;
		this.listaProdutos = listaProdutos;
		this.listaAcompanhamentos = listaAcompanhamentos;
	}

	public restaurante(String nome, String horario, boolean aberto, String telefone, entities.categoria categoria,
			entities.endereco endereco) {
	}

	public restaurante(int status, LocalDateTime now, String nome, String horario, boolean aceitaRetirada,
			String telefone, int idCategoria, int idEndereco) {

	}

	public String getHorarioFuncionamento() {
		return horarioFuncionamento;
	}

	public void setHorarioFuncionamento(String horarioFuncionamento) {
		this.horarioFuncionamento = horarioFuncionamento;
	}

	public boolean isAceitaRetirada() {
		return aceitaRetirada;
	}

	public void setAceitaRetirada(boolean aceitaRetirada) {
		this.aceitaRetirada = aceitaRetirada;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public void adicionarProduto(produto p) {
		this.listaProdutos.add(p);
	}

	public void adicionarAcompanhamento(acompanhamento a) {
		this.listaAcompanhamentos.add(a);
	}

	@Override
	public String toString() {
		return titulo;
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}

	public List<produto> getListaProdutos() {
		return listaProdutos;
	}

	public void setListaProdutos(List<produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}

	public List<acompanhamento> getListaAcompanhamentos() {
		return listaAcompanhamentos;
	}

	public void setListaAcompanhamentos(List<acompanhamento> listaAcompanhamentos) {
		this.listaAcompanhamentos = listaAcompanhamentos;
	}

}

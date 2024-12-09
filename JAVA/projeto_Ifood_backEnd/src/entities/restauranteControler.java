package entities;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class restauranteControler {
	List<produto> listaProdutos = new ArrayList<produto>();
	restaurante restaur = new restaurante();
	List<acompanhamento> listaAcompanhamentos = new ArrayList<acompanhamento>();
	List<restaurante> listaRestaurantes = new ArrayList<restaurante>();
	int idRestaurante = -1;

	protected carrinho carrinho = new carrinho();
	conexaoBD conexao = new conexaoBD();

	public restauranteControler() {
		carregarRestaurante();
	}

	public void carregarRestaurante() {
		listaRestaurantes = conexao.buscarRestaurantes();
	}

	public void carregarProdutos(int idRestaurante) {
		listaProdutos = conexao.buscarProdutos(idRestaurante);
	}

	public void carregarAcompanhamentos(int idRestaurante) {
		listaAcompanhamentos = conexao.buscarAcompanhamentos(idRestaurante);
	}

	public List<acompanhamento> getListaAcompanhamentos() {
		return listaAcompanhamentos;
	}

	public List<restaurante> getListaRestaurantes() {
		return listaRestaurantes;
	}

	public void setListaRestaurantes(List<restaurante> listaRestaurantes) {
		this.listaRestaurantes = listaRestaurantes;

	}

	public List<produto> getListaProdutos() {
		return listaProdutos;
	}

	public void realizarPedido(carrinho carrinho) {
		if (carrinho.getListaCarrinho().size() > 0) {
			JOptionPane.showMessageDialog(null, "Finalize o pedido no carrinho por favor!");
			return;
		}
		String[] restauranteArray = listaRestaurantes.stream().map(restaurante::getTitulo).toArray(String[]::new);
		String restauranteEscolhido = (String) JOptionPane.showInputDialog(null, "Escolha o Restaurante",
				"Seleção de restaurante", JOptionPane.QUESTION_MESSAGE, null, restauranteArray, restauranteArray[0]);
		if (restauranteEscolhido == null) {
			JOptionPane.showMessageDialog(null, "Nenhum restaurante escolhido!,Pedido cancelado!");
			return;
		}
		restaurante restauranteSelecionado = null;
		for (restaurante r : listaRestaurantes) {
			if (r.getTitulo().equals(restauranteEscolhido)) {
				restauranteSelecionado = r;
				restaur.setId(r.getId());

			}

		}
		carrinho.setRestaurante(restauranteSelecionado);
		if (restauranteSelecionado == null) {
			JOptionPane.showMessageDialog(null, "Restaurante não encontrado!");
			return;
		}
		boolean prosseguir = true;
		do {
			List<produto> produtosDisponiveis = restauranteSelecionado.getListaProdutos();
			JComboBox<produto> comboBox = new JComboBox<produto>(produtosDisponiveis.toArray(new produto[0]));

			produto produtoEscolhido = (produto) JOptionPane.showInputDialog(null, "Escolha o produto",
					"Selecione o produto", JOptionPane.QUESTION_MESSAGE, null, produtosDisponiveis.toArray(),
					produtosDisponiveis.get(0));

			if (produtoEscolhido != null) {
				carrinho.adicionarProduto(produtoEscolhido);
				JOptionPane.showMessageDialog(null, "Produto selecionado com sucesso!");
			} else {
				JOptionPane.showMessageDialog(null, "Produto não selecionado");
			}

			int escolha = JOptionPane.showConfirmDialog(null, "Deseja acrescentar outro produto?", null,
					JOptionPane.YES_NO_OPTION);
			if (escolha == JOptionPane.NO_OPTION) {
				prosseguir = false;
			}
		} while (prosseguir == true);

		int opcao = JOptionPane.showConfirmDialog(null, "Deseja Solicitar Acompanhamentos?",
				"Adicionar acompanhamento?", JOptionPane.YES_NO_OPTION);
		if (opcao == JOptionPane.NO_OPTION) {
			carrinho.resumoCarrinho();
			carrinho.pagamento();
			return;
		}
		prosseguir = true;
		do {
			List<acompanhamento> acompanhamentosDisponiveis = restauranteSelecionado.getListaAcompanhamentos();
			JComboBox<acompanhamento> comboBoxAcompanhamento = new JComboBox<acompanhamento>(
					acompanhamentosDisponiveis.toArray(new acompanhamento[0]));

			acompanhamento acompanhamentoEscolhido = (acompanhamento) JOptionPane.showInputDialog(null,
					"Escolha o acompanhamento", "Selecione o acompanhamento", JOptionPane.QUESTION_MESSAGE, null,
					acompanhamentosDisponiveis.toArray(), acompanhamentosDisponiveis.get(0));

			if (acompanhamentoEscolhido != null) {
				carrinho.adicionarAcompanhamento(acompanhamentoEscolhido);
				JOptionPane.showMessageDialog(null, "Acompanhamento Selecionado com sucesso!");
			} else {
				JOptionPane.showMessageDialog(null, "Acompanhamento não selecionado!");
			}

			int escolha = JOptionPane.showConfirmDialog(null, "Deseja acrescentar outro acompanhamento?", null,
					JOptionPane.YES_NO_OPTION);
			if (escolha == JOptionPane.NO_OPTION) {
				prosseguir = false;
			}
		} while (prosseguir == true);

		carrinho.resumoCarrinho();
		carrinho.pagamento();

	}

	public void exibirRestaurantes(List<restaurante> listaRestaurantes) {
		if (listaRestaurantes.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Nenhum Restaurante Dispoinivel!");
			return;
		}
		StringBuilder exibirRestaurantes = new StringBuilder("Restaurantes:\n");
		for (restaurante restaurantes : listaRestaurantes) {
			exibirRestaurantes.append("-").append(restaurantes.toString()).append("\n");
		}
		JOptionPane.showMessageDialog(null, exibirRestaurantes.toString());
	}

}

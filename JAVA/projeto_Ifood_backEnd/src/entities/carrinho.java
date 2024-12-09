package entities;

import java.awt.BorderLayout;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import enums.formasPagamento;
import enums.statusEntrega;

public class carrinho {
	conexaoBD conexao = new conexaoBD();
	formasPagamento formasPagamento;
	correiosCep correiosCep = new correiosCep();
	restaurante restaurante;
	private endereco endereco;
	private List<produto> produtos;
	private List<acompanhamento> acompanhamentos;
	private List<Object> listaCarrinho;
	private String formaPagaString;
	String path = "historicoPedidos.txt";
	File arquivo = new File(path);
	private List<Object> pedidosRealizados = new ArrayList<>();

	private float total;

	public List<Object> getListaCarrinho() {
		return listaCarrinho;
	}

	public void setRestaurante(restaurante restaurante) {
		this.restaurante = restaurante;
	}

	public carrinho() {
		this.produtos = new ArrayList<>();
		this.listaCarrinho = new ArrayList<>();
		this.acompanhamentos = new ArrayList<>();
		this.total = 0;
		this.endereco = new endereco();
	}

	public void adicionarProduto(produto produto) {
		produtos.add(produto);
		listaCarrinho.add(produto);
		total += produto.getPreco();

	}

	public void adicionarAcompanhamento(acompanhamento acompanhamento) {
		acompanhamentos.add(acompanhamento);
		listaCarrinho.add(acompanhamento);
		total += acompanhamento.getValor();

	}

	public String getFormaPagaString() {
		return formaPagaString;
	}

	public void resumoCarrinho() {
		StringBuilder resumoCarrinho = new StringBuilder("Carrinho de Compras:\n");
		resumoCarrinho.append("Produtos:\n");
		for (produto produto : produtos) {
			resumoCarrinho.append("- ").append(produto.getTitulo()).append(" - R$ ").append(produto.getPreco())
					.append("\n");
		}

		if (acompanhamentos.size() > 0) {
			resumoCarrinho.append("Acompanhamentos:\n");
			for (acompanhamento acompanhamento : acompanhamentos) {
				resumoCarrinho.append("- ").append(acompanhamento.getTitulo()).append(" - R$ ")
						.append(acompanhamento.getValor()).append("\n");
			}
		}

		resumoCarrinho.append("Total: R$ ").append(total).append("\n");

		JOptionPane.showMessageDialog(null, resumoCarrinho);
	}

	public void exibirCarrinho() {
		if (listaCarrinho.isEmpty()) {
			JOptionPane.showMessageDialog(null, "O carrinho está vazio!");
			return;
		}
		StringBuilder exibirCarrinho = new StringBuilder("Carrinho de compras:\n");
		for (Object item : listaCarrinho) {
			exibirCarrinho.append("- ").append(item.toString()).append("\n");
		}
		exibirCarrinho.append("- Total:").append(total).append(" R$ ").append("\n");

		JOptionPane.showMessageDialog(null, exibirCarrinho.toString());
	}

	public void setListaCarrinho(List<Object> listaCarrinho) {
		this.listaCarrinho = listaCarrinho;
	}

	public float getTotal() {
		return total;
	}

	public List<produto> getProdutos() {
		return produtos;
	}

	public List<acompanhamento> getAcompanhamentos() {
		return acompanhamentos;
	}

	public void pagamento() {
		endereco endereco = this.endereco;

		try {
			if (listaCarrinho.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Carrinho vazio adicione um pedido para prosseguir!");
				return;
			}
			int opcao = JOptionPane.showConfirmDialog(null, "Deseja prosseguir para o pagamento:?", null,
					JOptionPane.YES_NO_OPTION);
			if (opcao == JOptionPane.NO_OPTION) {
				return;
			}
			String cep = JOptionPane.showInputDialog(null, "Digite o Cep de Entrega");
			if (cep == null) {
				return;
			}
			endereco = correiosCep.consultarCep(cep);

			String mensagemEndereco = "Endereço encontrado:\n" + "Cidade: " + endereco.getCidade() + "\n" + "Bairro: "
					+ endereco.getBairro() + "\n" + "Estado: " + endereco.getEstado() + "\n" + "Confirma o endereço?";

			opcao = JOptionPane.showConfirmDialog(null, mensagemEndereco, "Confirma o endereço?",
					JOptionPane.YES_NO_OPTION);

			if (opcao == JOptionPane.NO_OPTION) {
				return;
			}

			String num;
			do {
				num = JOptionPane.showInputDialog(null, "Digite o numero de sua residencia");
				if (num == null) {
					return;
				} else if (num.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Campo vazio!", "ERRO!", JOptionPane.ERROR_MESSAGE);

				}
			} while (num.trim().isEmpty());
			endereco.setNumero(num);
			endereco.setCep(cep);
			String comple;
			do {
				comple = JOptionPane.showInputDialog(null, "Digite um complemeto para o seu endereço");
				if (comple == null) {
					return;
				}
				if (comple.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Campo vazio!", "ERRO!", JOptionPane.ERROR_MESSAGE);
				}
			} while (comple.trim().isEmpty());
			endereco.setComplemento(comple);

			String mensagem1 = "total do carrinho a ser pago: R$ " + total;

			formasPagamento[] formaDePagamentoArray = formasPagamento.values();

			formasPagamento formaDePagamentoEscolhida = (formasPagamento) JOptionPane.showInputDialog(null,
					"Escolha uma forma de pagamento", "Selecione o pagamento", JOptionPane.QUESTION_MESSAGE, null,
					formaDePagamentoArray, formaDePagamentoArray[0]);

			if (formaDePagamentoEscolhida == null) {
				JOptionPane.showMessageDialog(null, "Nenhuma forma de pagamento escolhida selecionado!");
				return;
			}
			formaPagaString = formaDePagamentoEscolhida.toString();
			JOptionPane.showMessageDialog(null, mensagem1);
			opcao = JOptionPane.showConfirmDialog(null, "Deseja efetuar o pagamento?", null, JOptionPane.YES_NO_OPTION);
			if (opcao == JOptionPane.NO_OPTION) {
				return;
			}
			JOptionPane.showMessageDialog(null, "Aguarde um instante estamos verificando o pagamento");
			for (int i = 0; i < 3; i++) {
				Thread.sleep(1000);
			}
			String mensagem2 = "Pagamento de: R$ " + total + " aprovado";
			JOptionPane.showMessageDialog(null, mensagem2);

			JOptionPane.showMessageDialog(null, "Pedido efetuado aguarde a entrega!");

			statusEntrega[] statusEntregaArray = statusEntrega.values();

			JFrame frame = new JFrame("Status do pedido");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(400, 200);
			frame.setLayout(new BorderLayout());

			JPanel panel = new JPanel();
			JLabel statusLabel = new JLabel("Aguardando Status:", SwingConstants.CENTER);
			statusLabel.setFont(new Font("Arial", Font.PLAIN, 14));
			panel.add(statusLabel);
			frame.add(panel, BorderLayout.CENTER);

			frame.setVisible(true);

			for (int i = 0; i < 3; i++) {
				Thread.sleep(10000);
				statusLabel.setText(statusEntregaArray[i].getDescricao());
			}
			conexao.adicionarPedidoBD(this, restaurante.getId());
			conexao.salvarEndereco(endereco);
			JOptionPane.showMessageDialog(null, "Pedido finalizado, obrigado pela preferencia!");

			if (pedidosRealizados.add(listaCarrinho)) {
				try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
					for (Object itens : pedidosRealizados) {
						String mensagem = itens.toString() + " " + LocalDateTime.now();
						bw.write(mensagem);
						bw.newLine();
					}

				}
				produtos.removeAll(produtos);
				acompanhamentos.removeAll(acompanhamentos);
				listaCarrinho.removeAll(listaCarrinho);
				total = 0;
				frame.setVisible(false);

			}

		} catch (InterruptedException e) {
			JOptionPane.showMessageDialog(null, e);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}

	public void limparCarrinho() {
		if (listaCarrinho.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Não ha produtos no carrinho!");
			return;
		}
		int opcao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover os pedidos do carrinho?", null,
				JOptionPane.YES_NO_OPTION);

		if (opcao == JOptionPane.NO_OPTION) {
			return;
		}
		produtos.removeAll(produtos);
		acompanhamentos.removeAll(acompanhamentos);
		listaCarrinho.removeAll(listaCarrinho);
		total = 0;
		JOptionPane.showMessageDialog(null, "Carrinho limpo com sucesso!");
	}

	public void historicoPedidos() {
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			List<String> pedidosExibicao = new ArrayList<>();
			if (arquivo.exists() && arquivo.length() == 0) {
				JOptionPane.showMessageDialog(null, "Não ha historico de pedidos!");
				return;
			}
			StringBuilder mensagem = new StringBuilder();

			String line;
			while ((line = br.readLine()) != null) {
				int indiceDataHora = line.lastIndexOf(" ");
				if (indiceDataHora != -1) {

					String pedidoSemData = line.substring(0, indiceDataHora);
					mensagem.append(pedidoSemData).append("\n");
					pedidosExibicao.add(pedidoSemData);
				}
			}
			JList<String> listaPedidos = new JList<String>(pedidosExibicao.toArray(new String[0]));
			listaPedidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listaPedidos.setVisibleRowCount(10);

			JScrollPane scrolPane = new JScrollPane(listaPedidos);

			JDialog dialog = new JDialog();
			dialog.setTitle("Histórico de Pedidos");
			dialog.setSize(800, 600);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.add(scrolPane);
			dialog.setModal(true);
			dialog.setVisible(true);

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "ERRO: Impossivel acessar o historico de pedidos");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

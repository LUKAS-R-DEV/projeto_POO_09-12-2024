package aplication;

import javax.swing.JOptionPane;

import UI.tema;
import entities.carrinho;
import entities.conexaoBD;
import entities.endereco;
import entities.restauranteControler;

public class program {
	
	public static void main(String[] args) {
		conexaoBD conexao=new conexaoBD();
		restauranteControler controler = new restauranteControler();
		carrinho carrinho = new carrinho();
		tema tema = new tema();
		tema.aparencia();
		while (true) {
			try {
				Integer opcao;
				do {

					String input =(JOptionPane.showInputDialog(null,
							"Seja Bem Vindo ao Menu De escolhas\n1:Adicionar Pedido Ao Carrinho\n2:Exibir Restaurantes Disponiveis\n3:Exibir Produtos No Carrinho\n4:Prosseguir Para  Pagamento\n5:Limpar o carrinho\n6:Exibir historico de compras\n7:Encerrar o Serviço"));
					if(input==null) {
						JOptionPane.showMessageDialog(null, "Programa encerrado.");
		                System.exit(0);
		                break; 
					}
					opcao=Integer.parseInt(input);
					
					switch (opcao) {
					case 1: {
						controler.realizarPedido(carrinho);
						break;

					}
					case 2: {
						controler.exibirRestaurantes(controler.getListaRestaurantes());
						break;

					}
					case 3: {
						carrinho.exibirCarrinho();
						break;
					}
					case 4: {
						carrinho.pagamento();
						break;

					}
					case 5:{
						carrinho.limparCarrinho();
						break;
						
					}
					case 6:{
						carrinho.historicoPedidos();
						break;
						
					}
					case 7: {
						JOptionPane.showMessageDialog(null, "Até logo!");
						System.exit(0);
						break;
					}

					default: {
						JOptionPane.showMessageDialog(null, "Opção errada!");
						break;
					}
					}
				} while (opcao != 7);
				break;

			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Formato invalido!");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "ERRO inesperado!");
				System.out.println(e.getMessage());

			}
		}
	}
}

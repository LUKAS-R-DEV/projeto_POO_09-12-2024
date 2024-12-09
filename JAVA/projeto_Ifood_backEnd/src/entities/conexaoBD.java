package entities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class conexaoBD {
	carrinho carrinho;
	private final String url = "jdbc:mysql://127.0.0.1:3306/projeto_ifood";
	private final String usuario = "Lucas";
	private final String senha = "~1lwb1yidh3i~6&";

	public List<restaurante> buscarRestaurantes() {
		List<restaurante> listaRestaurantes = new ArrayList<>();
		String sql = "SELECT * FROM restaurante";

		try (Connection conn = DriverManager.getConnection(url, usuario, senha);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				int idCategoria = rs.getInt("id_categoria");
				boolean retirada = rs.getBoolean("aceitaRetirada");
				int idEndereco = rs.getInt("id_endereco");

				categoria categoria = buscarCategoriaPorId(idCategoria);
				endereco endereco = buscarEnderecoPorId(idEndereco);

				restaurante rest = new restaurante();
				rest.setId(id);
				rest.setTitulo(nome);
				rest.setCategoria(idCategoria);
				rest.setDataCriacao(LocalDateTime.now());
				rest.setStatus(1);

				List<produto> listaProdutos = buscarProdutos(id);
				List<acompanhamento> listaAcompanhamentos = buscarAcompanhamentos(id);
				rest.setListaProdutos(listaProdutos);
				rest.setListaAcompanhamentos(listaAcompanhamentos);

				listaRestaurantes.add(rest);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaRestaurantes;
	}

	public List<produto> buscarProdutosPorRestaurante(restaurante restaurante) {

		return new ArrayList<>();
	}

	public List<acompanhamento> buscarAcompanhamentosPorRestaurante(restaurante restaurante) {

		return new ArrayList<>();
	}

	private categoria buscarCategoriaPorId(int id) {

		return new categoria();
	}

	private endereco buscarEnderecoPorId(int id) {

		return new endereco();
	}

	public List<produto> buscarProdutos(int idRestaurante) {
		List<produto> listaProdutos = new ArrayList<>();

		String sql = "SELECT * FROM produto WHERE id_restaurante = ?";

		try (Connection conn = DriverManager.getConnection(url, usuario, senha);
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, idRestaurante);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				String nome = rs.getString("nome");
				float valor = rs.getFloat("valor");

				produto produto = new produto(1, LocalDateTime.now(), nome, valor);
				listaProdutos.add(produto);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaProdutos;

	}

	public List<acompanhamento> buscarAcompanhamentos(int idRestaurante) {
		List<acompanhamento> listaAcompanhamentos = new ArrayList<>();

		String sql = "SELECT * FROM acompanhamento WHERE id_restaurante = ?";

		try (Connection conn = DriverManager.getConnection(url, usuario, senha);
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, idRestaurante);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				String nome = rs.getString("nome");
				double valor = rs.getDouble("valor");

				acompanhamento ac = new acompanhamento(1, LocalDateTime.now(), nome, valor);
				listaAcompanhamentos.add(ac);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaAcompanhamentos;
	}

	public void adicionarPedidoBD(carrinho carrinho, int idRestaurante) {
		String sqlPedido = "INSERT INTO pedido (id_restaurante,conteudo,troco,formaPagamento,dataPedido,total)"
				+ "VALUES (?,?,?,?,?,?)";
		String sqlProdutoVendidos = "INSERT INTO produtosVendidos(id_restaurante,nome,dataVenda,preco)"
				+ "VALUES (?,?,?,?)";
		String sqlAcompanhamentosVendidos = "INSERT INTO acompanhamentosVendidos(id_restaurante,nome,dataVenda,preco)"
				+ "VALUES (?,?,?,?)";
		String conteudo = "";
		try (Connection conn = DriverManager.getConnection(url, usuario, senha);
				PreparedStatement stmt = conn.prepareStatement(sqlPedido)) {

			for (produto produtos : carrinho.getProdutos()) {
				produto produtoBd = produtos;
				conteudo += "Produto: " + produtoBd.getTitulo() + "\n";

			}
			for (acompanhamento acompanhamentos : carrinho.getAcompanhamentos()) {
				acompanhamento acompanhamentoBD = acompanhamentos;
				conteudo += "Acompanhamento: " + acompanhamentoBD.getTitulo() + "\n";

			}

			stmt.setInt(1, idRestaurante);
			stmt.setString(2, conteudo);
			stmt.setDouble(3, 0);
			stmt.setString(4, carrinho.getFormaPagaString());
			stmt.setObject(5, LocalDateTime.now());
			stmt.setDouble(6, carrinho.getTotal());

			int linhasAfetadas = stmt.executeUpdate();

			if (linhasAfetadas > 0) {

			} else {

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		try (Connection conn = DriverManager.getConnection(url, usuario, senha);
				PreparedStatement stmt = conn.prepareStatement(sqlProdutoVendidos)) {

			for (produto produtos : carrinho.getProdutos()) {
				produto produtoBd = produtos;
				stmt.setInt(1, idRestaurante);
				stmt.setString(2, produtoBd.getTitulo());
				stmt.setObject(3, LocalDateTime.now());
				stmt.setDouble(4, produtoBd.getPreco());
				stmt.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		try (Connection conn = DriverManager.getConnection(url, usuario, senha);
				PreparedStatement stmt = conn.prepareStatement(sqlAcompanhamentosVendidos)) {

			for (acompanhamento acompanhamentos : carrinho.getAcompanhamentos()) {
				acompanhamento acompanhamentoBD = acompanhamentos;
				stmt.setInt(1, idRestaurante);
				stmt.setString(2, acompanhamentoBD.getTitulo());
				stmt.setObject(3, LocalDateTime.now());
				stmt.setDouble(4, acompanhamentoBD.getValor());
				stmt.executeUpdate();
			}

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

	public void salvarEndereco(endereco endereco) {
		String sqlEndereco = "INSERT INTO endereco(observacao,numero,complemento,bairro,cidade,estado,cep)"
				+ "VALUES (?,?,?,?,?,?,?)";
		try (Connection conn = DriverManager.getConnection(url, usuario, senha);
				PreparedStatement stmt = conn.prepareStatement(sqlEndereco)) {

			stmt.setString(1, "Cliente");
			stmt.setString(2, endereco.getNumero());
			stmt.setString(3, endereco.getComplemento());
			stmt.setString(4, endereco.getBairro());
			stmt.setString(5, endereco.getCidade());
			stmt.setString(6, endereco.getEstado());
			stmt.setString(7, endereco.getCep());
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}

package entities;

import java.awt.BorderLayout;
import java.awt.Font;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Time;
import java.util.Timer;

import javax.json.Json;
import javax.json.JsonObject;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class correiosCep {

	private static final String API_URL = "https://viacep.com.br/ws/";
	private String bairro;
	private String cidade;

	public String getBairro() {
		return bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public String getEstado() {
		return estado;
	}

	private String estado;

	public static endereco consultarCep(String cep) throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(API_URL + cep + "/json/")).build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		JFrame frame = new JFrame("Verificando CEP");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 200);
		frame.setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		JLabel label = new JLabel("Aguarde a verificação do CEP", SwingConstants.CENTER);
		label.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(label);
		frame.add(panel, BorderLayout.CENTER);
		frame.setVisible(true);
		Thread.sleep(3000);

		if (response.statusCode() == 200) {
			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(response.body().getBytes());
			InputStreamReader reader = new InputStreamReader(byteArrayInputStream);
			JsonObject jsonObject = Json.createReader(reader).readObject();
			frame.setVisible(false);
			String responseBody = response.body();

			String bairro = jsonObject.containsKey("bairro") ? jsonObject.getString("bairro") : "Não disponível";
			String cidade = jsonObject.containsKey("localidade") ? jsonObject.getString("localidade")
					: "Não disponível";
			String estado = jsonObject.containsKey("uf") ? jsonObject.getString("uf") : "Não disponível";

			endereco endereco = new endereco();
			endereco.setBairro(bairro);
			endereco.setCidade(cidade);
			endereco.setEstado(estado);

			return endereco;

		} else {
			frame.setVisible(false);
			JOptionPane.showMessageDialog(null, "Erro ao consultar o CEP.");
			return null;

		}

	}

}

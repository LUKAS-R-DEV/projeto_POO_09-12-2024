package enums;

public enum statusEntrega {
	PRODUÇÃO("Produção iniciada"),
    TRANSPORTE("O pedido está a caminho do seu endereço"),
    ENTREGUE("Pedido entregue");

	private final String descricao;

    
    statusEntrega(String descricao) {
        this.descricao = descricao;
    }

    
    public String getDescricao() {
        return descricao;
    }

		
	}

package br.com.fiap.thebestway.domain.enums;

public enum TipoCartao {
	DEBITO(1, "Debito"), CREDITO(2, "Credito");

	private Integer codigo;
	private String descricao;

	private TipoCartao(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static TipoCartao toEnum(Integer cod) {

		if (cod == null) {
			return null;
		}

		for (TipoCartao obj : TipoCartao.values()) {
			if (cod.equals(obj.getCodigo())) {
				return obj;
			}
		}

		throw new IllegalArgumentException("O tipo de cartão é inválido");
	}
}

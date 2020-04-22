package br.com.fiap.thebestway.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class CorridaPedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@EmbeddedId
	private CorridaPedidoPK id = new CorridaPedidoPK();

	private Double desconto;
	private Integer quantidade;
	private Double preco;

	public CorridaPedido() {
	}

	public CorridaPedido(Pedido pedido, Corrida corrida, Double desconto, Integer quantidade, Double preco) {
		super();
		id.setPedido(pedido);
		id.setCorrida(corrida);
		this.desconto = desconto;
		this.quantidade = quantidade;
		this.preco = preco;
	}

	@JsonIgnore
	public Pedido getPedido() {
		return id.getPedido();
	}

	public Corrida getCorrida() {
		return id.getCorrida();
	}

	public CorridaPedidoPK getId() {
		return id;
	}

	public Double getDesconto() {
		return desconto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public Double getPreco() {
		return preco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CorridaPedido other = (CorridaPedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
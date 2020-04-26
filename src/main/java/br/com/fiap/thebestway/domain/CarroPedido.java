package br.com.fiap.thebestway.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class CarroPedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@EmbeddedId
	private CarroPedidoPK id = new CarroPedidoPK();

	private Double custoFixo;
	private Double desconto;
	private Double subTotal;
	private Double extras;
	private Double total;

	public CarroPedido() {
	}

	public CarroPedido(Pedido pedido, Carro carro, Double custoFixo, Double desconto, Double subTotal, Double extras,
			Double total) {
		super();
		id.setPedido(pedido);
		id.setCarro(carro);
		this.custoFixo = custoFixo;
		this.desconto = desconto;
		this.subTotal = subTotal;
		this.extras = extras;
		this.total = total;
	}

	@JsonIgnore
	public Pedido getPedido() {
		return id.getPedido();
	}

	public Carro getCarro() {
		return id.getCarro();
	}

	public Double getCustoFixo() {
		return custoFixo;
	}

	public void setCustoFixo(Double custoFixo) {
		this.custoFixo = custoFixo;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}

	public Double getExtras() {
		return extras;
	}

	public void setExtras(Double extras) {
		this.extras = extras;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
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
		CarroPedido other = (CarroPedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
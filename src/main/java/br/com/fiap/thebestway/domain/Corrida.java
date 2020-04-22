package br.com.fiap.thebestway.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Corrida implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer categoria;
	private Double preco;
	private Integer status;

	@JsonBackReference
	@ManyToMany
	@JoinTable(name = "CORRIDA_CARRO", joinColumns = @JoinColumn(name = "corrida_id"), inverseJoinColumns = @JoinColumn(name = "carro_id"))
	private List<Carro> carros = new ArrayList<Carro>();

	private Set<CorridaPedido> corridas = new HashSet<>();

	public Corrida() {
	}

	public Corrida(Integer id, Integer categoria, Double preco, Integer status) {
		super();
		this.id = id;
		this.categoria = categoria;
		this.preco = preco;
		this.status = status;
	}

	public List<Pedido> getPedidos() {
		List<Pedido> lista = new ArrayList<>();

		for (CorridaPedido obj : corridas) {
			lista.add(obj.getPedido());
		}

		return lista;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCategoria() {
		return categoria;
	}

	public void setCategoria(Integer categoria) {
		this.categoria = categoria;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<Carro> getCarros() {
		return carros;
	}

	public void setCarros(List<Carro> carros) {
		this.carros = carros;
	}

	public Set<CorridaPedido> getCorridas() {
		return corridas;
	}

	public void setCorridas(Set<CorridaPedido> corridas) {
		this.corridas = corridas;
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
		Corrida other = (Corrida) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
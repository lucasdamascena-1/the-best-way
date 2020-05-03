package br.com.fiap.thebestway.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Carro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String marca;

	private String modelo;

	@Column(unique = true)
	private String placa;

	private Double notaMediaDeViagem;

	private Integer quantidadeDeCorridas;

	private Integer disponibilidade;

	@JsonIgnore
	@OneToMany(mappedBy = "id.pedido")
	private Set<CarroPedido> corrida = new HashSet<>();

	public Carro() {
	}

	public Carro(Integer id, String marca, String modelo, String placa, Double notaMediaDeViagem,
			Integer quantidadeDeCorridas, Integer disponibilidade) {
		super();
		this.id = id;
		this.marca = marca;
		this.modelo = modelo;
		this.placa = placa;
		this.notaMediaDeViagem = notaMediaDeViagem;
		this.quantidadeDeCorridas = quantidadeDeCorridas;
		this.disponibilidade = disponibilidade;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Double getNotaMediaDeViagem() {
		return notaMediaDeViagem;
	}

	public void setNotaMediaDeViagem(Double notaMediaDeViagem) {
		this.notaMediaDeViagem = notaMediaDeViagem;
	}

	public Integer getQuantidadeDeCorridas() {
		return quantidadeDeCorridas;
	}

	public void setQuantidadeDeCorridas(Integer quantidadeDeCorridas) {
		this.quantidadeDeCorridas = quantidadeDeCorridas;
	}

	public Integer getDisponibilidade() {
		return disponibilidade;
	}

	public void setDisponibilidade(Integer disponibilidade) {
		this.disponibilidade = disponibilidade;
	}

	@JsonIgnore
	public List<Pedido> getPedidos() {
		List<Pedido> lista = new ArrayList<>();

		for (CarroPedido obj : corrida) {
			lista.add(obj.getPedido());
		}

		return lista;
	}

	public Set<CarroPedido> getCorrida() {
		return corrida;
	}

	public void setCorrida(Set<CarroPedido> corrida) {
		this.corrida = corrida;
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
		Carro other = (Carro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
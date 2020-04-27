package br.com.fiap.thebestway.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.fiap.thebestway.domain.Carro;

public class CarroDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message = "Campo Obrigatório")
	@Length(min = 5, max = 30, message = "O tamanho deve ser entre 5 e 30 caracteres")
	private String marca;

	@NotEmpty(message = "Campo Obrigatório")
	@Length(min = 5, max = 30, message = "O tamanho deve ser entre 5 e 30 caracteres")
	private String modelo;

	@NotEmpty(message = "Campo Obrigatório")
	@Length(min = 6, max = 8, message = "O tamanho deve ser entre 5 e 8 caracteres")
	private String placa;

	private Double notaMediaDeViagem;
	private Integer quantidadeDeCorridas;
	private Integer disponibilidade;

	public CarroDTO(Integer id, String marca, String modelo, String placa, Double notaMediaDeViagem,
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

	public CarroDTO() {
	}

	public CarroDTO(Carro obj) {
		this.id = obj.getId();
		this.marca = obj.getMarca();
		this.modelo = obj.getModelo();
		this.placa = obj.getPlaca();
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

}
package br.com.fiap.thebestway.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import br.com.fiap.thebestway.domain.Carro;
import br.com.fiap.thebestway.domain.validation.CarroInsert;

@CarroInsert
public class CarroNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String marca;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String modelo;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String placa;

	private Double notaMediaDeViagem;
	private Integer quantidadeDeCorridas;
	private Integer disponibilidade;

	public CarroNewDTO() {
	}

	public CarroNewDTO(Carro obj) {
		this.marca = obj.getMarca();
		this.modelo = obj.getModelo();
		this.placa = obj.getPlaca();
		this.notaMediaDeViagem = obj.getNotaMediaDeViagem();
		this.quantidadeDeCorridas = obj.getQuantidadeDeCorridas();
		this.disponibilidade = obj.getDisponibilidade();
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
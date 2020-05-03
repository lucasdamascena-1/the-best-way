package br.com.fiap.thebestway.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import br.com.fiap.thebestway.domain.Carro;

public class CarroUpdateDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotNull(message = "Preenchimento obrigatório")
	private Double notaMediaDeViagem;

	@NotNull(message = "Preenchimento obrigatório")
	private Integer quantidadeDeCorridas;

	@NotNull(message = "Preenchimento obrigatório")
	private Integer disponibilidade;

	public CarroUpdateDTO() {
	}

	public CarroUpdateDTO(Carro obj) {
		this.id = obj.getId();
		this.notaMediaDeViagem = obj.getNotaMediaDeViagem();
		this.quantidadeDeCorridas = obj.getQuantidadeDeCorridas();
		this.disponibilidade = obj.getDisponibilidade();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
package br.com.fiap.thebestway.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.fiap.thebestway.domain.validation.UsuarioUpdate;

@UsuarioUpdate
public class UsuarioUpdateDTO {

	private Integer id;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String senha;
	@NotEmpty(message = "Preenchimento obrigatório")
	private String telefone;
	@NotNull(message = "Preenchimento obrigatório")
	private Integer status;

	public UsuarioUpdateDTO() {
	}

	public UsuarioUpdateDTO(Integer id,String senha, String telefone, Integer status) {
		super();
		this.id = id;
		this.senha = senha;
		this.telefone = telefone;
		this.status = status;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}

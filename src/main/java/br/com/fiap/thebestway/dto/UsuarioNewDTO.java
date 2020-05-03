package br.com.fiap.thebestway.dto;

import javax.validation.constraints.NotEmpty;

public class UsuarioNewDTO {

	@NotEmpty(message = "Preenchimento obrigatório")
	private String nome;
	@NotEmpty(message = "Preenchimento obrigatório")
	private String email;
	@NotEmpty(message = "Preenchimento obrigatório")
	private String cpf;
	@NotEmpty(message = "Preenchimento obrigatório")
	private String senha;
	@NotEmpty(message = "Preenchimento obrigatório")
	private String telefone;

	private Integer status;

	public UsuarioNewDTO() {
	}

	public UsuarioNewDTO(String nome, String email, String cpf, String senha, String telefone, Integer status) {
		super();
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.senha = senha;
		this.telefone = telefone;
		this.status = status;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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
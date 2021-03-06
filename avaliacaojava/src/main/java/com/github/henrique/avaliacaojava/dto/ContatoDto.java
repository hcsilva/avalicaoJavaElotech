package com.github.henrique.avaliacaojava.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ContatoDto {

	@NotEmpty(message = "{contato.nome.campoObrigatorio}")
	private String nome;

	@NotEmpty(message = "{contato.telefone.campoObrigatorio}")
	private String telefone;

	@Email(message = "{contato.email.invalido}")
	@NotEmpty(message = "{contato.email.campoObrigatorio}")
	private String email;

	@NotNull(message = "{contato.pessoa.campoObrigatorio}")
	private PessoaDto pessoa;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public PessoaDto getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaDto pessoa) {
		this.pessoa = pessoa;
	}

}

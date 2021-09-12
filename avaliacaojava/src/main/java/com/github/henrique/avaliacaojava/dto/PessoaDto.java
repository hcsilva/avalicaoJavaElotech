package com.github.henrique.avaliacaojava.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

public class PessoaDto {

	@NotEmpty(message = "{pessoas.nome.campoObrigatorio}")
	private String nome;

	@NotEmpty(message = "{pessoa.cpf.campoObrigatorio}")
	@CPF(message = "{pessoa.cpf.cpfInvalido}")
	private String cpf;

	@NotEmpty(message = "{pessoa.dataNascimento.campoObrigatorio}")
	private String dataNascimento;

	private List<ContatoDto> contatos = new ArrayList<>();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public List<ContatoDto> getContatos() {
		return contatos;
	}

	public void setContatos(List<ContatoDto> contatos) {
		this.contatos = contatos;
	}

}

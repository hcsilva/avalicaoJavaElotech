package com.github.henrique.avaliacaojava.util;

import java.util.Date;

import com.github.henrique.avaliacaojava.models.Contato;
import com.github.henrique.avaliacaojava.models.Pessoa;

public class PessoaCreator {

	public static Pessoa criarPessoaParaSerSalva() {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Teste Nome");
		pessoa.setCpf("03050235007");
		pessoa.setDataNascimento(new Date());

		Contato contato = new Contato();
		contato.setNome("Teste Contato");
		contato.setEmail("testeContato@gmail.com");
		contato.setPessoa(pessoa);
		contato.setTelefone("999999999999");

		pessoa.getContatos().add(contato);

		return pessoa;
	}

	public static Pessoa criarPessoaValida() {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Teste Nome");
		pessoa.setCpf("03050235007");
		pessoa.setDataNascimento(new Date());
		pessoa.setId(1);

		Contato contato = new Contato();
		contato.setId(1);
		contato.setNome("Teste Contato");
		contato.setEmail("testeContato@gmail.com");
		contato.setPessoa(pessoa);
		contato.setTelefone("99999999999");

		pessoa.getContatos().add(contato);

		return pessoa;
	}

	public static Pessoa criarPessoaUpdateValido() {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Ciclano");
		pessoa.setCpf("03050235007");
		pessoa.setDataNascimento(new Date());
		pessoa.setId(1);

		Contato contato = new Contato();
		contato.setNome("Fulano");
		contato.setEmail("testeContato@gmail.com");
		contato.setPessoa(pessoa);
		contato.setTelefone("88888888888");

		pessoa.getContatos().add(contato);

		return pessoa;
	}

}

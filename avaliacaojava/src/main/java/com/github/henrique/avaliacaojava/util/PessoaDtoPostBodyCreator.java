package com.github.henrique.avaliacaojava.util;

import java.util.Date;

import com.github.henrique.avaliacaojava.dto.ContatoDto;
import com.github.henrique.avaliacaojava.dto.PessoaDto;

public class PessoaDtoPostBodyCreator {

	public static PessoaDto criarPessoaParaSerSalva() {
		PessoaDto pessoaDto = new PessoaDto();
		pessoaDto.setNome("Teste Nome");
		pessoaDto.setCpf("03050235007");
		pessoaDto.setDataNascimento(new Date().toString());

		ContatoDto contatoDto = new ContatoDto();
		contatoDto.setNome("Teste Contato");
		contatoDto.setEmail("testeContato@gmail.com");
		contatoDto.setPessoa(pessoaDto);
		contatoDto.setTelefone("999999999999");

		pessoaDto.getContatos().add(contatoDto);

		return pessoaDto;
	}

}

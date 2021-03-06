package com.github.henrique.avaliacaojava.controller;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.github.henrique.avaliacaojava.dto.PessoaDto;
import com.github.henrique.avaliacaojava.models.Pessoa;
import com.github.henrique.avaliacaojava.service.PessoaService;
import com.github.henrique.avaliacaojava.util.PessoaCreator;
import com.github.henrique.avaliacaojava.util.PessoaDtoPostBodyCreator;
import com.github.henrique.avaliacaojava.util.PessoaDtoPutBodyCreator;

@ExtendWith(SpringExtension.class)
class PessoaControllerTest {

	@InjectMocks
	private PessoaController pessoaController;

	@Mock
	private PessoaService pessoaServiceMock;

	@BeforeEach
	void setUp() {
		PageImpl<Pessoa> pessoaPage = new PageImpl<Pessoa>(List.of(PessoaCreator.criarPessoaValida()));
		BDDMockito.when(pessoaServiceMock.findAll(ArgumentMatchers.any())).thenReturn(pessoaPage);

		BDDMockito.when(pessoaServiceMock.findAll()).thenReturn(List.of(PessoaCreator.criarPessoaValida()));

		BDDMockito.when(pessoaServiceMock.findById(ArgumentMatchers.anyInt()))
				.thenReturn(PessoaCreator.criarPessoaValida());

		BDDMockito.when(pessoaServiceMock.save(ArgumentMatchers.any(PessoaDto.class)))
				.thenReturn(PessoaCreator.criarPessoaValida());

		BDDMockito.doNothing().when(pessoaServiceMock).update(ArgumentMatchers.anyInt(),
				ArgumentMatchers.any(PessoaDto.class));

		BDDMockito.doNothing().when(pessoaServiceMock).deletar(ArgumentMatchers.anyInt());
	}

	@Test
	@DisplayName("Retorna uma lista de pessoas paginada quando sucesso")
	void list_return_pessoas_pageable_when_sucesso() {
		String nomeEsperado = PessoaCreator.criarPessoaValida().getNome();
		Page<Pessoa> pessoaPage = pessoaController.list(0, 10);

		Assertions.assertThat(pessoaPage).isNotNull();
		Assertions.assertThat(pessoaPage.toList()).isNotEmpty();
		Assertions.assertThat(pessoaPage.toList()).isNotEmpty().hasSize(1);
		Assertions.assertThat(pessoaPage.toList().get(0).getNome()).isEqualTo(nomeEsperado);

	}

	@Test
	@DisplayName("Retorna uma lista de pessoas quando sucesso")
	void list_return_pessoas_when_sucesso() {
		String nomeEsperado = PessoaCreator.criarPessoaValida().getNome();
		List<Pessoa> pessoas = pessoaController.findAll();

		Assertions.assertThat(pessoas).isNotNull().isNotEmpty().hasSize(1);
		Assertions.assertThat(pessoas.get(0).getNome()).isEqualTo(nomeEsperado);
	}

	@Test
	@DisplayName("Retorna uma pessoa quando sucesso")
	void findById_return_pessoa_when_sucesso() {
		Integer idEsperado = PessoaCreator.criarPessoaValida().getId();
		Pessoa pessoa = pessoaController.findById(1);

		Assertions.assertThat(pessoa).isNotNull();
		Assertions.assertThat(pessoa.getId()).isNotNull().isEqualTo(idEsperado);
	}

	@Test
	@DisplayName("Salva uma pessoa quando sucesso")
	void save_return_pessoa_when_sucesso() {
		Pessoa pessoaValida = PessoaCreator.criarPessoaValida();
		Pessoa pessoa = pessoaController.salvar(PessoaDtoPostBodyCreator.criarPessoaParaSerSalva());
		Assertions.assertThat(pessoa.getId()).isNotNull().isEqualTo(pessoaValida.getId());
	}

	@Test
	@DisplayName("Atualiza uma pessoa quando sucesso")
	void update_pessoa_when_sucesso() {

		Assertions.assertThatCode(() -> pessoaController.update(1, PessoaDtoPutBodyCreator.criarPessoaParaUpdate()))
				.doesNotThrowAnyException();
	}

	@Test
	@DisplayName("Deleta uma pessoa quando sucesso")
	void delete_pessoa_when_sucesso() {
		
		Assertions.assertThatCode(() -> pessoaController.delete(1))
		.doesNotThrowAnyException();
	}

}

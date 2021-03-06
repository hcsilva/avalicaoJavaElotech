package com.github.henrique.avaliacaojava.service;

import java.util.List;
import java.util.Optional;

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
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.github.henrique.avaliacaojava.models.Pessoa;
import com.github.henrique.avaliacaojava.repository.PessoaRepository;
import com.github.henrique.avaliacaojava.util.PessoaCreator;

@ExtendWith(SpringExtension.class)
class PessoaServiceTest {

	@InjectMocks
	private PessoaService pessoaService;

	@Mock
	private PessoaRepository pessoaRepositoryMock;

	@BeforeEach
	void setUp() {
		PageImpl<Pessoa> pessoaPage = new PageImpl<Pessoa>(List.of(PessoaCreator.criarPessoaValida()));
		BDDMockito.when(pessoaRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class))).thenReturn(pessoaPage);

		BDDMockito.when(pessoaRepositoryMock.findAll()).thenReturn(List.of(PessoaCreator.criarPessoaValida()));

		BDDMockito.when(pessoaRepositoryMock.findById(ArgumentMatchers.anyInt()))
				.thenReturn(Optional.of(PessoaCreator.criarPessoaValida()));

		BDDMockito.when(pessoaRepositoryMock.save(ArgumentMatchers.any(Pessoa.class)))
				.thenReturn(PessoaCreator.criarPessoaValida());

		BDDMockito.doNothing().when(pessoaRepositoryMock).delete(ArgumentMatchers.any(Pessoa.class));
	}

	@Test
	@DisplayName("Retorna uma lista de pessoas paginada quando sucesso")
	void list_return_pessoas_pageable_when_sucesso() {
		String nomeEsperado = PessoaCreator.criarPessoaValida().getNome();
		Page<Pessoa> pessoaPage = pessoaService.findAll(PageRequest.of(1, 1));

		Assertions.assertThat(pessoaPage).isNotNull();
		Assertions.assertThat(pessoaPage.toList()).isNotEmpty();
		Assertions.assertThat(pessoaPage.toList()).isNotEmpty().hasSize(1);
		Assertions.assertThat(pessoaPage.toList().get(0).getNome()).isEqualTo(nomeEsperado);

	}

	@Test
	@DisplayName("Retorna uma lista de pessoas quando sucesso")
	void list_return_pessoas_when_sucesso() {
		String nomeEsperado = PessoaCreator.criarPessoaValida().getNome();
		List<Pessoa> pessoas = pessoaService.findAll();

		Assertions.assertThat(pessoas).isNotNull().isNotEmpty().hasSize(1);
		Assertions.assertThat(pessoas.get(0).getNome()).isEqualTo(nomeEsperado);
	}

	@Test
	@DisplayName("Retorna uma pessoa quando sucesso")
	void findById_return_pessoa_when_sucesso() {
		Integer idEsperado = PessoaCreator.criarPessoaValida().getId();
		Pessoa pessoa = pessoaService.findById(1);

		Assertions.assertThat(pessoa).isNotNull();
		Assertions.assertThat(pessoa.getId()).isNotNull().isEqualTo(idEsperado);
	}

	@Test
	@DisplayName("Deleta uma pessoa quando sucesso")
	void delete_pessoa_when_sucesso() {

		Assertions.assertThatCode(() -> pessoaService.deletar(1)).doesNotThrowAnyException();
	}

}

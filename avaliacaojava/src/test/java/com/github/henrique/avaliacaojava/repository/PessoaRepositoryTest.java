package com.github.henrique.avaliacaojava.repository;

import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.github.henrique.avaliacaojava.models.Pessoa;
import com.github.henrique.avaliacaojava.util.PessoaCreator;

@DataJpaTest
@DisplayName("Testes para Pessoa Repository")
class PessoaRepositoryTest {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Test
	@DisplayName("Salva e cria uma pessoa quando bem sucedido")
	void save_PersistPessoa_WhenSuccessful() {
		Pessoa pessoa = PessoaCreator.criarPessoaParaSerSalva();
		Pessoa pessoaSalva = pessoaRepository.save(pessoa);
		Assertions.assertThat(pessoaSalva).isNotNull();
		Assertions.assertThat(pessoaSalva.getId()).isNotNull();
		Assertions.assertThat(pessoaSalva.getContatos()).isNotEmpty();
	}

	@Test
	@DisplayName("Salva e atualiza uma pessoa quando bem sucedido")
	void save_UpdatePessoa_WhenSuccessful() {
		Pessoa pessoa = PessoaCreator.criarPessoaParaSerSalva();
		Pessoa pessoaSalva = pessoaRepository.save(pessoa);

		pessoaSalva.setNome("Fulano");
		pessoaSalva.setCpf("01744824085");

		Pessoa pessoaAtualizada = this.pessoaRepository.save(pessoaSalva);
		Assertions.assertThat(pessoaAtualizada).isNotNull();
		Assertions.assertThat(pessoaAtualizada.getId()).isNotNull();
		Assertions.assertThat(pessoaAtualizada.getNome()).isEqualTo(pessoaSalva.getNome());
		Assertions.assertThat(pessoaAtualizada.getCpf()).isEqualTo(pessoaSalva.getCpf());
	}

	@Test
	@DisplayName("Deleta uma pessoa quando bem sucedido")
	void delete_Pessoa_WhenSuccessful() {
		Pessoa pessoa = PessoaCreator.criarPessoaParaSerSalva();
		Pessoa pessoaSalva = pessoaRepository.save(pessoa);

		this.pessoaRepository.delete(pessoaSalva);
		Optional<Pessoa> pessoaOptional = this.pessoaRepository.findById(pessoaSalva.getId());
		Assertions.assertThat(pessoaOptional).isEmpty();
	}

	@Test
	@DisplayName("Carrega uma pessoa quando bem sucedido")
	void find_Pessoa_WhenSuccessful() {
		Pessoa pessoa = PessoaCreator.criarPessoaParaSerSalva();
		Pessoa pessoaSalva = pessoaRepository.save(pessoa);

		Optional<Pessoa> pessoaOptional = this.pessoaRepository.findById(pessoaSalva.getId());
		Assertions.assertThat(pessoaOptional).isNotEmpty();
	}

	@Test
	@DisplayName("Não Carrega uma pessoa quando não encontra pelo ID")
	void find_PessoaReturnEmpty_WhenIdNotFound() {
		Pessoa pessoa = PessoaCreator.criarPessoaParaSerSalva();
		pessoaRepository.save(pessoa);

		Optional<Pessoa> pessoaOptional = this.pessoaRepository.findById(58);
		Assertions.assertThat(pessoaOptional).isEmpty();
	}

	@Test
	@DisplayName("Salva throw notEmpty quando nome é vazio")
	void save_ThrowPessoa_WhenNomeIsVazio() {
		Pessoa pessoa = new Pessoa();
		Assertions.assertThatThrownBy(() -> pessoaRepository.save(pessoa))
				.isInstanceOf(ConstraintViolationException.class);
	}

}

package com.github.henrique.avaliacaojava.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ResponseStatusException;

import com.github.henrique.avaliacaojava.dto.ContatoDto;
import com.github.henrique.avaliacaojava.dto.PessoaDto;
import com.github.henrique.avaliacaojava.mapper.ContatoMapper;
import com.github.henrique.avaliacaojava.mapper.PessoaMapper;
import com.github.henrique.avaliacaojava.models.Contato;
import com.github.henrique.avaliacaojava.models.Pessoa;
import com.github.henrique.avaliacaojava.repository.PessoaRepository;

@Service
public class PessoaService implements PessoaMapper, ContatoMapper {

	@Autowired
	private PessoaRepository pessoaRepositoy;

	@Autowired
	private ContatoMapper contatoMapper;

	@Autowired
	private PessoaMapper pessoaMapper;

	@Transactional
	public Pessoa save(PessoaDto pessoaDto) {
		return pessoaRepositoy.save(pessoaMapper.toModel(pessoaDto));
	}

	@Transactional(readOnly = true)
	public Pessoa findById(Integer id) {
		return pessoaRepositoy.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada"));
	}

	public void deletar(Integer id) {
		Pessoa pessoa = findById(id);
		pessoaRepositoy.delete(pessoa);
	}

	public void update(Integer id, PessoaDto pessoaAtualizadaDto) {
		Pessoa pessoaSalva = findById(id);
		Pessoa pessoa = pessoaMapper.toModel(pessoaAtualizadaDto);
		pessoa.setId(pessoaSalva.getId());

		pessoaRepositoy.save(pessoa);
	}

	@Transactional(readOnly = true)
	public List<Pessoa> findAll() {
		return pessoaRepositoy.findAll();
	}

	@Transactional(readOnly = true)
	public Page<Pessoa> findAll(Pageable pageable) {
		return pessoaRepositoy.findAll(pageable);
	}

	@Override
	public Pessoa toModel(PessoaDto pessoaDto) {
		if (pessoaDto == null) {
			return null;
		}

		Pessoa pessoa = new Pessoa();
		pessoa.setNome(pessoaDto.getNome());
		pessoa.setCpf(pessoaDto.getCpf());

		Date dataConvertida;
		try {
			dataConvertida = new SimpleDateFormat("dd/MM/yyyy").parse(pessoaDto.getDataNascimento());
			pessoa.setDataNascimento(dataConvertida);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if (!CollectionUtils.isEmpty(pessoaDto.getContatos())) {
			pessoa.setContatos(converterTodosContatosParaModel(pessoaDto.getContatos()));
		}

		return pessoa;
	}

	private List<Contato> converterTodosContatosParaModel(List<ContatoDto> contatosDto) {

		if (CollectionUtils.isEmpty(contatosDto)) {
			return new ArrayList<>();
		}

		List<Contato> contatos = contatosDto.stream().map(contato -> contatoMapper.toModel(contato))
				.collect(Collectors.toList());

		return contatos;
	}

	@Override
	public PessoaDto toDto(Pessoa pessoa) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contato toModel(ContatoDto contatoDto) {

		if (contatoDto == null) {
			return null;
		}

		Contato contato = new Contato();
		contato.setNome(contatoDto.getNome());
		contato.setEmail(contatoDto.getEmail());
		contato.setTelefone(contatoDto.getTelefone());
		contato.setPessoa(pessoaMapper.toModel(contatoDto.getPessoa()));

		return contato;
	}

	@Override
	public ContatoDto toDto(Contato contato) {
		// TODO Auto-generated method stub
		return null;
	}

}

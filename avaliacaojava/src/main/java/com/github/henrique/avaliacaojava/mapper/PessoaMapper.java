package com.github.henrique.avaliacaojava.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.github.henrique.avaliacaojava.dto.PessoaDto;
import com.github.henrique.avaliacaojava.models.Pessoa;

@Mapper(componentModel = "spring")
public interface PessoaMapper {
	PessoaMapper INSTANCE = Mappers.getMapper(PessoaMapper.class);
	
	Pessoa toModel(PessoaDto pessoaDto);
	
	PessoaDto toDto(Pessoa pessoa);
}

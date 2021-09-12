package com.github.henrique.avaliacaojava.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.github.henrique.avaliacaojava.dto.ContatoDto;
import com.github.henrique.avaliacaojava.models.Contato;

@Mapper(componentModel = "spring")
public interface ContatoMapper {
	ContatoMapper INSTANCE = Mappers.getMapper(ContatoMapper.class);

	Contato toModel(ContatoDto contatoDto);

	ContatoDto toDto(Contato contato);
}

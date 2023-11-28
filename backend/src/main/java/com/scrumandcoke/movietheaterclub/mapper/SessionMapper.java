package com.scrumandcoke.movietheaterclub.mapper;

import com.scrumandcoke.movietheaterclub.dto.SessionDto;
import com.scrumandcoke.movietheaterclub.model.SessionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SessionMapper {

    SessionMapper INSTANCE = Mappers.getMapper(SessionMapper.class);

    SessionDto entityToDto(SessionEntity entity);

    SessionEntity dtoToEntity(SessionDto dto);
}


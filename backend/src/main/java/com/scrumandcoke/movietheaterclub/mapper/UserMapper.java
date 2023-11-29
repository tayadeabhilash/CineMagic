package com.scrumandcoke.movietheaterclub.mapper;

import com.scrumandcoke.movietheaterclub.dto.UserDto;
import com.scrumandcoke.movietheaterclub.model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "externalId", target = "userId")
    UserDto userEntityToUserDto(UserEntity userEntity);

    @Mapping(source = "externalId", target = "userId")
    List<UserDto> userEntitiesToDtos(List<UserEntity> userEntities);

    @Mapping(source = "userId", target = "externalId")
    UserEntity userDtoToUserEntity(UserDto userDto);
}

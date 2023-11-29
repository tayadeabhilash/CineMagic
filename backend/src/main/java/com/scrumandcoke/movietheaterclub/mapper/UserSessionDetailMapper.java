package com.scrumandcoke.movietheaterclub.mapper;

import com.scrumandcoke.movietheaterclub.dto.SessionDto;
import com.scrumandcoke.movietheaterclub.dto.UserDto;
import com.scrumandcoke.movietheaterclub.dto.UserSessionDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserSessionDetailMapper {

    UserSessionDetailMapper INSTANCE = Mappers.getMapper(UserSessionDetailMapper.class);

    @Mappings({
            @Mapping(source = "userDto.userId", target = "userId"),
            @Mapping(source = "userDto.firstName", target = "firstName"),
            @Mapping(source = "userDto.lastName", target = "lastName"),
            @Mapping(source = "userDto.email", target = "email"),
            @Mapping(source = "userDto.memberType", target = "memberType"),
            @Mapping(source = "userDto.createdAt", target = "createdAt"),
            @Mapping(source = "userDto.lastUpdatedAt", target = "lastUpdatedAt"),
            @Mapping(source = "sessionDto.sessionId", target = "sessionId"),
            @Mapping(source = "sessionDto.expireAt", target = "sessionExpireAt"),
            @Mapping(source = "userDto.userType", target = "userType")
    })
    UserSessionDetail toUserSessionDetail(UserDto userDto, SessionDto sessionDto);
}

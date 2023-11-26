package com.scrumandcoke.movietheaterclub.mapper;

import com.scrumandcoke.movietheaterclub.dto.BookingDto;
import com.scrumandcoke.movietheaterclub.model.BookingEntity;
import com.scrumandcoke.movietheaterclub.model.ShowTimeEntity;
import com.scrumandcoke.movietheaterclub.model.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.util.StringUtils;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    @Mapping(target = "userId", source = "bookingEntity.user.id")
    @Mapping(target = "showtimeId", source = "bookingEntity.showtime.id")
    BookingDto toDto(BookingEntity bookingEntity);

    @Mapping(target = "bookingId", ignore = true)
    @Mapping(target = "totalAmount", ignore = true)
    @Mapping(target = "onlineServiceFee", ignore = true)
    @Mapping(target = "bookingStatus", ignore = true)
    @Mapping(target = "bookingDate", ignore = true)
    @Mapping(target = "user", source = "userId", qualifiedByName = "userIdToUser")
    @Mapping(target = "showtime", source = "showtimeId", qualifiedByName = "showtimeIdToShowtimeEntity")
    BookingEntity toEntity(BookingDto bookingDto);

    @Mapping(target = "userId", source = "bookingEntity.user.userId")
    @Mapping(target = "showtimeId", source = "bookingEntity.showtime.showtimeId")
    List<BookingDto> toDto(List<BookingEntity> bookingEntities);

    @Named("showtimeIdToShowtimeEntity")
    public static ShowTimeEntity showtimeIdToShowtimeEntity(Integer showTimeId) {
        ShowTimeEntity showTimeEntity = new ShowTimeEntity();
        showTimeEntity.setId(showTimeId);
        return showTimeEntity;
    }

    @Named("userIdToUser")
    public static User userIdToUser(Integer userId) {
        if(userId == null || StringUtils.isEmpty(userId))
            return null;
        User user = new User();
        user.setId(userId);
        return user;
    }
}

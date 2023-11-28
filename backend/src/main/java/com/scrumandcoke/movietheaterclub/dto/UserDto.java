package com.scrumandcoke.movietheaterclub.dto;

import com.scrumandcoke.movietheaterclub.model.enums.MemberType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    String userId;

    private String firstName;

    private String lastName;

    private String email;

    private MemberType memberType;

    private Date createdAt;

    private Date lastUpdatedAt;
}

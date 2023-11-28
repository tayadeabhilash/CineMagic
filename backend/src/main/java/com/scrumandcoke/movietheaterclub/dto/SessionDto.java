package com.scrumandcoke.movietheaterclub.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionDto {

    @JsonIgnore
    private String sessionId;

    private String userId;

    private Date expireAt;

    private Date lastUpdatedAt;

    private Date createdAt;
}

package com.scrumandcoke.movietheaterclub.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.scrumandcoke.movietheaterclub.enums.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocationDto {
    private int id;

    private String name;

    @JsonIgnore
    private Location location;

    private int theaterScreenCount;
}

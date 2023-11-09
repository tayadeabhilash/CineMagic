package com.scrumandcoke.movietheaterclub.dto;

import com.scrumandcoke.movietheaterclub.model.enums.Location;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MultiplexDto {
	private int id;
	private String name;
	private Location location;
	private int theaterscreencount;
}

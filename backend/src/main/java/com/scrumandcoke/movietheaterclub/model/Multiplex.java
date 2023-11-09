package com.scrumandcoke.movietheaterclub.model;

import com.scrumandcoke.movietheaterclub.model.enums.Location;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class Multiplex {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@Column(name="name")
	private String name;
	
	 @Enumerated(EnumType.STRING)
	 @Column(name = "location")
	 private Location location;

	 @Column(name="theaterscreencount")
	 private int theaterscreencount;
}

package com.scrumandcoke.movietheaterclub.entity;


import com.scrumandcoke.movietheaterclub.enums.Location;

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
@Table(name = "multiplex")
@Data
public class MultiplexEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "location")
    private Location location;

    @Column(name = "theater_screen_count")
    private int theaterScreenCount;

}

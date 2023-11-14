package com.scrumandcoke.movietheaterclub.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "theater_screens")
@Data
public class TheaterScreen {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
	
    @Column(name = "name")
	private String name;
    
    @ManyToOne
    @JoinColumn(name = "multiplex_id")
    private Multiplex multiplex;
		
    @Column(name = "seatingCapacity")
	private int seatingCapacity;
    
   
}

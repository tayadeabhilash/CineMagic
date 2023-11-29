package com.scrumandcoke.movietheaterclub.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "movies")
@Data
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Integer movieId;

    @Column(name = "movie_name")
    private String movieName;

    private String synopsis;

    @Column(name = "running_time")
    private Integer runningTime;

    @Column(name="poster")
    private String poster;

    @Column(name="genre")
    private String genre;

    @Column(name="language")
    private String language;

    @Column(name="release_date")
    private LocalDate releaseDate;
}

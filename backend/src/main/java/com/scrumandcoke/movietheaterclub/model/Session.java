package com.scrumandcoke.movietheaterclub.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "sessions")
@Data
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "createdAt")
    private Date createdAt;

    @Column(name = "expireAt")
    private Date expireAt;

    @Column(name = "lastUpdatedAt")
    private Date lastUpdatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

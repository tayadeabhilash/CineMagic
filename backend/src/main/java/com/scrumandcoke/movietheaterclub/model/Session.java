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

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "expire_at")
    private Date expireAt;

    @Column(name = "last_updated_at")
    private Date lastUpdatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
}

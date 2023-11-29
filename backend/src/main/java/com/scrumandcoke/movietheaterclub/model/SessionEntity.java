package com.scrumandcoke.movietheaterclub.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "sessions")
@Data
public class SessionEntity {

    @Id
    @Column(name = "session_id")
    private String sessionId;

    @Column(name = "user_id")
    String userId;

    @Column(name = "expire_at")
    private Date expireAt;

    @Column(name = "last_updated_at")
    @CreationTimestamp
    private Date lastUpdatedAt;

    @Column(name = "created_at")
    @UpdateTimestamp
    private Date createdAt;
}

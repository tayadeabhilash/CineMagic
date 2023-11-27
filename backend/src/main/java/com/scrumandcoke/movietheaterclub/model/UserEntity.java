package com.scrumandcoke.movietheaterclub.model;

import com.scrumandcoke.movietheaterclub.model.enums.MemberType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "users")
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "external_id")
    String externalId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_type")
    private MemberType memberType;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "last_updated_at")
    private Date lastUpdatedAt;
}

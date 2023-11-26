package com.scrumandcoke.movietheaterclub.model;

import com.scrumandcoke.movietheaterclub.model.enums.MemberType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "memberType")
    private MemberType memberType;

    @Column(name = "createdAt")
    private Date createdAt;

    @Column(name = "lastUpdatedAt")
    private Date lastUpdatedAt;
}

package com.example.cache.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "tbl_user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

}

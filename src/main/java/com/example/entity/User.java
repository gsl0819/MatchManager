package com.example.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int userid;
    String username;
    String password;
    String role;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {

    }
}

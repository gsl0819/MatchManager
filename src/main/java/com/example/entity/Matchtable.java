package com.example.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Matchtable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int matchid;
    int hometeam;
    int awayteam;
    String starttime;
    int matchtime;
    int winteam;
    String videoweb;

    public Matchtable() {
    }
}

package com.example_pgr.demo_pgr.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "MOVIES")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MOVIE")
    private Integer id_movie;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "DURATION")
    private int duration;
    @Column(name = "POSTER_URL")
    private String poster_url;
    @Column(name = "DIRECTOR")
    private String director;
    @Column(name = "MAIN_PROTAGONIST")
    private String main_protagonist;
    @Column(name = "PROTAGONIST")
    private String protagonist;
    @Column(name = "SYNOPSIS")
    private String synopsis;
}

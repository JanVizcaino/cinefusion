package com.example_pgr.demo_pgr.model;

import com.example_pgr.demo_pgr.model.Movie;
import com.example_pgr.demo_pgr.model.Room;
import com.example_pgr.demo_pgr.model.Cine;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SESSIONS")
public class Sesion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SESSION")
    private Integer id_session;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "DATE")
    private LocalDate date;

    @Column(name = "START_TIME")
    private LocalTime startTime;

    @Column(name = "END_TIME")
    private LocalTime endTime;

    @ManyToOne
    @JoinColumn(name = "NUM_ROOM", referencedColumnName = "NUM_ROOM")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "ID_MOVIE", referencedColumnName = "ID_MOVIE")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "ID_CINE", referencedColumnName = "ID_CINE")
    private Cine cine;
}

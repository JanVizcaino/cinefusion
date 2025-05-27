package com.example_pgr.demo_pgr.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionDTO {
    private int id_session;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private int num_room;
    private int id_movie;
    private int id_cine;
}


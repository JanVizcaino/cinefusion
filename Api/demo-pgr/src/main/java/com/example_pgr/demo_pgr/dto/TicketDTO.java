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
public class TicketDTO {
    private int id_ticket;
    private double price;
    private int id_buy;
    private int id_session;
    private int id_room;
    private int id_seat;
    private int id_cine;

}

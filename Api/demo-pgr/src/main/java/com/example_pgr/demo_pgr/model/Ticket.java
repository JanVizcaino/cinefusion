package com.example_pgr.demo_pgr.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TICKETS")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TICKET")
    private Integer id_ticket;
    @Column(name = "PRICE")
    private double price;

    @ManyToOne
    @JoinColumn(name = "ID_BUY", referencedColumnName = "ID_BUY")
    private Purchase purchase;

    @ManyToOne
    @JoinColumn(name = "ID_SESSION", referencedColumnName = "ID_SESSION")
    private Sesion session;



    @ManyToOne
    @JoinColumn(name = "ID_SEAT", referencedColumnName = "ID_SEAT")
    private Seat seat;
}
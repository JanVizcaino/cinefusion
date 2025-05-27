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
    @JoinColumn(name = "id_session", referencedColumnName = "id_session")
    private Sesion session;

    @ManyToOne
    @JoinColumn(name = "id_seat", referencedColumnName = "id_seat")
    private Seat seat;

}
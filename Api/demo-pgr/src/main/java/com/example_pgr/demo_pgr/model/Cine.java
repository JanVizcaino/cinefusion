package com.example_pgr.demo_pgr.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CINES")
public class Cine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CINE")
    private Integer id_cine;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ADDRESS")
    private String address;
}
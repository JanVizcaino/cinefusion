package com.example_pgr.demo_pgr.model;

import com.example_pgr.demo_pgr.services.CineService;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ROOMS")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NUM_ROOM")
    private Integer num_room;
    @Column(name = "CAPACITY")
    private int capacity;
    @ManyToOne
    @JoinColumn(name = "ID_CINE")
    private Cine cine;
    @Column(name = "MAX_ROWS")
    private int max_rows;
    @Column(name = "MAX_COLS")
    private int max_cols;
}

package com.example_pgr.demo_pgr.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SEATS")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SEAT")
    private Integer id_seat;
    @ManyToOne
    @JoinColumn(name = "NUM_ROOM", referencedColumnName = "NUM_ROOM", nullable = false)
    private Room room;
    @ManyToOne
    @JoinColumn(name = "ID_CINE",referencedColumnName = "ID_CINE", nullable = false)
    private Cine cine;
    @Column(name = "ROW_NUM")
    private int row_num;
    @Column(name = "COL_NUM")
    private int col_num;
}

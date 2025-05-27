package com.example_pgr.demo_pgr.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatDTO {
    private int id_seat;
    private int num_room;
    private int id_cine;
    private int row_num;
    private int col_num;
}

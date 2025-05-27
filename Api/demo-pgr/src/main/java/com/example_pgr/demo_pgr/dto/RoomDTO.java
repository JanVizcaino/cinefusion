package com.example_pgr.demo_pgr.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {
    private int num_room;
    private int capacity;
    private int id_cine;
    private int max_rows;
    private int max_cols;
}

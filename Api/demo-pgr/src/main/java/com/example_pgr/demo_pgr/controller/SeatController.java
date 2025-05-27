package com.example_pgr.demo_pgr.controller;

import com.example_pgr.demo_pgr.dto.SeatDTO;
import com.example_pgr.demo_pgr.dto.RoomDTO;
import com.example_pgr.demo_pgr.model.Cine;
import com.example_pgr.demo_pgr.model.Room;
import com.example_pgr.demo_pgr.model.Seat;
import com.example_pgr.demo_pgr.services.RoomService;
import com.example_pgr.demo_pgr.services.CineService;
import com.example_pgr.demo_pgr.services.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/seats")
public class SeatController {
    @Autowired
    private SeatService seatService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private CineService cineService;

    //OBTENER TODOS LO ASIENTOS
    @GetMapping
    public ResponseEntity<List<SeatDTO>> getAllSeats() {
        List<Seat> seats = seatService.listSeats();
        if (seats.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<SeatDTO> seatDTOS = seats.stream().map(seat -> {
            SeatDTO dto = new SeatDTO();
            dto.setId_seat(seat.getId_seat());
            dto.setNum_room(seat.getRoom() != null ? seat.getRoom().getNum_room() : 0);
            dto.setId_cine(seat.getCine() != null ? seat.getCine().getId_cine() : 0);
            dto.setRow_num(seat.getRow_num());
            dto.setCol_num(seat.getCol_num());
            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(seatDTOS);
    }

    //OBTENER ASIENTO POR ID
    @GetMapping("/{id}")
    public ResponseEntity<SeatDTO> findById(@PathVariable int id) {
        Seat seat = seatService.findById(id);
        if (seat == null) {
            return ResponseEntity.notFound().build();
        }
        SeatDTO dto = new SeatDTO();
        dto.setId_seat(seat.getId_seat());
        dto.setNum_room(seat.getRoom() != null ? seat.getRoom().getNum_room() : 0);
        dto.setId_cine(seat.getCine() != null ? seat.getCine().getId_cine() : 0);
        dto.setRow_num(seat.getRow_num());
        dto.setCol_num(seat.getCol_num());
        return ResponseEntity.ok(dto);
    }

    //CREAR UN NUEVO ASIENTO
    @PostMapping
    public ResponseEntity<SeatDTO> addSeat(@RequestBody SeatDTO seatDTO) {
        Cine cine = cineService.findById(seatDTO.getId_cine());
        if (cine == null) {
            return ResponseEntity.badRequest().body(null);
        }
        Room room = roomService.findById(seatDTO.getNum_room());
        if (room == null) {
            return ResponseEntity.badRequest().body(null);
        }
        Seat seat = new Seat();
        seat.setRoom(room);
        seat.setCine(cine);
        seat.setRow_num(seatDTO.getRow_num());
        seat.setCol_num(seatDTO.getCol_num());
        seatService.saveSeat(seat);
        return new ResponseEntity<>(seatDTO, HttpStatus.CREATED);
    }

    //ACTUALIZAR UN ASIENTO EXISTENTE
    @PutMapping("/{id}")
    public ResponseEntity<SeatDTO> updateSeat(@PathVariable int id, @RequestBody SeatDTO seatDTO) {
        Seat seat = seatService.findById(id);
        if (seat == null) {
            return ResponseEntity.notFound().build();
        }
        Cine cine = cineService.findById(seatDTO.getId_cine());
        if (cine == null) {
            return ResponseEntity.badRequest().body(null);
        }
        seat.setCine(cine);
        seat.setRow_num(seatDTO.getRow_num());
        seat.setCol_num(seatDTO.getCol_num());
        seatService.saveSeat(seat);
        return ResponseEntity.ok(seatDTO);
    }

    //ELIMINAR UN ASIENTO POR ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeat(@PathVariable int id) {
        Seat seat = seatService.findById(id);
        if (seat == null) {
            return ResponseEntity.notFound().build();
        }
        seatService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

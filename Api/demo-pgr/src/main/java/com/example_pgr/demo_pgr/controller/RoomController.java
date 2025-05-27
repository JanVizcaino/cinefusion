package com.example_pgr.demo_pgr.controller;

import com.example_pgr.demo_pgr.dto.EmployeeDTO;
import com.example_pgr.demo_pgr.dto.RoomDTO;
import com.example_pgr.demo_pgr.model.Employee;
import com.example_pgr.demo_pgr.model.Room;
import com.example_pgr.demo_pgr.model.Cine;
import com.example_pgr.demo_pgr.services.RoomService;
import com.example_pgr.demo_pgr.services.CineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @Autowired
    private CineService cineService;

    //OBTENER TODAS LAS SALAS
    @GetMapping
    public ResponseEntity<List<RoomDTO>> getAllRooms() {
        List<Room> rooms = roomService.listRoom();
        if (rooms.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<RoomDTO> roomDTOS = rooms.stream().map(room -> {
            RoomDTO dto = new RoomDTO();
            dto.setNum_room(room.getNum_room());
            dto.setCapacity(room.getCapacity());
            dto.setId_cine(room.getCine() != null ? room.getCine().getId_cine() : 0);
            dto.setMax_rows(room.getMax_rows());
            dto.setMax_cols(room.getMax_cols());
            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(roomDTOS);
    }

    //OBTENER SALA POR ID
    @GetMapping("/{id}")
    public ResponseEntity<RoomDTO> findById(@PathVariable int id) {
        Room room = roomService.findById(id);
        if (room == null) {
            return ResponseEntity.notFound().build();
        }
        RoomDTO dto = new RoomDTO();
        dto.setNum_room(room.getNum_room());
        dto.setCapacity(room.getCapacity());
        dto.setId_cine(room.getCine() != null ? room.getCine().getId_cine() : 0);
        dto.setMax_rows(room.getMax_rows());
        dto.setMax_cols(room.getMax_cols());
        return ResponseEntity.ok(dto);
    }

    //CREAR UNA NUEVA SALA
    @PostMapping
    public ResponseEntity<RoomDTO> addRoom(@RequestBody RoomDTO roomDTO) {
        Cine cine = cineService.findById(roomDTO.getId_cine());
        if (cine == null) {
            return ResponseEntity.badRequest().body(null);
        }
        Room room = new Room();
        room.setCapacity(roomDTO.getCapacity());
        room.setCine(cine);
        room.setMax_rows(roomDTO.getMax_rows());
        room.setMax_cols(roomDTO.getMax_cols());
        roomService.saveRoom(room);
        return new ResponseEntity<>(roomDTO, HttpStatus.CREATED);
    }

    //ACTUALIZAR UNA SALA EXXISTENTE
    @PutMapping("/{id}")
    public ResponseEntity<RoomDTO> updateRoom(@PathVariable int id, @RequestBody RoomDTO roomDTO) {
        Room room = roomService.findById(id);
        if (room == null) {
            return ResponseEntity.notFound().build();
        }
        Cine cine = cineService.findById(roomDTO.getId_cine());
        if (cine == null) {
            return ResponseEntity.badRequest().body(null);
        }
        room.setCapacity(roomDTO.getCapacity());
        room.setCine(cine);
        room.setMax_rows(roomDTO.getMax_rows());
        room.setMax_cols(roomDTO.getMax_cols());

        roomService.saveRoom(room);
        return ResponseEntity.ok(roomDTO);
    }

    //ELIMINAR UNA SALA POR ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable int id) {
        Room room = roomService.findById(id);
        if (room == null) {
            return ResponseEntity.notFound().build();
        }
        roomService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

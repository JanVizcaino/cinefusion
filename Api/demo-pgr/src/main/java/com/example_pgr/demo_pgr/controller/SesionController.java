package com.example_pgr.demo_pgr.controller;

import com.example_pgr.demo_pgr.model.Cine;
import com.example_pgr.demo_pgr.model.Movie;
import com.example_pgr.demo_pgr.model.Sesion;
import com.example_pgr.demo_pgr.dto.SessionDTO;
import com.example_pgr.demo_pgr.model.Room;
import com.example_pgr.demo_pgr.*;
import com.example_pgr.demo_pgr.services.CineService;
import com.example_pgr.demo_pgr.services.MovieService;
import com.example_pgr.demo_pgr.services.RoomService;
import com.example_pgr.demo_pgr.services.SesionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/sessions")
public class SesionController {
    @Autowired
    private SesionService sesionService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private MovieService movieService;
    @Autowired
    private CineService cineService;

    // OBTENER TODAS LAS SESIONES
    @GetMapping
    public ResponseEntity<List<SessionDTO>> getAllSesiones() {
        List<Sesion> sesiones = sesionService.listSesiones();
        if (sesiones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<SessionDTO> sessionDTOS = sesiones.stream().map(sesion -> {
            SessionDTO dto = new SessionDTO();
            dto.setId_session(sesion.getId_session());
            dto.setDate(sesion.getDate());
            dto.setStartTime(sesion.getStartTime());
            dto.setEndTime(sesion.getEndTime());
            dto.setNum_room(sesion.getRoom() != null ? sesion.getRoom().getNum_room() : 0);
            dto.setId_movie(sesion.getMovie() != null ? sesion.getMovie().getId_movie() : 0);
            dto.setId_cine(sesion.getCine() != null ? sesion.getCine().getId_cine() : 0);
            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(sessionDTOS);
    }

    // OBTENER UNA SESIÓN POR LA ID
    @GetMapping("/{id}")
    public ResponseEntity<SessionDTO> findById(@PathVariable int id) {
        Sesion sesion = sesionService.findById(id);
        if (sesion == null) {
            return ResponseEntity.notFound().build();
        }
        SessionDTO dto = new SessionDTO();
        dto.setId_session(sesion.getId_session());
        dto.setDate(sesion.getDate());
        dto.setStartTime(sesion.getStartTime());
        dto.setEndTime(sesion.getEndTime());
        dto.setNum_room(sesion.getRoom() != null ? sesion.getRoom().getNum_room() : 0);
        dto.setId_movie(sesion.getMovie() != null ? sesion.getMovie().getId_movie() : 0);
        dto.setId_cine(sesion.getCine() != null ? sesion.getCine().getId_cine() : 0);

        return ResponseEntity.ok(dto);
    }

    //CREAR UNA NUEVA SESION
    @PostMapping
    public ResponseEntity<SessionDTO> addSession(@RequestBody SessionDTO sessionDTO){
        Room room = roomService.findById(sessionDTO.getNum_room());
        Movie movie = movieService.findById(sessionDTO.getId_movie());
        Cine cine = cineService.findById(sessionDTO.getId_cine());

        if (room == null || movie == null || cine == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Sesion sesion = new Sesion();
        sesion.setDate(sessionDTO.getDate());
        sesion.setStartTime(sessionDTO.getStartTime());
        sesion.setEndTime(sessionDTO.getEndTime());
        sesion.setRoom(room);
        sesion.setMovie(movie);
        sesion.setCine(cine);

        sesionService.saveSesion(sesion);
        sessionDTO.setId_session(sesion.getId_session());

        return new ResponseEntity<>(sessionDTO, HttpStatus.CREATED);
    }

    //ACTUALIZAR UNA SESION EXISTENTE
    @PutMapping("/{id}")
    public ResponseEntity<SessionDTO> updateSesion(@PathVariable int id, @RequestBody SessionDTO sesionDTO) {
        Sesion sesion = sesionService.findById(id);
        if (sesion == null) {
            return ResponseEntity.notFound().build();
        }
        Room room = roomService.findById(sesionDTO.getNum_room());
        Movie movie = movieService.findById(sesionDTO.getId_movie());
        Cine cine = cineService.findById(sesionDTO.getId_cine());

        if (room == null || movie == null || cine == null) {
            return ResponseEntity.badRequest().body(null);
        }
        sesion.setDate(sesionDTO.getDate());
        sesion.setStartTime(sesionDTO.getStartTime());
        sesion.setEndTime(sesionDTO.getEndTime());
        sesion.setRoom(room);
        sesion.setMovie(movie);
        sesion.setCine(cine);
        sesionService.saveSesion(sesion);
        return ResponseEntity.ok(sesionDTO);
    }

    //ELIMINAR UNA SESION POR ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSesion(@PathVariable int id) {
        Sesion sesion = sesionService.findById(id);
        if (sesion == null) {
            return ResponseEntity.notFound().build();
        }
        sesionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

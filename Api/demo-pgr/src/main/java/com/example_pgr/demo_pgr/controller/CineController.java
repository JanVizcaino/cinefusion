package com.example_pgr.demo_pgr.controller;

import com.example_pgr.demo_pgr.model.Cine ;
import com.example_pgr.demo_pgr.model.User;
import com.example_pgr.demo_pgr.repository.CineRepository;
import com.example_pgr.demo_pgr.services.CineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/cines")
public class CineController {

    @Autowired
    private CineService cineService;

    //OBTENER TODOS LOS CINES
    @GetMapping
    public ResponseEntity<List<Cine>> getAllCines() {
        List<Cine> cines = cineService.listCines();
        return ResponseEntity.ok(cines);
    }

    //OBTENER CINE PO LA ID
    @GetMapping("/{id}")
    public ResponseEntity<Cine> findById(@PathVariable int id) {
        Cine cine = cineService.findById(id);
        if (cine == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cine);
    }

    //CREAR UN NUEVO CINE
    @PostMapping
    public ResponseEntity<Cine> addCine(@RequestBody Cine cine) {
        try {
            cineService.saveCine(cine);
            return new ResponseEntity<>(cine, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    //ACTUALIZAR CINE QUE YA EXISTE
    @PutMapping("/{id}")
    public ResponseEntity<Cine> updateCine(@PathVariable int id, @RequestBody Cine cine) {
        try {
            Cine currentCine = cineService.findById(id);
            if (currentCine == null) {
                return ResponseEntity.notFound().build();
            }
            currentCine.setName(cine.getName());
            currentCine.setAddress(cine.getAddress());

            cineService.saveCine(currentCine);
            return ResponseEntity.ok(currentCine);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    //ELIMINAR CINE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCine(@PathVariable int id) {
        try {
            cineService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

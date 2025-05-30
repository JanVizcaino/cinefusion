package com.example_pgr.demo_pgr.services;

import com.example_pgr.demo_pgr.model.Sesion;
import com.example_pgr.demo_pgr.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class SesionService {
    @Autowired
    private SesionRepository sesionRepository;

    public List<Sesion> listSesiones() { return sesionRepository.findAll();}

    public Sesion findById(Integer id) { return sesionRepository.findById(id).orElse(null);}

    public Sesion saveSesion(Sesion sesion) { return sesionRepository.save(sesion);}

    public boolean deleteById(Integer id) {
        if (sesionRepository.existsById(id))
        {
            sesionRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

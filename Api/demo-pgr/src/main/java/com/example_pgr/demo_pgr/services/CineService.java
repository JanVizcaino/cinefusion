package com.example_pgr.demo_pgr.services;

import com.example_pgr.demo_pgr.model.Cine;
import com.example_pgr.demo_pgr.model.User;
import com.example_pgr.demo_pgr.repository.CineRepository;
import com.example_pgr.demo_pgr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CineService {
    @Autowired
    private CineRepository cineRepository;

    public List<Cine> listCines() { return cineRepository.findAll();}

    public Cine findById(Integer id) { return cineRepository.findById(id).orElse(null);}

    public Cine saveCine(Cine cine) { return cineRepository.save(cine);}

    public boolean deleteById(Integer id) {
        if (cineRepository.existsById(id)) {
            cineRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

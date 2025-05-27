package com.example_pgr.demo_pgr.services;

import com.example_pgr.demo_pgr.model.Seat;
import com.example_pgr.demo_pgr.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;

    public List<Seat> listSeats() {
        return seatRepository.findAll();
    }
    public Seat findById(Integer id) {
        return seatRepository.findById(id).orElse(null);
    }
    public Seat saveSeat(Seat seat) {
        return seatRepository.save(seat);
    }
    public boolean deleteById(Integer id) {
        if (seatRepository.existsById(id)) {
            seatRepository.deleteById(id);
            return true;
        }
        return false;
    }

}

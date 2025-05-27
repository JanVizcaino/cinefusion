package com.example_pgr.demo_pgr.services;

import com.example_pgr.demo_pgr.model.Room;
import com.example_pgr.demo_pgr.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    public List<Room> listRoom() {
        return roomRepository.findAll();
    }
    public Room findById(Integer id) {
        return roomRepository.findById(id).orElse(null);
    }
    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }
    public boolean deleteById(Integer id) {
        if (roomRepository.existsById(id)) {
            roomRepository.deleteById(id);
            return true;
        }
        return false;
    }

}

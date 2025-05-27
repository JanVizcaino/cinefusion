package com.example_pgr.demo_pgr.repository;

import com.example_pgr.demo_pgr.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
public interface RoomRepository extends JpaRepository<Room, Integer> {
}

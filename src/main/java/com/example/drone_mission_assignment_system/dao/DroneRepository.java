package com.example.drone_mission_assignment_system.dao;
import com.example.drone_mission_assignment_system.entity.Drone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneRepository extends JpaRepository<Drone, Long> {
}

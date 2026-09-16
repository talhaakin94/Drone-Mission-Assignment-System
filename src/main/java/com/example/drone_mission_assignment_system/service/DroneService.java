package com.example.drone_mission_assignment_system.service;
import com.example.drone_mission_assignment_system.dto.DroneRequest;
import com.example.drone_mission_assignment_system.entity.Drone;
import java.util.List;

public interface DroneService {
    Drone create(DroneRequest droneRequest);
    List<Drone> getAll();
    Drone getById(Long id);
    Drone update(Long id, DroneRequest droneRequest);
    Drone remove(Long id);
}

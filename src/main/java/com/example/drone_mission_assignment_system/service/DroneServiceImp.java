package com.example.drone_mission_assignment_system.service;
import com.example.drone_mission_assignment_system.dao.DroneRepository;
import com.example.drone_mission_assignment_system.dto.DroneRequest;
import com.example.drone_mission_assignment_system.entity.Drone;
import com.example.drone_mission_assignment_system.exception.SystemException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DroneServiceImp implements DroneService{
    private final DroneRepository droneRepository;
    public DroneServiceImp(DroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }
    @Override
    public Drone create(DroneRequest droneRequest) {
        Drone drone = new Drone();
        drone.setName(droneRequest.name());
        drone.setDroneStatus(droneRequest.droneStatus());
        return droneRepository.save(drone);
    }
    @Override
    public List<Drone> getAll() {
        return droneRepository.findAll();
    }
    @Override
    public Drone getById(Long id) {
        return droneRepository.findById(id).orElseThrow(() -> new SystemException("Drone not found.", HttpStatus.NOT_FOUND));
    }
    @Override
    public Drone update(Long id, DroneRequest droneRequest) {
        Drone drone = droneRepository.findById(id).orElseThrow(() -> new SystemException("Drone to update not found.", HttpStatus.NOT_FOUND));
        drone.setName(droneRequest.name());
        drone.setDroneStatus(droneRequest.droneStatus());
        return droneRepository.save(drone);
    }
    @Override
    public Drone remove(Long id) {
        Drone drone = droneRepository.findById(id).orElseThrow(() -> new SystemException("Drone to remove not found.", HttpStatus.NOT_FOUND));
        drone.getOperators().forEach(operator -> operator.getDrones().removeIf(drone::equals));
        droneRepository.delete(drone);
        return drone;
    }
}

package com.example.drone_mission_assignment_system.service;
import com.example.drone_mission_assignment_system.dao.DroneRepository;
import com.example.drone_mission_assignment_system.dto.DroneRequest;
import com.example.drone_mission_assignment_system.entity.Drone;
import com.example.drone_mission_assignment_system.entity.DroneStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DroneServiceImpTest {
    @InjectMocks
    private DroneServiceImp droneService;
    @Mock
    private DroneRepository droneRepository;
    @Test
    void create() {
        DroneRequest droneRequest = new DroneRequest("Reconnaissance Drone 01", DroneStatus.AVAILABLE);
        given(droneRepository.save(any(Drone.class))).willReturn(new Drone());
        droneService.create(droneRequest);
        verify(droneRepository).save(any(Drone.class));
    }
    @Test
    void getAll() {
        List<Drone> drones = new ArrayList<>();
        given(droneRepository.findAll()).willReturn(drones);
        droneService.getAll();
        verify(droneRepository).findAll();
    }
    @Test
    void getById() {
        Drone drone = new Drone();
        drone.setId(1L);
        given(droneRepository.findById(1L)).willReturn(Optional.of(drone));
        droneService.getById(1L);
        verify(droneRepository).findById(1L);
    }
    @Test
    void update() {
        Drone drone = new Drone();
        drone.setId(1L);
        DroneRequest droneRequest = new DroneRequest("Mapping Drone 01", DroneStatus.ACTIVE);
        given(droneRepository.findById(1L)).willReturn(Optional.of(drone));
        given(droneRepository.save(drone)).willReturn(drone);
        droneService.update(1L, droneRequest);
        verify(droneRepository).findById(1L);
        verify(droneRepository).save(drone);
    }
    @Test
    void delete() {
        Drone drone = new Drone();
        drone.setId(1L);
        given(droneRepository.findById(1L)).willReturn(Optional.of(drone));
        droneService.remove(1L);
        verify(droneRepository).findById(1L);
        verify(droneRepository).delete(drone);
    }
}
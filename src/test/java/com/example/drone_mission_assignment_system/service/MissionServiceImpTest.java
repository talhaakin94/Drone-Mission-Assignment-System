package com.example.drone_mission_assignment_system.service;
import com.example.drone_mission_assignment_system.dao.MissionRepository;
import com.example.drone_mission_assignment_system.dto.MissionRequest;
import com.example.drone_mission_assignment_system.entity.*;
import com.example.drone_mission_assignment_system.exception.SystemException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MissionServiceImpTest {
    @InjectMocks
    private MissionServiceImp missionService;
    @Mock
    private MissionRepository missionRepository;
    @Test
    void create() {
        Drone drone = new Drone();
        Operator operator = new Operator();
        MissionRequest missionRequest = new MissionRequest(
                "Medication Delivery",
                "Urgent delivery of medicines",
                35.6762,
                139.6503,
                100.0,
                MissionPriority.HIGH,
                MissionStatus.ACTIVE,
                drone,
                operator);
        given(missionRepository.save(any(Mission.class))).willReturn(new Mission());
        missionService.create(missionRequest);
        verify(missionRepository).save(any(Mission.class));
    }
    @Test
    @DisplayName("Shouldn't assign a mission to a drone in maintenance.")
    void createRuleOne() {
        Drone drone = new Drone();
        Operator operator = new Operator();
        drone.setDroneStatus(DroneStatus.MAINTENANCE);
        MissionRequest missionRequest = new MissionRequest(
                "Medication Delivery",
                "Urgent delivery of medicines",
                35.6762,
                139.6503,
                100.0,
                MissionPriority.HIGH,
                MissionStatus.ACTIVE,
                drone,
                operator);
        assertThrows(SystemException.class, () -> missionService.create(missionRequest));
        verify(missionRepository, never()).save(any(Mission.class));
    }
    @Test
    @DisplayName("Shouldn't assign a mission to an active drone.")
    void createRuleTwo() {
        Drone drone = new Drone();
        Operator operator = new Operator();
        drone.setDroneStatus(DroneStatus.ACTIVE);
        MissionRequest missionRequest = new MissionRequest(
                "Medication Delivery",
                "Urgent delivery of medicines",
                35.6762,
                139.6503,
                100.0,
                MissionPriority.HIGH,
                MissionStatus.ACTIVE,
                drone,
                operator);
        assertThrows(SystemException.class, () -> missionService.create(missionRequest));
        verify(missionRepository, never()).save(any(Mission.class));
    }
    @Test
    @DisplayName("Shouldn't create a cancelled mission.")
    void createRuleThree() {
        Drone drone = new Drone();
        Operator operator = new Operator();
        MissionRequest missionRequest = new MissionRequest(
                "Medication Delivery",
                "Urgent delivery of medicines",
                35.6762,
                139.6503,
                100.0,
                MissionPriority.HIGH,
                MissionStatus.CANCELLED,
                drone,
                operator);
        assertThrows(SystemException.class, () -> missionService.create(missionRequest));
        verify(missionRepository, never()).save(any(Mission.class));
    }
    @Test
    @DisplayName("Shouldn't create a finished mission.")
    void createRuleFour() {
        Drone drone = new Drone();
        Operator operator = new Operator();
        MissionRequest missionRequest = new MissionRequest(
                "Medication Delivery",
                "Urgent delivery of medicines",
                35.6762,
                139.6503,
                100.0,
                MissionPriority.HIGH,
                MissionStatus.FINISHED,
                drone,
                operator);
        assertThrows(SystemException.class, () -> missionService.create(missionRequest));
        verify(missionRepository, never()).save(any(Mission.class));
    }
    @Test
    void getAll() {
        List<Mission> missions = new ArrayList<>();
        given(missionRepository.findAll()).willReturn(missions);
        missionService.getAll();
        verify(missionRepository).findAll();
    }
    @Test
    void getById() {
        Mission mission = new Mission();
        mission.setId(1L);
        given(missionRepository.findById(1L)).willReturn(Optional.of(mission));
        missionService.getById(1L);
        verify(missionRepository).findById(1L);
    }
    @Test
    void update() {
        Drone drone = new Drone();
        Operator operator = new Operator();
        Mission mission = new Mission();
        mission.setId(1L);
        mission.setDrone(drone);
        MissionRequest missionRequest = new MissionRequest(
                "Medication Delivery",
                "Urgent delivery of medicines",
                35.6762,
                139.6503,
                100.0,
                MissionPriority.HIGH,
                MissionStatus.FINISHED,
                drone,
                operator);
        given(missionRepository.findById(1L)).willReturn(Optional.of(mission));
        given(missionRepository.save(mission)).willReturn(mission);
        missionService.update(1L, missionRequest);
        verify(missionRepository).findById(1L);
        verify(missionRepository).save(mission);
    }
    @Test
    @DisplayName("Shouldn't activate a cancelled mission.")
    void updateRuleOne() {
        Drone drone = new Drone();
        Operator operator = new Operator();
        Mission mission = new Mission();
        mission.setId(1L);
        mission.setDrone(drone);
        mission.setMissionStatus(MissionStatus.CANCELLED);
        MissionRequest missionRequest = new MissionRequest(
                "Medication Delivery",
                "Urgent delivery of medicines",
                35.6762,
                139.6503,
                100.0,
                MissionPriority.HIGH,
                MissionStatus.ACTIVE,
                drone,
                operator);
        given(missionRepository.findById(1L)).willReturn(Optional.of(mission));
        assertThrows(SystemException.class, () -> missionService.update(1L, missionRequest));
        verify(missionRepository, never()).save(any(Mission.class));
    }
    @Test
    @DisplayName("Shouldn't activate a finished mission.")
    void updateRuleTwo() {
        Drone drone = new Drone();
        Operator operator = new Operator();
        Mission mission = new Mission();
        mission.setId(1L);
        mission.setDrone(drone);
        mission.setMissionStatus(MissionStatus.FINISHED);
        MissionRequest missionRequest = new MissionRequest(
                "Medication Delivery",
                "Urgent delivery of medicines",
                35.6762,
                139.6503,
                100.0,
                MissionPriority.HIGH,
                MissionStatus.ACTIVE,
                drone,
                operator);
        given(missionRepository.findById(1L)).willReturn(Optional.of(mission));
        assertThrows(SystemException.class, () -> missionService.update(1L, missionRequest));
        verify(missionRepository, never()).save(any(Mission.class));
    }
    @Test
    @DisplayName("Shouldn't cancel a finished mission.")
    void updateRuleThree() {
        Drone drone = new Drone();
        Operator operator = new Operator();
        Mission mission = new Mission();
        mission.setId(1L);
        mission.setDrone(drone);
        mission.setMissionStatus(MissionStatus.FINISHED);
        MissionRequest missionRequest = new MissionRequest(
                "Medication Delivery",
                "Urgent delivery of medicines",
                35.6762,
                139.6503,
                100.0,
                MissionPriority.HIGH,
                MissionStatus.CANCELLED,
                drone,
                operator);
        given(missionRepository.findById(1L)).willReturn(Optional.of(mission));
        assertThrows(SystemException.class, () -> missionService.update(1L, missionRequest));
        verify(missionRepository, never()).save(any(Mission.class));
    }
    @Test
    @DisplayName("Shouldn't activate a drone in maintenance.")
    void updateRuleFour() {
        Drone drone = new Drone();
        drone.setDroneStatus(DroneStatus.MAINTENANCE);
        Operator operator = new Operator();
        Mission mission = new Mission();
        mission.setId(1L);
        mission.setDrone(drone);
        MissionRequest missionRequest = new MissionRequest(
                "Medication Delivery",
                "Urgent delivery of medicines",
                35.6762,
                139.6503,
                100.0,
                MissionPriority.HIGH,
                MissionStatus.ACTIVE,
                drone,
                operator);
        given(missionRepository.findById(1L)).willReturn(Optional.of(mission));
        assertThrows(SystemException.class, () -> missionService.update(1L, missionRequest));
        verify(missionRepository, never()).save(any(Mission.class));
    }
    @Test
    void delete() {
        Drone drone = new Drone();
        Operator operator = new Operator();
        Mission mission = new Mission();
        mission.setId(1L);
        mission.setDrone(drone);
        mission.setOperator(operator);
        given(missionRepository.findById(1L)).willReturn(Optional.of(mission));
        missionService.remove(1L);
        verify(missionRepository).delete(mission);
    }
}
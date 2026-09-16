package com.example.drone_mission_assignment_system.service;
import com.example.drone_mission_assignment_system.dto.MissionRequest;
import com.example.drone_mission_assignment_system.entity.Mission;
import java.util.List;

public interface MissionService {
    Mission create(MissionRequest missionRequest);
    List<Mission> getAll();
    Mission getById(Long id);
    Mission update(Long id, MissionRequest missionRequest);
    Mission remove(Long id);
}

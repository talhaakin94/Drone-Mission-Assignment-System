package com.example.drone_mission_assignment_system.dao;
import com.example.drone_mission_assignment_system.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {
}

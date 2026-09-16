package com.example.drone_mission_assignment_system.dao;
import com.example.drone_mission_assignment_system.entity.Operator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperatorRepository extends JpaRepository<Operator, Long> {
}

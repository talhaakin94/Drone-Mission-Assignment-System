package com.example.drone_mission_assignment_system.service;
import com.example.drone_mission_assignment_system.dto.OperatorRequest;
import com.example.drone_mission_assignment_system.entity.Operator;
import java.util.List;

public interface OperatorService {
    Operator create(OperatorRequest operatorRequest);
    List<Operator> getAll();
    Operator getById(Long id);
    Operator update(Long id, OperatorRequest operatorRequest);
    Operator remove(Long id);
}

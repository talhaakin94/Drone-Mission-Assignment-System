package com.example.drone_mission_assignment_system.service;
import com.example.drone_mission_assignment_system.dao.OperatorRepository;
import com.example.drone_mission_assignment_system.dto.OperatorRequest;
import com.example.drone_mission_assignment_system.entity.Operator;
import com.example.drone_mission_assignment_system.exception.SystemException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OperatorServiceImp implements OperatorService{
    private final OperatorRepository operatorRepository;
    public OperatorServiceImp(OperatorRepository operatorRepository) {
        this.operatorRepository = operatorRepository;
    }
    @Override
    public Operator create(OperatorRequest operatorRequest) {
        Operator operator = new Operator();
        operator.setName(operatorRequest.name());
        return operatorRepository.save(operator);
    }
    @Override
    public List<Operator> getAll() {
        return operatorRepository.findAll();
    }
    @Override
    public Operator getById(Long id) {
        return operatorRepository.findById(id).orElseThrow(() -> new SystemException("Operator not found.", HttpStatus.NOT_FOUND));
    }
    @Override
    public Operator update(Long id, OperatorRequest operatorRequest) {
        Operator operator = operatorRepository.findById(id).orElseThrow(() -> new SystemException("Operator to update not found.", HttpStatus.NOT_FOUND));
        operator.setName(operatorRequest.name());
        return operatorRepository.save(operator);
    }
    @Override
    public Operator remove(Long id) {
        Operator operator = operatorRepository.findById(id).orElseThrow(() -> new SystemException("Operator to remove not found.", HttpStatus.NOT_FOUND));
        operator.getDrones().forEach(drone -> drone.getOperators().removeIf(operator::equals));
        operatorRepository.delete(operator);
        return operator;
    }
}

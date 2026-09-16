package com.example.drone_mission_assignment_system.service;
import com.example.drone_mission_assignment_system.dao.OperatorRepository;
import com.example.drone_mission_assignment_system.dto.OperatorRequest;
import com.example.drone_mission_assignment_system.entity.Operator;
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
class OperatorServiceImpTest {
    @InjectMocks
    private OperatorServiceImp operatorService;
    @Mock
    private OperatorRepository operatorRepository;
    @Test
    void create() {
        OperatorRequest operatorRequest = new OperatorRequest("Olivia Bennett");
        given(operatorRepository.save(any(Operator.class))).willReturn(new Operator());
        operatorService.create(operatorRequest);
        verify(operatorRepository).save(any(Operator.class));
    }
    @Test
    void getAll() {
        List<Operator> operators = new ArrayList<>();
        given(operatorRepository.findAll()).willReturn(operators);
        operatorService.getAll();
        verify(operatorRepository).findAll();
    }
    @Test
    void getById() {
        Operator operator = new Operator();
        operator.setId(1L);
        given(operatorRepository.findById(1L)).willReturn(Optional.of(operator));
        operatorService.getById(1L);
        verify(operatorRepository).findById(1L);
    }
    @Test
    void update() {
        Operator operator = new Operator();
        operator.setId(1L);
        OperatorRequest operatorRequest = new OperatorRequest("Sophia Mitchell");
        given(operatorRepository.findById(1L)).willReturn(Optional.of(operator));
        given(operatorRepository.save(operator)).willReturn(operator);
        operatorService.update(1L, operatorRequest);
        verify(operatorRepository).findById(1L);
        verify(operatorRepository).save(operator);
    }
    @Test
    void delete() {
        Operator operator = new Operator();
        operator.setId(1L);
        given(operatorRepository.findById(1L)).willReturn(Optional.of(operator));
        operatorService.remove(1L);
        verify(operatorRepository).findById(1L);
        verify(operatorRepository).delete(operator);
    }
}
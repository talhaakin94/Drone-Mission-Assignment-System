package com.example.drone_mission_assignment_system.controller;
import com.example.drone_mission_assignment_system.dto.OperatorRequest;
import com.example.drone_mission_assignment_system.dto.OperatorResponse;
import com.example.drone_mission_assignment_system.entity.Operator;
import com.example.drone_mission_assignment_system.service.OperatorService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Validated
@RequestMapping("/operator")
public class OperatorController {
    private final OperatorService operatorService;
    public OperatorController(OperatorService operatorService) {
        this.operatorService = operatorService;
    }
    @Operation(summary = "Create an operator.")
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public OperatorResponse create(@Valid @RequestBody OperatorRequest operatorRequest) {
        Operator operator = operatorService.create(operatorRequest);
        return new OperatorResponse(operator.getName());
    }
    @Operation(summary = "Get all operators.")
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<OperatorResponse> getAll() {
        return operatorService.getAll().stream().map(operator -> new OperatorResponse(operator.getName())).toList();
    }
    @Operation(summary = "Get an operator by id.")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OperatorResponse getById(@Positive @PathVariable Long id) {
        Operator operator = operatorService.getById(id);
        return new OperatorResponse(operator.getName());
    }
    @Operation(summary = "Update an operator by id.")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OperatorResponse update(@Positive @PathVariable Long id, @Valid @RequestBody OperatorRequest operatorRequest) {
        Operator operator = operatorService.update(id, operatorRequest);
        return new OperatorResponse(operator.getName());
    }
    @Operation(summary = "Delete an operator by id.")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OperatorResponse remove(@Positive @PathVariable long id) {
        Operator operator = operatorService.remove(id);
        return new OperatorResponse(operator.getName());
    }
}

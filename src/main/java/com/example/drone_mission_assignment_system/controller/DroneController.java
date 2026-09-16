package com.example.drone_mission_assignment_system.controller;
import com.example.drone_mission_assignment_system.dto.DroneRequest;
import com.example.drone_mission_assignment_system.dto.DroneResponse;
import com.example.drone_mission_assignment_system.entity.Drone;
import com.example.drone_mission_assignment_system.service.DroneService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Validated
@RequestMapping("/drone")
public class DroneController {
    private final DroneService droneService;
    public DroneController(DroneService droneService) {
        this.droneService = droneService;
    }
    @Operation(summary = "Create a drone.")
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public DroneResponse create(@Valid @RequestBody DroneRequest droneRequest) {
        Drone drone = droneService.create(droneRequest);
        return new DroneResponse(drone.getName(), drone.getDroneStatus(), drone.getMissions(), drone.getOperators());
    }
    @Operation(summary = "Get all drones.")
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<DroneResponse> getAll() {
        return droneService.getAll().stream().map(drone -> new DroneResponse(drone.getName(), drone.getDroneStatus(), drone.getMissions(), drone.getOperators())).toList();
    }
    @Operation(summary = "Get a drone by id.")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DroneResponse getById(@Positive @PathVariable Long id) {
        Drone drone = droneService.getById(id);
        return new DroneResponse(drone.getName(), drone.getDroneStatus(), drone.getMissions(), drone.getOperators());
    }
    @Operation(summary = "Update a drone by id.")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DroneResponse update(@Positive @PathVariable Long id, @Valid @RequestBody DroneRequest droneRequest) {
        Drone drone = droneService.update(id, droneRequest);
        return new DroneResponse(drone.getName(), drone.getDroneStatus(), drone.getMissions(), drone.getOperators());
    }
    @Operation(summary = "Delete a drone by id.")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DroneResponse remove(@Positive @PathVariable Long id) {
        Drone drone = droneService.remove(id);
        return new DroneResponse(drone.getName(), drone.getDroneStatus(), drone.getMissions(), drone.getOperators());
    }
}

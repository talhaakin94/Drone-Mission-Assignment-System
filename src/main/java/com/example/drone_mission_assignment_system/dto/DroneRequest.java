package com.example.drone_mission_assignment_system.dto;
import com.example.drone_mission_assignment_system.entity.DroneStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DroneRequest(@Schema(description = "Drone name") @NotBlank String name, @Schema(description = "Drone status") @NotNull DroneStatus droneStatus) {
}

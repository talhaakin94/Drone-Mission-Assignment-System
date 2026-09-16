package com.example.drone_mission_assignment_system.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record OperatorRequest(@Schema(description = "Operator name") @NotBlank String name) {
}

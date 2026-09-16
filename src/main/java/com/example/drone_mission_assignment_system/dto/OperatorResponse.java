package com.example.drone_mission_assignment_system.dto;
import io.swagger.v3.oas.annotations.media.Schema;

public record OperatorResponse(@Schema(description = "Operator name") String name) {
}

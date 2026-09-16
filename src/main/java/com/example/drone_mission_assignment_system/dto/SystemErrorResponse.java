package com.example.drone_mission_assignment_system.dto;
import io.swagger.v3.oas.annotations.media.Schema;

public record SystemErrorResponse(@Schema(description = "System error message") String message, @Schema(description = "Http status code") int status) {
}

package com.example.drone_mission_assignment_system.dto;
import com.example.drone_mission_assignment_system.entity.Drone;
import com.example.drone_mission_assignment_system.entity.MissionPriority;
import com.example.drone_mission_assignment_system.entity.MissionStatus;
import com.example.drone_mission_assignment_system.entity.Operator;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MissionRequest(@Schema(description = "Mission name") @NotBlank String name,
                             @Schema(description = "Mission description") String description,
                             @Schema(description = "Mission latitude") @NotNull Double latitude,
                             @Schema(description = "Mission longitude") @NotNull Double longitude,
                             @Schema(description = "Mission altitude") @NotNull Double altitude,
                             @Schema(description = "Mission priority") @NotNull MissionPriority missionPriority,
                             @Schema(description = "Mission status") @NotNull MissionStatus missionStatus,
                             @Schema(description = "Mission drone") @NotNull Drone drone,
                             @Schema(description = "Mission operator") @NotNull Operator operator) {
}

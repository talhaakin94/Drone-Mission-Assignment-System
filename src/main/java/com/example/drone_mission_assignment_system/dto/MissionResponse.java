package com.example.drone_mission_assignment_system.dto;
import com.example.drone_mission_assignment_system.entity.Drone;
import com.example.drone_mission_assignment_system.entity.MissionPriority;
import com.example.drone_mission_assignment_system.entity.MissionStatus;
import com.example.drone_mission_assignment_system.entity.Operator;
import io.swagger.v3.oas.annotations.media.Schema;

public record MissionResponse(@Schema(description = "Mission name") String name,
                              @Schema(description = "Mission description") String description,
                              @Schema(description = "Mission latitude") Double latitude,
                              @Schema(description = "Mission longitude") Double longitude,
                              @Schema(description = "Mission altitude") Double altitude,
                              @Schema(description = "Mission priority") MissionPriority missionPriority,
                              @Schema(description = "Mission status") MissionStatus missionStatus,
                              @Schema(description = "Mission drone") Drone drone,
                              @Schema(description = "Mission operator") Operator operator) {
}

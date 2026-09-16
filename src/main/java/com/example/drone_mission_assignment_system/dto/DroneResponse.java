package com.example.drone_mission_assignment_system.dto;
import com.example.drone_mission_assignment_system.entity.DroneStatus;
import com.example.drone_mission_assignment_system.entity.Mission;
import com.example.drone_mission_assignment_system.entity.Operator;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public record DroneResponse(@Schema(description = "Drone name") String name, @Schema(description = "Drone status") DroneStatus droneStatus, @Schema(description = "Drone missions") List<Mission> missions, @Schema(description = "Drone operators") List<Operator> operators) {
}

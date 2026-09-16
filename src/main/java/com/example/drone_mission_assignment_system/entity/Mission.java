package com.example.drone_mission_assignment_system.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mission")
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    private String description;
    @NotNull
    private Double latitude;
    @NotNull
    private Double longitude;
    @NotNull
    private Double altitude;
    @NotNull
    private MissionPriority missionPriority;
    @NotNull
    private MissionStatus missionStatus;
    @ManyToOne
    @JoinColumn(name = "drone_id")
    @NotNull
    private Drone drone;
    @ManyToOne
    @JoinColumn(name = "operator_id")
    @NotNull
    private Operator operator;
}

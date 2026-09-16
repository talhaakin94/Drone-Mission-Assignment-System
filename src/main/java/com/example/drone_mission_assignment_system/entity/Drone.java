package com.example.drone_mission_assignment_system.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "drone")
public class Drone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @NotNull
    private DroneStatus droneStatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drone")
    private List<Mission> missions = new ArrayList<>();
    @ManyToMany
    @JoinTable(name = "drone_operator", joinColumns = @JoinColumn(name = "drone_id"), inverseJoinColumns = @JoinColumn(name = "operator_id"))
    private List<Operator> operators = new ArrayList<>();
}

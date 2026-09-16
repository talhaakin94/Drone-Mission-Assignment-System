package com.example.drone_mission_assignment_system.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "operator")
public class Operator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @ManyToMany(mappedBy = "operators")
    private List<Drone> drones = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "operator")
    private List<Mission> missions = new ArrayList<>();
}

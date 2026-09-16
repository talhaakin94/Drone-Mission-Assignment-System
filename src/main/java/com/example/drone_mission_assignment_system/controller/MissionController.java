package com.example.drone_mission_assignment_system.controller;
import com.example.drone_mission_assignment_system.dto.MissionRequest;
import com.example.drone_mission_assignment_system.dto.MissionResponse;
import com.example.drone_mission_assignment_system.entity.Mission;
import com.example.drone_mission_assignment_system.service.MissionService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Validated
@RequestMapping("/mission")
public class MissionController {
    private final MissionService missionService;
    public MissionController(MissionService missionService) {
        this.missionService = missionService;
    }
    @Operation(summary = "Create a mission.")
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public MissionResponse create(@Valid @RequestBody MissionRequest missionRequest) {
        Mission mission = missionService.create(missionRequest);
        return new MissionResponse(
                mission.getName(),
                mission.getDescription(),
                mission.getLatitude(),
                mission.getLongitude(),
                mission.getAltitude(),
                mission.getMissionPriority(),
                mission.getMissionStatus(),
                mission.getDrone(),
                mission.getOperator());
    }
    @Operation(summary = "Get all missions.")
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<MissionResponse> getAll() {
        return missionService.getAll().stream().map(mission -> new MissionResponse(
                mission.getName(),
                mission.getDescription(),
                mission.getLatitude(),
                mission.getLongitude(),
                mission.getAltitude(),
                mission.getMissionPriority(),
                mission.getMissionStatus(),
                mission.getDrone(),
                mission.getOperator())).toList();
    }
    @Operation(summary = "Get a mission by id.")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MissionResponse getById(@Positive @PathVariable Long id) {
        Mission mission = missionService.getById(id);
        return new MissionResponse(
                mission.getName(),
                mission.getDescription(),
                mission.getLatitude(),
                mission.getLongitude(),
                mission.getAltitude(),
                mission.getMissionPriority(),
                mission.getMissionStatus(),
                mission.getDrone(),
                mission.getOperator());
    }
    @Operation(summary = "Update a mission by id.")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MissionResponse update(@Positive @PathVariable Long id, @Valid @RequestBody MissionRequest missionRequest) {
        Mission mission = missionService.update(id, missionRequest);
        return new MissionResponse(
                mission.getName(),
                mission.getDescription(),
                mission.getLatitude(),
                mission.getLongitude(),
                mission.getAltitude(),
                mission.getMissionPriority(),
                mission.getMissionStatus(),
                mission.getDrone(),
                mission.getOperator());
    }
    @Operation(summary = "Delete a mission by id.")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MissionResponse remove(@Positive @PathVariable Long id) {
        Mission mission = missionService.remove(id);
        return new MissionResponse(
                mission.getName(),
                mission.getDescription(),
                mission.getLatitude(),
                mission.getLongitude(),
                mission.getAltitude(),
                mission.getMissionPriority(),
                mission.getMissionStatus(),
                mission.getDrone(),
                mission.getOperator());
    }
}

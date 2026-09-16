package com.example.drone_mission_assignment_system.controller;
import com.example.drone_mission_assignment_system.dto.DroneRequest;
import com.example.drone_mission_assignment_system.entity.Drone;
import com.example.drone_mission_assignment_system.entity.DroneStatus;
import com.example.drone_mission_assignment_system.service.DroneService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tools.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DroneController.class)
class DroneControllerTest {
    @MockitoBean
    private DroneService droneService;
    @Autowired
    private MockMvc mockMvc;
    @Test
    void create() throws Exception {
        Drone drone = new Drone();
        DroneRequest droneRequest = new DroneRequest("Reconnaissance Drone 01", DroneStatus.AVAILABLE);
        drone.setName(droneRequest.name());
        given(droneService.create(droneRequest)).willReturn(drone);
        mockMvc.perform(post("/drone")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(droneRequest))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Reconnaissance Drone 01"));
        verify(droneService).create(droneRequest);
    }
    @Test
    void getAll() throws Exception {
        Drone drone = new Drone();
        drone.setName("Reconnaissance Drone 01");
        List<Drone> drones = new ArrayList<>();
        drones.add(drone);
        given(droneService.getAll()).willReturn(drones);
        mockMvc.perform(get("/drone")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Reconnaissance Drone 01"));
        verify(droneService).getAll();
    }
    @Test
    void getById() throws Exception {
        Drone drone = new Drone();
        drone.setId(1L);
        drone.setName("Reconnaissance Drone 01");
        given(droneService.getById(1L)).willReturn(drone);
        mockMvc.perform(get("/drone/{id}", 1L)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Reconnaissance Drone 01"));
        verify(droneService).getById(1L);
    }
    @Test
    void update() throws Exception {
        Drone drone = new Drone();
        DroneRequest droneRequest = new DroneRequest("Mapping Drone 01", DroneStatus.ACTIVE);
        drone.setId(1L);
        drone.setName(droneRequest.name());
        given(droneService.update(1L, droneRequest)).willReturn(drone);
        mockMvc.perform(put("/drone/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(droneRequest))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Mapping Drone 01"));
        verify(droneService).update(1L, droneRequest);
    }
    @Test
    void remove() throws Exception {
        Drone drone = new Drone();
        drone.setId(1L);
        drone.setName("Cargo Drone 01");
        given(droneService.remove(1L)).willReturn(drone);
        mockMvc.perform(delete("/drone/{id}", 1L)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Cargo Drone 01"));
        verify(droneService).remove(1L);
    }
    public static String jsonToString(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception exception) {
            throw new RuntimeException();
        }
    }
}
package com.example.drone_mission_assignment_system.controller;
import com.example.drone_mission_assignment_system.dto.MissionRequest;
import com.example.drone_mission_assignment_system.entity.*;
import com.example.drone_mission_assignment_system.service.MissionService;
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

@WebMvcTest(MissionController.class)
class MissionControllerTest {
    @MockitoBean
    private MissionService missionService;
    @Autowired
    private MockMvc mockMvc;
    @Test
    void create() throws Exception {
        Drone drone = new Drone();
        Operator operator = new Operator();
        Mission mission = new Mission();
        MissionRequest missionRequest = new MissionRequest(
                "Medication Delivery",
                "Urgent delivery of medicines",
                35.6762,
                139.6503,
                100.0,
                MissionPriority.HIGH,
                MissionStatus.ACTIVE,
                drone,
                operator);
        mission.setName(missionRequest.name());
        given(missionService.create(missionRequest)).willReturn(mission);
        mockMvc.perform(post("/mission")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(missionRequest))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Medication Delivery"));
        verify(missionService).create(missionRequest);
    }
    @Test
    void getAll() throws Exception {
        Mission mission = new Mission();
        mission.setName("Medication Delivery");
        List<Mission> missions = new ArrayList<>();
        missions.add(mission);
        given(missionService.getAll()).willReturn(missions);
        mockMvc.perform(get("/mission")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Medication Delivery"));
        verify(missionService).getAll();
    }
    @Test
    void getById() throws Exception {
        Mission mission = new Mission();
        mission.setId(1L);
        mission.setName("Medication Delivery");
        given(missionService.getById(1L)).willReturn(mission);
        mockMvc.perform(get("/mission/{id}", 1L)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Medication Delivery"));
        verify(missionService).getById(1L);
    }
    @Test
    void update() throws Exception {
        Drone drone = new Drone();
        Operator operator = new Operator();
        Mission mission = new Mission();
        MissionRequest missionRequest = new MissionRequest(
                "Medication Delivery",
                "Urgent delivery of medicines",
                35.6762,
                139.6503,
                100.0,
                MissionPriority.HIGH,
                MissionStatus.FINISHED,
                drone,
                operator);
        mission.setId(1L);
        mission.setName(missionRequest.name());
        given(missionService.update(1L, missionRequest)).willReturn(mission);
        mockMvc.perform(put("/mission/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(missionRequest))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Medication Delivery"));
        verify(missionService).update(1L, missionRequest);
    }
    @Test
    void remove() throws Exception {
        Mission mission = new Mission();
        mission.setId(1L);
        mission.setName("Medication Delivery");
        given(missionService.remove(1L)).willReturn(mission);
        mockMvc.perform(delete("/mission/{id}", 1L)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Medication Delivery"));
        verify(missionService).remove(1L);
    }
    public static String jsonToString(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception exception) {
            throw new RuntimeException();
        }
    }
}
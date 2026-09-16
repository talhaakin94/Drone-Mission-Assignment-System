package com.example.drone_mission_assignment_system.controller;
import com.example.drone_mission_assignment_system.dto.OperatorRequest;
import com.example.drone_mission_assignment_system.entity.Operator;
import com.example.drone_mission_assignment_system.service.OperatorService;
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

@WebMvcTest(OperatorController.class)
class OperatorControllerTest {
    @MockitoBean
    private OperatorService operatorService;
    @Autowired
    private MockMvc mockMvc;
    @Test
    void create() throws Exception {
        Operator operator = new Operator();
        OperatorRequest operatorRequest = new OperatorRequest("Olivia Bennett");
        operator.setName(operatorRequest.name());
        given(operatorService.create(operatorRequest)).willReturn(operator);
        mockMvc.perform(post("/operator")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(operatorRequest))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Olivia Bennett"));
        verify(operatorService).create(operatorRequest);
    }
    @Test
    void getAll() throws Exception {
        Operator operator = new Operator();
        operator.setName("Olivia Bennett");
        List<Operator> operators = new ArrayList<>();
        operators.add(operator);
        given(operatorService.getAll()).willReturn(operators);
        mockMvc.perform(get("/operator")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Olivia Bennett"));
        verify(operatorService).getAll();
    }
    @Test
    void getById() throws Exception {
        Operator operator = new Operator();
        operator.setId(1L);
        operator.setName("Olivia Bennett");
        given(operatorService.getById(1L)).willReturn(operator);
        mockMvc.perform(get("/operator/{id}", 1L)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Olivia Bennett"));
        verify(operatorService).getById(1L);
    }
    @Test
    void update() throws Exception {
        Operator operator = new Operator();
        operator.setId(1L);
        OperatorRequest operatorRequest = new OperatorRequest("Sophia Mitchell");
        operator.setName(operatorRequest.name());
        given(operatorService.update(1L, operatorRequest)).willReturn(operator);
        mockMvc.perform(put("/operator/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(operatorRequest))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Sophia Mitchell"));
        verify(operatorService).update(1L, operatorRequest);
    }
    @Test
    void remove() throws Exception {
        Operator operator = new Operator();
        operator.setId(1L);
        operator.setName("Olivia Bennett");
        given(operatorService.remove(1L)).willReturn(operator);
        mockMvc.perform(delete("/operator/{id}", 1L)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Olivia Bennett"));
        verify(operatorService).remove(1L);
    }
    public static String jsonToString(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception exception) {
            throw new RuntimeException();
        }
    }
}
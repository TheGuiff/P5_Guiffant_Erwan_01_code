package com.safetynet.alerts;

import com.safetynet.alerts.domain.service.FloodService;
import com.safetynet.alerts.web.controller.FloodController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = FloodController.class)
public class FloodControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    FloodService floodService;

    @Test
    public void floodControllerTest() throws Exception {
        mockMvc.perform(get("/flood/stations")
                        .content("{\"stations\":[1,2]}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}

package com.safetynet.alerts;

import com.safetynet.alerts.domain.service.FireService;
import com.safetynet.alerts.web.controller.FireController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = FireController.class)
public class FireControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    FireService fireService;

    @Test
    public void fireControllerTest() throws Exception {
        mockMvc.perform(get("/fire?address=\"1509 Culver St\""))
                .andExpect(status().isOk());
    }
}

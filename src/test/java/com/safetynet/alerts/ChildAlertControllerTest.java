package com.safetynet.alerts;

import com.safetynet.alerts.domain.service.FireStationService;
import com.safetynet.alerts.domain.service.PersonService;
import com.safetynet.alerts.web.controller.ChildAlertController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ChildAlertController.class)
public class ChildAlertControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    PersonService personService;

    @MockBean
    FireStationService fireStationService;

    @Test
    public void phoneAlertTestOnGoodAddress() throws Exception {
        mockMvc.perform(get("/childAlert?address=\"1509 Culver St\""))
                .andExpect(status().isOk());
    }
}

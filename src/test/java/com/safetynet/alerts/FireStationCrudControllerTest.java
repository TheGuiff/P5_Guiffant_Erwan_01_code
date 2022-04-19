package com.safetynet.alerts;

import com.safetynet.alerts.domain.service.FireStationService;
import com.safetynet.alerts.web.controller.FireStationCrudController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = FireStationCrudController.class)
public class FireStationCrudControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FireStationService fireStationService;

    static final String fireStationTestForGetAndDelete = "/firestation/1";
    static final String fireStationTestForCreate = "/firestation/5,[\"address\"]";

    @Test
    public void testGetPersons() throws Exception {
        mockMvc.perform(get("/firestation"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetFireStation() throws Exception {
        mockMvc.perform(get(fireStationTestForGetAndDelete))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteFireStation() throws Exception {
        mockMvc.perform(delete(fireStationTestForGetAndDelete))
                .andExpect(status().isOk());
    }

    @Test
    public void testSaveFireStation() throws Exception {
        mockMvc.perform(post(fireStationTestForCreate))
                .andExpect(status().isOk());
    }
}

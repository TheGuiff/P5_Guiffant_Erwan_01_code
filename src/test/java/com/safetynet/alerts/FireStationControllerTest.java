package com.safetynet.alerts;

import com.safetynet.alerts.domain.service.FireStationService;
import com.safetynet.alerts.web.controller.FireStationController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = FireStationController.class)
public class FireStationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FireStationService fireStationService;

    static final String endpointTestForGet = "/firestation";
    static final String endpointTestForGetAndDelete = "/firestation/1";
    static final String endpointTestForDeleteMappingAddress = "/firestation/addressTest";
    static final String endpointTestMappingFireStationAddress = "/firestation/1,addressTest";

    @Test
    public void testGetFireStations() throws Exception {
        mockMvc.perform(get(endpointTestForGet))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetFireStation() throws Exception {
        mockMvc.perform(get(endpointTestForGetAndDelete))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddMappingFireStationAddress() throws Exception {
        mockMvc.perform(post(endpointTestMappingFireStationAddress))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteMappingFireStation() throws Exception {
        mockMvc.perform(delete(endpointTestForGetAndDelete))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteMappingAddress() throws Exception {
        mockMvc.perform(delete(endpointTestForDeleteMappingAddress))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateMappingFireStationAddress() throws Exception {
        mockMvc.perform(put(endpointTestMappingFireStationAddress))
                .andExpect(status().isOk());
    }
}

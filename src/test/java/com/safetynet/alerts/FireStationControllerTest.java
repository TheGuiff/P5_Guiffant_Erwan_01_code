package com.safetynet.alerts;

import com.safetynet.alerts.domain.service.FireStationService;
import com.safetynet.alerts.domain.service.PersonService;
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

    @MockBean
    private PersonService personService;

    static final String endpointTest = "/firestation?stationNumber=3";
    static final String endpointTestForGet = "/firestation/1";
    static final String endpointTestForGetKo = "/firestation/8";
    static final String endpointForDelete = "/firestation/2";
    static final String endpointForDeleteAddress = "/firestation?address=\"1509 Culver St\"";
    static final String endpointTestMappingFireStationAddress = "/firestation?station=1&address=\"address test\"";

    @Test
    public void personByFireStationTest() throws Exception {
        mockMvc.perform(get(endpointTest))
                .andExpect(status().isOk());
    }

    @Test
    public void getFireStationTest() throws Exception {
        mockMvc.perform(get(endpointTestForGet))
                .andExpect(status().isOk());
    }

    @Test
    public void addMappingFireStationAddressTest() throws Exception {
        mockMvc.perform(post(endpointTestMappingFireStationAddress))
                .andExpect(status().isOk());
    }

    @Test
    public void updateMappingFireStationAddressTest() throws Exception {
        mockMvc.perform(put(endpointTestMappingFireStationAddress))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteMappingFireStationTest() throws Exception {
        mockMvc.perform(delete(endpointForDelete))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteMappingAddressTest() throws Exception {
        mockMvc.perform(delete(endpointForDeleteAddress))
                .andExpect(status().isOk());
    }

}

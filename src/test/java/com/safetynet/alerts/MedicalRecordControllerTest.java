package com.safetynet.alerts;

import com.safetynet.alerts.domain.service.MedicalRecordService;
import com.safetynet.alerts.web.controller.MedicalRecordController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MedicalRecordController.class)
public class MedicalRecordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    MedicalRecordService medicalRecordService;

    static final String endpointTestForAddAndUpdate = "/medicalRecord/";
    static final String endpointTestForDelete = "/medicalRecord/Test,Test";

    @Test
    public void postMedicalRecordTest() throws Exception {
        mockMvc.perform(post(endpointTestForAddAndUpdate).content(" { \"firstName\":\"John\", \n" +
                        " \"lastName\":\"Boyd\", \n" +
                        " \"birthdate\":\"03/06/1984\", \n" +
                        " \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \n" +
                        " \"allergies\":[\"nillacilan\"] }").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void putMedicalRecordTest() throws Exception {
        mockMvc.perform(put(endpointTestForAddAndUpdate))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteMedicalRecordTest() throws Exception {
        mockMvc.perform(delete(endpointTestForDelete))
                .andExpect(status().isOk());
    }

}

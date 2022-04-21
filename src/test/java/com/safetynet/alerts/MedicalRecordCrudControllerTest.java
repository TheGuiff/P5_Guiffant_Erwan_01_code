package com.safetynet.alerts;

import com.safetynet.alerts.domain.service.MedicalRecordService;
import com.safetynet.alerts.web.controller.MedicalRecordController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MedicalRecordController.class)
public class MedicalRecordCrudControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    MedicalRecordService medicalRecordService;

    static final String endpointTestForAddAndUpdate = "/medicalRecord/Test,Test,22/04/2022,[med1,med2],[al1, al2]";
    static final String endpointTestForDelete = "/medicalRecord/Test,Test";

    @Test
    public void postMedicalRecordTest() throws Exception {
        mockMvc.perform(post(endpointTestForAddAndUpdate))
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

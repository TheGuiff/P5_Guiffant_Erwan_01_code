package com.safetynet.alerts;

import com.safetynet.alerts.domain.service.MedicalRecordService;
import com.safetynet.alerts.web.controller.MedicalRecordController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MedicalRecordController.class)
public class MedicalRecordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    MedicalRecordService medicalRecordService;

    static final String endpointTest = "/medicalRecord/";
    static final String endpointForGetTest = "/medicalRecord/Sophia,Zemicks";
    static final String endpointForDeleteTest = "/medicalRecord?firstName=John&lastName=Boyd";
    static final String contentTest = " { \"firstName\":\"John\", \n" +
            " \"lastName\":\"Boyd\", \n" +
            " \"birthdate\":\"03/06/1984\", \n" +
            " \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \n" +
            " \"allergies\":[\"nillacilan\"] }";
    static final String contentKoTest = " { \"firstName\":\"JohnTest\", \n" +
            " \"lastName\":\"BoydTest\", \n" +
            " \"birthdate\":\"03/06/1984\", \n" +
            " \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \n" +
            " \"allergies\":[\"nillacilan\"] }";

    @Test
    public void getMedicalRecordByFirstNameAndLastNameTest() throws Exception {
        mockMvc.perform(get(endpointForGetTest))
                .andExpect(status().isOk());
    }

    @Test
    public void getMedicalRecordByFirstNameAndLastNameTestKo() throws Exception {
        try {
            when(medicalRecordService.getMedicalRecordByFirstNameAndLastName("Sophia","Zemicks")).thenThrow(NoSuchElementException.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to set up test mock objects");
        }
        mockMvc.perform(get(endpointForGetTest))
                .andExpect(status().isNotFound());
    }

    @Test
    public void postMedicalRecordTest() throws Exception {
        mockMvc.perform(post(endpointTest)
                        .content(contentTest)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void postMedicalRecordTestKo() throws Exception {
        try {
            when(medicalRecordService.personToMedicalRecordDto(any())).thenThrow(NoSuchElementException.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to set up test mock objects");
        }
        mockMvc.perform(post(endpointTest)
                        .content(contentTest)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void putMedicalRecordTest() throws Exception {
        mockMvc.perform(put(endpointTest)
                        .content(contentTest)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void putMedicalRecordTestKo() throws Exception {
        try {
            when(medicalRecordService.personToMedicalRecordDto(any())).thenThrow(NoSuchElementException.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to set up test mock objects");
        }
        mockMvc.perform(put(endpointTest)
                        .content(contentTest)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteMedicalRecordOkTest() throws Exception {
        mockMvc.perform(delete(endpointForDeleteTest))
                .andExpect(status().isOk());
    }

}

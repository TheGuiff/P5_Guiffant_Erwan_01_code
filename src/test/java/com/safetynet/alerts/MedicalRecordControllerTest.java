package com.safetynet.alerts;

import com.safetynet.alerts.domain.service.MedicalRecordService;
import com.safetynet.alerts.web.controller.MedicalRecordController;
import com.safetynet.alerts.web.dto.MedicalRecordDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
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
    static final MedicalRecordDto medicalRecordDto = new MedicalRecordDto();

    @BeforeAll
    static void beforeMedicalRecordControllerTest () {
        medicalRecordDto.setFirstName("Test");
        medicalRecordDto.setLastName("Test");
        medicalRecordDto.setBirthdate("");
        medicalRecordDto.setMedications(new ArrayList<>());
        medicalRecordDto.setAllergies(new ArrayList<>());
    }

    @Test
    public void getMedicalRecordByFirstNameAndLastNameTest() throws Exception {
        try {
            when(medicalRecordService.getMedicalRecordByFirstNameAndLastName("Sophia","Zemicks")).thenReturn(medicalRecordDto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to set up test mock objects");
        }
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
        try {
            when(medicalRecordService.getMedicalRecordByFirstNameAndLastName(anyString(),anyString())).thenReturn(medicalRecordDto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to set up test mock objects");
        }
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
        try {
            when(medicalRecordService.getMedicalRecordByFirstNameAndLastName(anyString(),anyString())).thenReturn(medicalRecordDto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to set up test mock objects");
        }
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
    public void deleteMedicalRecordTest() throws Exception {
        try {
            doNothing().when(medicalRecordService).deleteMedicalRecord(anyString(),anyString());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to set up test mock objects");
        }
        mockMvc.perform(delete(endpointForDeleteTest))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteMedicalRecordTestKo() throws Exception {
        try {
            doThrow(new NoSuchElementException()).when(medicalRecordService).deleteMedicalRecord(anyString(),anyString());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to set up test mock objects");
        }
        mockMvc.perform(delete(endpointForDeleteTest))
                .andExpect(status().isNotFound());
    }

}

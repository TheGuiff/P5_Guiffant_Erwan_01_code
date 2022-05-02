package com.safetynet.alerts;

import com.safetynet.alerts.domain.service.FireStationService;
import com.safetynet.alerts.domain.service.PersonService;
import com.safetynet.alerts.web.controller.FireStationController;
import com.safetynet.alerts.web.dto.FireStationDto;
import com.safetynet.alerts.web.dto.ListPersonsOfAFireStationDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
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
    static final String endpointForDelete = "/firestation/2";
    static final String endpointForDeleteAddress = "/firestation?address=\"1509 Culver St\"";
    static final String endpointTestMappingFireStationAddress = "/firestation?station=1&address=\"address test\"";
    static final ListPersonsOfAFireStationDto listPersonsOfAFireStationDto = new ListPersonsOfAFireStationDto();
    static final FireStationDto fireStationDto = new FireStationDto();

    @BeforeAll
    static void beforeFireStationControllerTest () {
        listPersonsOfAFireStationDto.setPersonsCovered(new ArrayList<>());
        listPersonsOfAFireStationDto.setNumberOfChilds(0);
        listPersonsOfAFireStationDto.setNumberOfAdults(0);
        fireStationDto.setStation(1);
        fireStationDto.setAdresses(new ArrayList<>());
    }

    @Test
    public void personByFireStationTest() throws Exception {
        try {
            when(personService.listPersonsByAFireSation(any())).thenReturn(listPersonsOfAFireStationDto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to set up test mock objects");
        }
        mockMvc.perform(get(endpointTest))
                .andExpect(status().isOk());
    }

    @Test
    public void personByFireStationTestKo() throws Exception {
        try {
            when(personService.listPersonsByAFireSation(any())).thenThrow(NoSuchElementException.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to set up test mock objects");
        }
        mockMvc.perform(get(endpointTest))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getFireStationTest() throws Exception {
        try {
            when(fireStationService.fireStationToFireStationDto(any())).thenReturn(fireStationDto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to set up test mock objects");
        }
        mockMvc.perform(get(endpointTestForGet))
                .andExpect(status().isOk());
    }

    @Test
    public void getFireStationTestKo() throws Exception {
        try {
            when(fireStationService.fireStationToFireStationDto(any())).thenThrow(NoSuchElementException.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to set up test mock objects");
        }
        mockMvc.perform(get(endpointTestForGet))
                .andExpect(status().isNotFound());
    }

    @Test
    public void addMappingFireStationAddressTest() throws Exception {
        try {
            when(fireStationService.fireStationToFireStationDto(any())).thenReturn(fireStationDto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to set up test mock objects");
        }
        mockMvc.perform(post(endpointTestMappingFireStationAddress))
                .andExpect(status().isOk());
    }

    @Test
    public void updateMappingFireStationAddressTest() throws Exception {
        try {
            when(fireStationService.fireStationToFireStationDto(any())).thenReturn(fireStationDto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to set up test mock objects");
        }
        mockMvc.perform(put(endpointTestMappingFireStationAddress))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteMappingFireStationTest() throws Exception {
        try {
            doNothing().when(fireStationService).deleteMappingFireStation(anyInt());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to set up test mock objects");
        }
        mockMvc.perform(delete(endpointForDelete))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteMappingFireStationTestKo() throws Exception {
        try {
            doThrow(new NoSuchElementException("test")).when(fireStationService).deleteMappingFireStation(anyInt());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to set up test mock objects");
        }
        mockMvc.perform(delete(endpointForDelete))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteMappingAddressTest() throws Exception {
        try {
            doNothing().when(fireStationService).deleteMappingAddress(any());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to set up test mock objects");
        }
        mockMvc.perform(delete(endpointForDeleteAddress))
                .andExpect(status().isOk());
    }

}

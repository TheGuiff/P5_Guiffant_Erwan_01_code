package com.safetynet.alerts;

import com.safetynet.alerts.domain.service.PersonService;
import com.safetynet.alerts.web.controller.PersonController;
import com.safetynet.alerts.web.dto.PersonDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PersonController.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    static final String endpointTest = "/person";
    static final String endpointForGetTest = "/person/John,Boyd";
    static final String endpointForDeleteTest = "/person?firstName=John&lastName=Boyd";
    static final String contentAdd = "{ \"firstName\":\"John\", \n" +
            " \"lastName\":\"BoydTest\", \n" +
            " \"address\":\"address\", \n" +
            " \"city\":\"city\", \n" +
            " \"zip\":\"zip\", \n" +
            " \"phone\":\"phone\", \n" +
            " \"email\":\"email\" }";
    static final String contentUpdate = "{ \"firstName\":\"John\", \n" +
            " \"lastName\":\"Boyd\", \n" +
            " \"address\":\"address\", \n" +
            " \"city\":\"city\", \n" +
            " \"zip\":\"zip\", \n" +
            " \"phone\":\"phone\", \n" +
            " \"email\":\"email\" }";
    static final PersonDto personDto = new PersonDto();
    static final List<PersonDto> personDtos = new ArrayList<>();

    @BeforeAll
    static void beforePersonControllerTest() {
        personDto.setFirstName("Test");
        personDto.setLastName("Test");
        personDtos.add(personDto);
    }

    @Test
    public void getListPersonsTest() throws Exception {
        try {
                when(personService.listPersonToPersonDto(any())).thenReturn(personDtos);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to set up test mock objects");
            }
        mockMvc.perform(get(endpointTest))
                .andExpect(status().isOk());
    }

    @Test
    public void getPersonByFirstNameAndLastNameTest() throws Exception {
        try {
            when(personService.personToPersonDto(any())).thenReturn(personDto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to set up test mock objects");
        }
        mockMvc.perform(get(endpointForGetTest))
                .andExpect(status().isOk());
    }

    @Test
    public void getPersonByFirstNameAndLastNameTestKo() throws Exception {
        try {
            when(personService.personToPersonDto(any())).thenThrow(NoSuchElementException.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to set up test mock objects");
        }
        mockMvc.perform(get(endpointForGetTest))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deletePersonTest() throws Exception {
        try {
            doNothing().when(personService).deletePerson(anyString(),anyString());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to set up test mock objects");
        }
        mockMvc.perform(delete(endpointForDeleteTest))
                .andExpect(status().isOk());
    }

    @Test
    public void deletePersonTestKo() throws Exception {
        try {
            doThrow(new NoSuchElementException()).when(personService).deletePerson(anyString(),anyString());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to set up test mock objects");
        }
        mockMvc.perform(delete(endpointForDeleteTest))
                .andExpect(status().isNotFound());
    }

    @Test
    public void addPersonTest() throws Exception {
        try {
            when(personService.personToPersonDto(any())).thenReturn(personDto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to set up test mock objects");
        }
        mockMvc.perform(post(endpointTest)
                        .content(contentAdd)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updatePersonTest() throws Exception {
        try {
            when(personService.personToPersonDto(any())).thenReturn(personDto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to set up test mock objects");
        }
        mockMvc.perform(put(endpointTest)
                        .content(contentUpdate)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updatePersonTestKo() throws Exception {
        try {
            when(personService.personToPersonDto(any())).thenThrow(NoSuchElementException.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to set up test mock objects");
        }
        mockMvc.perform(put(endpointTest)
                        .content(contentUpdate)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}

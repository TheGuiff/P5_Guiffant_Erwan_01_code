package com.safetynet.alerts;

import com.safetynet.alerts.domain.service.PersonService;
import com.safetynet.alerts.web.controller.PersonController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

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

    @Test
    public void getListPersonsTest() throws Exception {
        mockMvc.perform(get(endpointTest))
                .andExpect(status().isOk());
    }

    @Test
    public void getPersonByFirstNameAndLastNameTest() throws Exception {
        mockMvc.perform(get(endpointForGetTest))
                .andExpect(status().isOk());
    }

    @Test
    public void deletePersonTest() throws Exception {
        mockMvc.perform(delete(endpointForDeleteTest))
                .andExpect(status().isOk());
    }

    @Test
    public void addPersonTest() throws Exception {
        mockMvc.perform(post(endpointTest)
                        .content(contentAdd)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updatePersonTest() throws Exception {
        mockMvc.perform(put(endpointTest)
                        .content(contentUpdate)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}

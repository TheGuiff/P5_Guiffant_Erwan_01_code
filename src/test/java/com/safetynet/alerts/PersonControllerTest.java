package com.safetynet.alerts;

import com.safetynet.alerts.domain.service.PersonService;
import com.safetynet.alerts.web.controller.PersonController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PersonController.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    static final String endpointTestForGetAndDelete = "/person/Test,Test";
    static final String endpointTestForCreate = "/person/Test,Test,,,,,,";

    @Test
    public void testGetPersons() throws Exception {
        mockMvc.perform(get("/person"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetPerson() throws Exception {
        mockMvc.perform(get(endpointTestForGetAndDelete))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeletePerson() throws Exception {
        mockMvc.perform(delete(endpointTestForGetAndDelete))
                .andExpect(status().isOk());
    }

    @Test
    public void testSavePerson() throws Exception {
        mockMvc.perform(post(endpointTestForCreate))
                .andExpect(status().isOk());
    }

}

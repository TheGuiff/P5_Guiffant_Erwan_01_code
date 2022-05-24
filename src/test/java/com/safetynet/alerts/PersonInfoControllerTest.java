package com.safetynet.alerts;

import com.safetynet.alerts.domain.service.PersonInfoService;
import com.safetynet.alerts.web.controller.PersonInfoController;
import com.safetynet.alerts.web.dto.PersonInfoDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PersonInfoController.class)
public class PersonInfoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    PersonInfoService personInfoService;

    static final String personInfo = "/personInfo?firstName=John&lastName=Boyd";
    static final PersonInfoDto personInfoDto = new PersonInfoDto();

    @Test
    public void phoneAlertTestOnGoodAddress() throws Exception {
        personInfoDto.setFirstName("Test");
        personInfoDto.setLastName("Test");
        try {
            when(personInfoService.personInfo(anyString(),anyString())).thenReturn(personInfoDto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to set up test mock objects");
        }
        mockMvc.perform(get(personInfo))
                .andExpect(status().isOk());
    }

    @Test
    public void phoneAlertTestOnGoodAddressKo() throws Exception {
        try {
            when(personInfoService.personInfo("John","Boyd")).thenThrow(NoSuchElementException.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to set up test mock objects");
        }
        mockMvc.perform(get(personInfo))
                .andExpect(status().isNotFound());
    }

}

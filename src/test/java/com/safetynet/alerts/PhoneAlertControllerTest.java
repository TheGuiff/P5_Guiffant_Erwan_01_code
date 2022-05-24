package com.safetynet.alerts;

import com.safetynet.alerts.domain.service.FireStationService;
import com.safetynet.alerts.domain.service.PersonService;
import com.safetynet.alerts.domain.service.PhoneAlertService;
import com.safetynet.alerts.web.controller.PhoneAlertController;
import com.safetynet.alerts.web.dto.PhoneAlertDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PhoneAlertController.class)
public class PhoneAlertControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    PhoneAlertService phoneAlertService;

    @MockBean
    PersonService personService;

    @MockBean
    FireStationService fireStationService;

    static final String phoneAlert = "/phoneAlert?firestation=1";
    static final List<String> phoneAlerts = new ArrayList<>();
    static final PhoneAlertDto phoneAlertDto = new PhoneAlertDto();

    @Test
    public void phoneAlertTest() throws Exception {
        phoneAlerts.add("phone");
        phoneAlertDto.setListPhones(phoneAlerts);
        try {
            when(phoneAlertService.listPersonToPhoneAlertDto(any())).thenReturn(phoneAlertDto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to set up test mock objects");
        }
        mockMvc.perform(get(phoneAlert))
                .andExpect(status().isOk());
    }

    @Test
    public void phoneAlertTestKo() throws Exception {
        try {
            when(phoneAlertService.listPersonToPhoneAlertDto(any())).thenThrow(NoSuchElementException.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to set up test mock objects");
        }
        mockMvc.perform(get(phoneAlert))
                .andExpect(status().isNotFound());
    }
}

package com.safetynet.alerts;

import com.safetynet.alerts.domain.service.FireService;
import com.safetynet.alerts.web.controller.FireController;
import com.safetynet.alerts.web.dto.ListFireDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = FireController.class)
public class FireControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    FireService fireService;

    private static final String address = "/fire?address=\"1509 Culver St\"";
    private static final ListFireDto listFireDto = new ListFireDto();

    @Test
    public void fireControllerTest() throws Exception {
        listFireDto.setStation(1);
        listFireDto.setInhabitantsList(new ArrayList<>());
        try {
            when(fireService.fire(any())).thenReturn(listFireDto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to set up test mock objects");
        }
        mockMvc.perform(get(address))
                .andExpect(status().isOk());
    }

    @Test
    public void fireControllerTestKo() throws Exception {
        try {
            when(fireService.fire(any())).thenThrow(NoSuchElementException.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to set up test mock objects");
        }
        mockMvc.perform(get(address))
                .andExpect(status().isNotFound());
    }
}

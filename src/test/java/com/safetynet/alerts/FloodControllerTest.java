package com.safetynet.alerts;

import com.safetynet.alerts.domain.service.FloodService;
import com.safetynet.alerts.web.controller.FloodController;
import com.safetynet.alerts.web.dto.FireAndFloodByStationDto;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = FloodController.class)
public class FloodControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    FloodService floodService;

    static final String flood = "/flood/stations";
    static final List<FireAndFloodByStationDto> fireAndFloodByStationDtos = new ArrayList<>();
    static final FireAndFloodByStationDto fireAndFloodByStationDto = new FireAndFloodByStationDto();

    @Test
    public void floodControllerTest() throws Exception {
        fireAndFloodByStationDto.setStation(1);
        fireAndFloodByStationDto.setListAddresses(new ArrayList<>());
        fireAndFloodByStationDtos.add(fireAndFloodByStationDto);
        try {
            when(floodService.flood(any())).thenReturn(fireAndFloodByStationDtos);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to set up test mock objects");
        }
        mockMvc.perform(get(flood)
                        .content("{\"stations\":[1,2]}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void floodControllerTestKo() throws Exception {
        try {
            when(floodService.flood(any())).thenThrow(NoSuchElementException.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to set up test mock objects");
        }
        mockMvc.perform(get(flood)
                        .content("{\"stations\":[1,2]}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}

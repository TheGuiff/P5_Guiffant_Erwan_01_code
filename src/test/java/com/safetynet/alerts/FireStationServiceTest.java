package com.safetynet.alerts;

import com.safetynet.alerts.domain.model.FireStation;
import com.safetynet.alerts.domain.service.FireStationService;
import com.safetynet.alerts.web.dto.FireStationDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class FireStationServiceTest {

    @Autowired
    FireStationService fireStationService;

    @Test
    public void fireStationToFireStationDtoTest() {
        //Given
        final int number = 1;
        List<String> listAddresses = new ArrayList<>();
        listAddresses.add("addresse");
        FireStation fireStation = new FireStation(number, listAddresses);
        //When
        FireStationDto fireStationDto = fireStationService.fireStationToFireStationDto(fireStation);
        //Then
        assertEquals(number,fireStationDto.getNumber());
        assertEquals(listAddresses,fireStationDto.getAdresses());
    }
}

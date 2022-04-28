package com.safetynet.alerts;

import com.safetynet.alerts.domain.model.Person;
import com.safetynet.alerts.domain.service.FireService;
import com.safetynet.alerts.domain.service.FireStationService;
import com.safetynet.alerts.domain.service.FloodService;
import com.safetynet.alerts.web.dto.FireAndFloodByStationDto;
import com.safetynet.alerts.web.dto.FireAndFloodDto;
import com.safetynet.alerts.web.dto.ListFireDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class FloodServiceTest {

    @Autowired
    FloodService floodService;

    @MockBean
    FireService fireService;

    @MockBean
    FireStationService fireStationService;

    private static final String addressTest = "address";
    private static final List<Integer> listStations = new ArrayList<>();
    private static final List<String> listAddresses = new ArrayList<>();
    private static final ListFireDto listFireDto = new ListFireDto();
    private static final List<String> medications = new ArrayList<>();
    private static final List<String> allergies = new ArrayList<>();

    @BeforeAll
    static void beforeFloodServiceTest() {
        listStations.add(1);
        listAddresses.add(addressTest);
        listFireDto.setStation(1);
        Person person = new Person("firstname","lastname",addressTest,"","","phone","");
        person.setBirthdate("01/01/1980");
        person.setAllergies(allergies);
        person.setMedications(medications);
        FireAndFloodDto fireAndFloodDto = new FireAndFloodDto(person);
        List<FireAndFloodDto> fireAndFloodDtos = new ArrayList<>();
        fireAndFloodDtos.add(fireAndFloodDto);
        listFireDto.setInhabitantsList(fireAndFloodDtos);
    }

    @Test
    public void floodTest() {
        try {
            when(fireStationService.listAddressesByStation(1)).thenReturn(listAddresses);
            when(fireService.fire(addressTest)).thenReturn(listFireDto);
        } catch (Exception e) {
            e.printStackTrace();
            throw  new RuntimeException("Failed to set up test mock objects");
        }
        List<FireAndFloodByStationDto> fireAndFloodByStationDtos = floodService.flood(listStations);
        Assertions.assertEquals(1, fireAndFloodByStationDtos.size());
        Assertions.assertEquals(1, fireAndFloodByStationDtos.get(0).getStation());
        Assertions.assertEquals(1, fireAndFloodByStationDtos.get(0).getListAddresses().size());
        Assertions.assertEquals(addressTest, fireAndFloodByStationDtos.get(0).getListAddresses().get(0).getAddress());
        Assertions.assertEquals(1, fireAndFloodByStationDtos.get(0).getListAddresses().get(0).getInhabitantsList().size());
    }

    @Test
    public void floodTestKo() {
        try {
            when(fireStationService.listAddressesByStation(1)).thenThrow(NoSuchElementException.class);
            when(fireService.fire(addressTest)).thenReturn(listFireDto);
        } catch (Exception e) {
            e.printStackTrace();
            throw  new RuntimeException("Failed to set up test mock objects");
        }
        Assertions.assertThrows(NoSuchElementException.class,() -> floodService.flood(listStations));
    }
}

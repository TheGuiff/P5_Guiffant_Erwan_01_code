package com.safetynet.alerts;

import com.safetynet.alerts.dal.repository.FireStationRepository;
import com.safetynet.alerts.domain.model.FireStation;
import com.safetynet.alerts.domain.service.FireStationService;
import com.safetynet.alerts.web.dto.FireStationDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class FireStationServiceTest {

    @Autowired
    FireStationService fireStationService;

    @MockBean
    private FireStationRepository fireStationRepository;

    static final String address = "address";

    static List<FireStation> listFireStation = new ArrayList<>();
    static FireStation fireStationToAdd;

    @BeforeAll
    static void listOfFireStationTest() {
        List<String> listAddress = new ArrayList<>();
        listAddress.add(address);
        listFireStation.add(new FireStation(1,listAddress));
        listFireStation.add(new FireStation(2,listAddress));
        fireStationToAdd = new FireStation(3,listAddress);
    }

    @Test
    public void fireStationToFireStationDtoTest() {
        //Given
        final int number = 1;
        List<String> listAddresses = new ArrayList<>();
        listAddresses.add(address);
        FireStation fireStation = new FireStation(number, listAddresses);
        //When
        FireStationDto fireStationDto = fireStationService.fireStationToFireStationDto(fireStation);
        //Then
        assertEquals(number,fireStationDto.getNumber());
        assertEquals(listAddresses,fireStationDto.getAdresses());
    }

    @Test
    public void listFireStationToFireStationDtoTest() {
        //When
        List<FireStationDto> listFireStationDto = fireStationService.listFireStationToFireStationDto(listFireStation);
        //Then
        assertEquals(2,listFireStationDto.size());
    }

    @Test
    public void getListFireStationOk () {
        //Given
        try {
            when(fireStationRepository.findAll()).thenReturn(listFireStation);
        } catch (Exception e) {
            e.printStackTrace();
            throw  new RuntimeException("Failed to set up test mock objects");
        }
        //WHEN
        assertEquals(2,fireStationService.getFireStations().size());
    }

    @Test
    public void getFireStationByIdOk () {
        try {
            when(fireStationRepository.findById(listFireStation.get(0).getNumber())).thenReturn(Optional.of(listFireStation.get(0)));
        } catch (Exception e) {
            e.printStackTrace();
            throw  new RuntimeException("Failed to set up test mock objects");
        }
        assertEquals(listFireStation.get(0).getNumber(),
                fireStationService.getFireStation(listFireStation.get(0).getNumber()).getNumber());
    }

    @Test
    public void deleteFireStationOk () {
        fireStationService.deleteFireStation(listFireStation.get(0).getNumber());
        verify(fireStationRepository, Mockito.times(1))
                .delete(listFireStation.get(0).getNumber());
    }

    @Test
    public void saveFirePersonOk () {
        fireStationService.saveFireStation(fireStationToAdd);
        verify(fireStationRepository, Mockito.times(1)).save(fireStationToAdd);
    }
}

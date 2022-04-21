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

    static final String addressTest1 = "address1";
    static final String addressTest2 = "address2";
    static final int fireStationTest1 = 1;
    static final int fireStationTest2 = 2;

    static List<FireStation> listFireStationTest = new ArrayList<>();

    @BeforeAll
    static void listOfFireStationTest() {
        List<String> listAddress = new ArrayList<>();
        listAddress.add(addressTest1);
        listFireStationTest.add(new FireStation(fireStationTest1,listAddress));
        listFireStationTest.add(new FireStation(fireStationTest2,listAddress));
    }

    @Test
    public void fireStationToFireStationDtoTest() {
        FireStationDto fireStationDto = fireStationService.fireStationToFireStationDto(listFireStationTest.get(0));
        //Then
        assertEquals(listFireStationTest.get(0).getNumber(),fireStationDto.getNumber());
        assertEquals(listFireStationTest.get(0).getAddresses(),fireStationDto.getAdresses());
    }

    @Test
    public void listFireStationToFireStationDtoTest() {
        //When
        List<FireStationDto> listFireStationDto = fireStationService.listFireStationToFireStationDto(listFireStationTest);
        //Then
        assertEquals(2,listFireStationDto.size());
    }

    @Test
    public void getListFireStationOk () {
        //Given
        try {
            when(fireStationRepository.findAll()).thenReturn(listFireStationTest);
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
            when(fireStationRepository.findById(listFireStationTest.get(0).getNumber())).thenReturn(Optional.of(listFireStationTest.get(0)));
        } catch (Exception e) {
            e.printStackTrace();
            throw  new RuntimeException("Failed to set up test mock objects");
        }
        assertEquals(listFireStationTest.get(0).getNumber(),
                fireStationService.getFireStation(listFireStationTest.get(0).getNumber()).getNumber());
    }

    @Test
    public void deleteMappingFireStationTest () {
        int number = listFireStationTest.get(0).getNumber();
        fireStationService.deleteMappingFireStation(number);
        verify(fireStationRepository, Mockito.times(1))
                .deleteMappingFireStation(number);
    }

    @Test
    public void deleteMappingAddressTest () {
        String address = listFireStationTest.get(0).getAddresses().get(0);
        fireStationService.deleteMappingAddress(address);
        verify(fireStationRepository, Mockito.times(1))
                .deleteMappingAddress(address);
    }

    @Test
    public void addMappingFireStationAddressTest () {
        int number = listFireStationTest.get(0).getNumber();
        fireStationService.addMappingFiresStationAddress(number, addressTest2);
        verify(fireStationRepository, Mockito.times(1))
                .addMappingFiresStationAddress(number, addressTest2);
    }

}

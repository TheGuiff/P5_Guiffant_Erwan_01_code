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
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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
    static final int stationTest1 = 1;
    static final int stationTest2 = 2;
    static final int stationTestKo = 8;

    static List<FireStation> listFireStationTest = new ArrayList<>();

    @BeforeAll
    static void listOfFireStationTest() {
        List<String> listAddress1 = new ArrayList<>();
        listAddress1.add(addressTest1);
        List<String> listAddress2 = new ArrayList<>();
        listAddress2.add(addressTest2);
        listFireStationTest.add(new FireStation(stationTest1,listAddress1));
        listFireStationTest.add(new FireStation(stationTest2,listAddress2));
    }

    @Test
    public void getFireStationsTest () {
        //Given
        try {
            when(fireStationRepository.findAll()).thenReturn(listFireStationTest);
        } catch (Exception e) {
            e.printStackTrace();
            throw  new RuntimeException("Failed to set up test mock objects");
        }
        //WHEN
        assertEquals(listFireStationTest.size(),fireStationService.getFireStations().size());
    }

    @Test
    public void getFireStationTest () {
        try {
            when(fireStationRepository.findById(listFireStationTest.get(0).getStation())).thenReturn(Optional.of(listFireStationTest.get(0)));
        } catch (Exception e) {
            e.printStackTrace();
            throw  new RuntimeException("Failed to set up test mock objects");
        }
        assertEquals(listFireStationTest.get(0).getStation(),
                fireStationService.getFireStation(listFireStationTest.get(0).getStation()).getStation());
    }

    @Test
    public void deleteMappingFireStationTest () {
        int number = listFireStationTest.get(0).getStation();
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
        int number = listFireStationTest.get(0).getStation();
        fireStationService.addMappingFiresStationAddress(number, addressTest2);
        verify(fireStationRepository, Mockito.times(1))
                .addMappingFiresStationAddress(number, addressTest2);
    }

    @Test
    public void updateMappingFireStationAddressTest () {
        int station = listFireStationTest.get(1).getStation();
        fireStationService.updateMappingFireStationAddress(station, listFireStationTest.get(0).getAddresses().get(0));
        verify(fireStationRepository, Mockito.times(1))
                .addMappingFiresStationAddress(station, listFireStationTest.get(0).getAddresses().get(0));
    }

    @Test
    public void fireStationToFireStationDtoTest() {
        FireStationDto fireStationDto = fireStationService.fireStationToFireStationDto(listFireStationTest.get(0));
        //Then
        assertEquals(listFireStationTest.get(0).getStation(),fireStationDto.getStation());
        assertEquals(listFireStationTest.get(0).getAddresses(),fireStationDto.getAdresses());
    }

    @Test
    public void listFireStationToFireStationDtoTest() {
        //When
        List<FireStationDto> listFireStationDto = fireStationService.listFireStationToFireStationDto(listFireStationTest);
        //Then
        assertEquals(listFireStationTest.size(),listFireStationDto.size());
    }

    @Test
    public void listAddressesByStationTestOk () {
        try {
            when(fireStationRepository.findById(stationTest1)).thenReturn(Optional.of(listFireStationTest.get(0)));
        } catch (Exception e) {
            e.printStackTrace();
            throw  new RuntimeException("Failed to set up test mock objects");
        }
        List<String> listAddressesTest = fireStationService.listAddressesByStation(stationTest1);
        assertEquals(listFireStationTest.get(0).getAddresses().size(), listAddressesTest.size());
        assertEquals(listFireStationTest.get(0).getAddresses().get(0), listAddressesTest.get(0));
    }

    @Test
    public void listAddressesByStationTestKo () {
        try {
            when(fireStationRepository.findById(stationTestKo)).thenReturn(Optional.empty());
        } catch (Exception e) {
            e.printStackTrace();
            throw  new RuntimeException("Failed to set up test mock objects");
        }
        assertThrows(NoSuchElementException.class,() -> fireStationService.listAddressesByStation(stationTestKo));
    }

    @Test
    public void fireStationByAddressTest() {
        try {
            when(fireStationRepository.getListFireStations()).thenReturn(listFireStationTest);
        } catch (Exception e) {
            e.printStackTrace();
            throw  new RuntimeException("Failed to set up test mock objects");
        }
        int station = fireStationService.fireStationByAddress(addressTest2);
        assertEquals(2, station);
    }
    @Test
    public void fireStationByAddressTestKo() {
        try {
            when(fireStationRepository.getListFireStations()).thenReturn(listFireStationTest);
        } catch (Exception e) {
            e.printStackTrace();
            throw  new RuntimeException("Failed to set up test mock objects");
        }
        assertThrows(NoSuchElementException.class,() -> fireStationService.fireStationByAddress(addressTest2+"KO"));
    }









}

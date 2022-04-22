package com.safetynet.alerts;

import com.safetynet.alerts.dal.repository.FireStationRepository;
import com.safetynet.alerts.domain.model.FireStation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.NoSuchElementException;

@SpringBootTest
public class FireStationRepositoryTest {

    @Autowired
    FireStationRepository fireStationRepository;

    static FireStation fireStation;
    static List<String> listAddresses;

    static final String addressTest = "address test";
    static final int stationTest1 = 1;
    static final int newStationTest = 8;

    @Test
    public void testNumberOfFireStationsInListFireStation () {
        //GIVEN WHEN
        List<FireStation> listFireStation = fireStationRepository.findAll();
        //THEN
        Assertions.assertEquals(fireStationRepository.getListFireStations().size(), listFireStation.size());
    }

    @Test
    public void testFireStationOfListIsOk () {
        Assertions.assertTrue(fireStationRepository.findById(stationTest1).isPresent());
    }

    @Test
    public void testFireStationNotInListIsKo () {
        Assertions.assertFalse(fireStationRepository.findById(fireStationRepository.getListFireStations().size()+1).isPresent());
    }

    @Test
    public void deleteMappingFireStationTest () {
        int nbFireStation = fireStationRepository.getListFireStations().size();
        fireStationRepository.deleteMappingFireStation(stationTest1);
        Assertions.assertEquals(nbFireStation-1,fireStationRepository.getListFireStations().size());
        Assertions.assertFalse(fireStationRepository.findById(stationTest1).isPresent());
    }

    @Test
    public void deleteMappingFireStationTest_WhenUnknownFireStation() {
        int nbFireStation = fireStationRepository.getListFireStations().size();
        Assertions.assertThrows(NoSuchElementException.class,() -> fireStationRepository.deleteMappingFireStation(nbFireStation+1));
    }

    @Test
    public void deleteMappingAddressTest () {
        String addressToDelete = fireStationRepository.getListFireStations().get(0).getAddresses().get(0);
        int nbAddresses = fireStationRepository.getListFireStations().get(0).getAddresses().size();
        fireStationRepository.deleteMappingAddress(addressToDelete);
        Assertions.assertEquals(nbAddresses-1, fireStationRepository.getListFireStations().get(0).getAddresses().size());
        Assertions.assertNotEquals(addressToDelete, fireStationRepository.getListFireStations().get(0).getAddresses().get(0));
    }

    @Test
    public void addMappingFireStationAddress_WhenExistingFireStation () {
        int nbAddresses = fireStationRepository.getListFireStations().get(0).getAddresses().size();
        int nbFireStations = fireStationRepository.getListFireStations().size();
        fireStationRepository.addMappingFiresStationAddress(fireStationRepository.getListFireStations().get(0).getStation(),addressTest);
        Assertions.assertEquals(nbFireStations,fireStationRepository.getListFireStations().size());
        Assertions.assertEquals(nbAddresses+1,fireStationRepository.getListFireStations().get(0).getAddresses().size());
        Assertions.assertTrue(fireStationRepository.getListFireStations().get(0).getAddresses().contains(addressTest));
    }

    @Test
    public void addMappingFireStationAddress_WhenNonExistingFireStation () {
        int nbFireStations = fireStationRepository.getListFireStations().size();
        fireStationRepository.addMappingFiresStationAddress(newStationTest,addressTest);
        Assertions.assertEquals(nbFireStations+1,fireStationRepository.getListFireStations().size());
    }
}

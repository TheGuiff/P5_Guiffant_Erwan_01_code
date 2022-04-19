package com.safetynet.alerts;

import com.safetynet.alerts.dal.repository.FireStationRepository;
import com.safetynet.alerts.domain.model.FireStation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SpringBootTest
public class FireStationRepositoryTest {

    @Autowired
    FireStationRepository fireStationRepository;

    static FireStation fireStation;
    static List<String> listAddresses;

    static final String address = "address";
    static final String addressTest = "address test";

    @Test
    public void testNumberOfFireStationsInListFireStation () {
        //GIVEN WHEN
        List<FireStation> listFireStation = fireStationRepository.findAll();
        //THEN
        Assertions.assertEquals(4, listFireStation.size());
    }

    @Test
    public void testFireStationOfListIsOk () {
        Assertions.assertTrue(fireStationRepository.findById(1).isPresent());
    }

    @Test
    public void testFireStationNotInListIsKo () {
        Assertions.assertFalse(fireStationRepository.findById(12).isPresent());
    }

    @Test
    public void testDeleteFireStation () {
        fireStationRepository.delete(1);
        Assertions.assertEquals(3, fireStationRepository.getListFireStations().size());
    }

    @Test
    public void testCreateFireStation () {
        //GIVEN
        listAddresses = new ArrayList<>();
        listAddresses.add(address);
        fireStation = new FireStation(5,listAddresses);
        //WHEN
        FireStation createdFireStation = fireStationRepository.save(fireStation);
        //THEN
        Assertions.assertEquals(5, fireStationRepository.getListFireStations().size());
    }

    @Test
    public void testUpdateFireStation () {
        //GIVEN
        listAddresses = fireStationRepository.getListFireStations().get(1).getAddresses();
        listAddresses.add(addressTest);
        fireStation = new FireStation(fireStationRepository.getListFireStations().get(1).getNumber(),listAddresses);
        //WHEN
        fireStationRepository.save(fireStation);
        //THEN
        Assertions.assertEquals(4, fireStationRepository.getListFireStations().size());
        Assertions.assertTrue(fireStationRepository.getListFireStations().stream()
                .anyMatch(f -> Objects.equals(f.getNumber(),fireStation.getNumber())
                && f.getAddresses().stream().anyMatch(a -> Objects.equals(a,addressTest))));
    }
}

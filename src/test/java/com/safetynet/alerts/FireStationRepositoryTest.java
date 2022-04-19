package com.safetynet.alerts;

import com.safetynet.alerts.dal.repository.FireStationRepository;
import com.safetynet.alerts.domain.model.FireStation;
import com.safetynet.alerts.domain.model.Person;
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
        List<String> listAddresses = new ArrayList<>();
        listAddresses.add("addresse");
        FireStation fireStation = new FireStation(5,listAddresses);
        //WHEN
        FireStation createdFireStation = fireStationRepository.save(fireStation);
        //THEN
        Assertions.assertEquals(5, fireStationRepository.getListFireStations().size());
    }
}

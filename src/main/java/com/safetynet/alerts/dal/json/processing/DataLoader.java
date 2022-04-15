package com.safetynet.alerts.dal.json.processing;

import com.safetynet.alerts.dal.json.data.JsonFile;
import com.safetynet.alerts.dal.repository.FireStationRepository;
import com.safetynet.alerts.dal.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    FireStationRepository fireStationRepository;

    public void jsonToData (JsonFile jsonFile) {

        FireStationsBuilder fireStationsBuilder = new FireStationsBuilder();
        PersonsBuilder personsBuilder = new PersonsBuilder();
        fireStationRepository.setListFireStations(fireStationsBuilder.getFireStations(jsonFile));
        personRepository.setListPersons(personsBuilder.getPersons(jsonFile));

    }
}

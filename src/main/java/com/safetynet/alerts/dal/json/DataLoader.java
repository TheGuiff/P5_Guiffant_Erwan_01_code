package com.safetynet.alerts.dal.json;

import com.safetynet.alerts.dal.data.Datas;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {

    public void JsonToData (JsonFile jsonFile) {

        FireStationsBuilder fireStationsBuilder = new FireStationsBuilder();
        PersonsBuilder personsBuilder = new PersonsBuilder();
    //TODO méthodes ne commencent pas par une majuscule !!!
        Datas.listFireStations = fireStationsBuilder.GetFireStations(jsonFile);
        Datas.listPersons = personsBuilder.GetPersons(jsonFile);

    }
}

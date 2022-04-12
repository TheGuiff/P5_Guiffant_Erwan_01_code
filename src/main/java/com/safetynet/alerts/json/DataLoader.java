package com.safetynet.alerts.json;

import com.safetynet.alerts.domain.model.Datas;
import com.safetynet.alerts.json.entity.JsonFile;

public class DataLoader {

    public void JsonToData (JsonFile jsonFile) {

        FireStationsBuilder fireStationsBuilder = new FireStationsBuilder();
        PersonsBuilder personsBuilder = new PersonsBuilder();

        Datas.listFireStations = fireStationsBuilder.GetFireStations(jsonFile);
        Datas.listPersons = personsBuilder.GetPersons(jsonFile);

    }
}

package com.safetynet.alerts.dal.json.processing;

import com.safetynet.alerts.dal.json.data.JsonFile;
import com.safetynet.alerts.dal.json.data.JsonFireStation;
import com.safetynet.alerts.domain.model.FireStation;

import java.util.List;
import java.util.stream.Collectors;

public class FireStationsBuilder {

    public List<FireStation> getFireStations(JsonFile jsonFile) {

        return jsonFile.getFirestations().stream()
                    .map(temp -> new FireStation(Integer.parseInt(temp.getStation()),
                            jsonFile.getFirestations().stream()
                                    .filter(fs -> Integer.parseInt(fs.getStation()) == Integer.parseInt(temp.getStation()))
                                    .map(JsonFireStation::getAddress)
                                    .collect(Collectors.toList())))
                .distinct()
                .collect(Collectors.toList());
    }
}

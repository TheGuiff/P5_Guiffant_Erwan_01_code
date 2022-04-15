package com.safetynet.alerts.dal.json.processing;

import com.safetynet.alerts.dal.json.data.JsonFile;
import com.safetynet.alerts.dal.json.data.JsonFireStation;
import com.safetynet.alerts.domain.model.FireStation;

import java.util.List;
import java.util.stream.Collectors;

public class FireStationsBuilder {

    public List<FireStation> getFireStations(JsonFile jsonFile) {

        return jsonFile.getFirestations().stream()
                    .map(temp -> {
                    FireStation fOut = new FireStation();
                    fOut.setNumber(Integer.parseInt(temp.getStation()));
                    fOut.setAdresses(jsonFile.getFirestations().stream()
                            .filter(fs -> Integer.parseInt(fs.getStation()) == fOut.getNumber())
                            .map(JsonFireStation::getAddress)
                            .collect(Collectors.toList()));
                    return fOut;
                })
                .distinct()
                .collect(Collectors.toList());
    }
}

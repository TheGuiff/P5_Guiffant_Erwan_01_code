package com.safetynet.alerts.json;

import com.safetynet.alerts.domain.model.FireStation;
import com.safetynet.alerts.json.entity.JsonFile;
import com.safetynet.alerts.json.entity.JsonFirestations;

import java.util.List;
import java.util.stream.Collectors;

public class FireStationsBuilder {

    public List<FireStation> GetFireStations (JsonFile jsonFile) {

        return jsonFile.getFirestations().stream()
                    .map(temp -> {
                    FireStation fOut = new FireStation();
                    fOut.setNumber(Integer.parseInt(temp.getStation()));
                    fOut.setAdresses(jsonFile.getFirestations().stream()
                            .filter(fs -> Integer.parseInt(fs.getStation()) == fOut.getNumber())
                            .map(JsonFirestations::getAddress)
                            .collect(Collectors.toList()));
                    return fOut;
                })
                .distinct()
                .collect(Collectors.toList());
    }
}

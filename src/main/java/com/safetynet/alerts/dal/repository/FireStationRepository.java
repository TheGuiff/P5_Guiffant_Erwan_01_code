package com.safetynet.alerts.dal.repository;

import com.safetynet.alerts.domain.model.FireStation;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@Setter
@Getter
public class FireStationRepository {

    public List<FireStation> listFireStations;

    public List<FireStation> findAll () {
        return this.listFireStations;
    }

    public Optional<FireStation> findById (int station) {
        return this.getListFireStations().stream()
                .filter(fireStation -> fireStation.getStation() == station)
                .findFirst();
    }

    public void deleteMappingFireStation(int station) throws NoSuchElementException {
        Optional<FireStation> optionalFireStation = findById(station);
        optionalFireStation.orElseThrow(() -> new NoSuchElementException("Unknown firestation with this number"));
        this.listFireStations.remove(optionalFireStation.get());
    }

    public void deleteMappingAddress(String address) {
        this.setListFireStations(this.getListFireStations().stream()
                .peek(fireStation -> fireStation.setAddresses(fireStation.getAddresses().stream()
                        .filter(a -> !Objects.equals(a, address))
                        .collect(Collectors.toList())))
                .collect(Collectors.toList()));
    }

    public FireStation addMappingFiresStationAddress(int station, String address) {
        Optional<FireStation> optionalFireStation = findById(station);
        FireStation fireStationOut;
        if (optionalFireStation.isPresent()) {
            this.setListFireStations(this.getListFireStations().stream()
                    .peek(fireStation -> {
                        if (fireStation.getStation() == station) {
                            List<String> listAddresses = fireStation.getAddresses();
                            listAddresses.add(address);
                            fireStation.setAddresses(listAddresses);
                        }
                    })
                    .collect(Collectors.toList()));
            fireStationOut = optionalFireStation.get();
        } else {
            List<String> addresses = new ArrayList<>();
            addresses.add(address);
            FireStation fireStation = new FireStation(station, addresses);
            this.listFireStations.add(fireStation);
            fireStationOut = fireStation;
        }
        return fireStationOut;
    }
}

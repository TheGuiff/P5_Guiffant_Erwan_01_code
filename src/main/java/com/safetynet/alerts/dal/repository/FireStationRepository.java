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

    public Optional<FireStation> findById (int number) {
        return this.getListFireStations().stream()
                .filter(fireStation -> fireStation.getNumber() == number)
                .findFirst();
    }

    public void deleteMappingFireStation(int number) throws NoSuchElementException {
        Optional<FireStation> optionalFireStation = findById(number);
        optionalFireStation.orElseThrow(NoSuchElementException::new);
        this.setListFireStations(this.getListFireStations().stream()
                .filter(fireStation -> fireStation.getNumber() != number)
                .collect(Collectors.toList()));
    }

    public void deleteMappingAddress(String address) {
        this.setListFireStations(this.getListFireStations().stream()
                .peek(fireStation -> fireStation.setAddresses(fireStation.getAddresses().stream()
                        .filter(a -> !Objects.equals(a, address))
                        .collect(Collectors.toList())))
                .collect(Collectors.toList()));
    }

    public FireStation addMappingFiresStationAddress(int number, String address) {
        Optional<FireStation> optionalFireStation = findById(number);
        if (optionalFireStation.isPresent()) {
            this.setListFireStations(this.getListFireStations().stream()
                    .peek(fireStation -> {
                        if (fireStation.getNumber() == number) {
                            List<String> listAddresses = fireStation.getAddresses();
                            listAddresses.add(address);
                            fireStation.setAddresses(listAddresses);
                        }
                    })
                    .collect(Collectors.toList()));
        } else {
            List<String> addresses = new ArrayList<>();
            addresses.add(address);
            FireStation fireStation = new FireStation(number, addresses);
            this.listFireStations.add(fireStation);
        }
        return findById(number).get();
    }
}

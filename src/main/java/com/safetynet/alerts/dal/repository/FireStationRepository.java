package com.safetynet.alerts.dal.repository;

import com.safetynet.alerts.domain.model.FireStation;
import com.safetynet.alerts.domain.model.Person;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
@Setter
@Getter
public class FireStationRepository {

    public List<FireStation> listFireStations;

    public List<FireStation> findAll () {
        return this.listFireStations;
    }

    public Optional<FireStation> findById (int number) {
        return this.listFireStations.stream()
                .filter(fireStation -> fireStation.getNumber() == number)
                .findFirst();
    }

    public void delete (int number) {
        Optional<FireStation> optionalFireStation = findById(number);
        optionalFireStation.orElseThrow(NoSuchElementException::new);
        this.listFireStations.remove(optionalFireStation.get());
    }

    public FireStation save (FireStation fireStation) {
        if (findById(fireStation.getNumber()).isPresent()) {
            delete(fireStation.getNumber());
        }
        this.listFireStations.add(fireStation);
        return fireStation;
    }
}

package com.safetynet.alerts.dal.repository;

import com.safetynet.alerts.domain.model.FireStation;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Setter
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
}

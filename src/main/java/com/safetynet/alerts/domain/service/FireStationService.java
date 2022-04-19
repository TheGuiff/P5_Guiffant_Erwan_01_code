package com.safetynet.alerts.domain.service;

import com.safetynet.alerts.dal.repository.FireStationRepository;
import com.safetynet.alerts.domain.model.FireStation;
import com.safetynet.alerts.web.dto.FireStationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FireStationService {

    @Autowired
    FireStationRepository fireStationRepository;

    public List<FireStation> getFireStations () {
        return fireStationRepository.findAll();
    }

    public FireStation getFireStation (int number) {
        Optional<FireStation> optionalFireStation = fireStationRepository.findById(number);
        optionalFireStation.orElseThrow(NoSuchElementException::new);
        return optionalFireStation.get();
    }

    public void deleteFireStation(int number) {
        fireStationRepository.delete(number);
    }

    public FireStation saveFireStation (FireStation fireStation) {
        return fireStationRepository.save(fireStation);
    }

    public FireStationDto fireStationToFireStationDto (FireStation fireStation) {
        FireStationDto fireStationDto = new FireStationDto();
        fireStationDto.setNumber(fireStation.getNumber());
        fireStationDto.setAdresses(fireStation.getAddresses());
        return fireStationDto;
    }

    public List<FireStationDto> listFireStationToFireStationDto (List<FireStation> listFireStation) {
        return listFireStation.stream().map(this::fireStationToFireStationDto).collect(Collectors.toList());
    }


}

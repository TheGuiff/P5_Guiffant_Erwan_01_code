package com.safetynet.alerts.domain.service;

import com.safetynet.alerts.dal.repository.FireStationRepository;
import com.safetynet.alerts.web.dto.FireStationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class FireStationService {

    @Autowired
    FireStationRepository fireStationRepository;

    public Iterable<FireStationDto> getFireStations () {
        return fireStationRepository.findAll().stream().map(f -> {
            FireStationDto fireStationDto = new FireStationDto();
            fireStationDto.setNumber(f.getNumber());
            fireStationDto.setAdresses(f.getAdresses());
            return fireStationDto;
        }).collect(Collectors.toList());
    }

    public FireStationDto getFireStation (int number) {
        return fireStationRepository.findAll().stream()
                .filter(f -> f.getNumber() == number)
                .map(f -> {
                    FireStationDto fireStationDto = new FireStationDto();
                    fireStationDto.setNumber(f.getNumber());
                    fireStationDto.setAdresses(f.getAdresses());
                    return fireStationDto;
                })
                .collect(Collectors.toList()).get(0);
    }


}

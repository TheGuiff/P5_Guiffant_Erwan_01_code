package com.safetynet.alerts.domain.service;

import com.safetynet.alerts.dal.data.Datas;
import com.safetynet.alerts.web.dto.FireStationDto;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class FireStationService {

    public Iterable<FireStationDto> getFireStations () {
        return Datas.listFireStations.stream().map(f -> {
            FireStationDto fireStationDto = new FireStationDto();
            fireStationDto.setNumber(f.getNumber());
            fireStationDto.setAdresses(f.getAdresses());
            return fireStationDto;
        }).collect(Collectors.toList());
    }

    public FireStationDto getFireStation (int number) {
        return Datas.listFireStations.stream()
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

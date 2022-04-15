package com.safetynet.alerts.web.controller;

import com.safetynet.alerts.web.dto.FireStationDto;
import com.safetynet.alerts.domain.service.FireStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FireStationCrudController {

    @Autowired
    FireStationService fireStationService;

    @GetMapping("/firestation")
    public Iterable<FireStationDto> getFireStations() {
        return fireStationService.getFireStations();
    }

    @GetMapping("/firestation/{number}")
    public FireStationDto getFireStation (@PathVariable("number") final int number) {
        return fireStationService.getFireStation(number);
    }
}

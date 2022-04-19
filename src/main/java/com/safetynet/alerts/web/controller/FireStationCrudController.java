package com.safetynet.alerts.web.controller;

import com.safetynet.alerts.domain.model.FireStation;
import com.safetynet.alerts.web.dto.FireStationDto;
import com.safetynet.alerts.domain.service.FireStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FireStationCrudController {

    @Autowired
    FireStationService fireStationService;

    @GetMapping("/firestation")
    public List<FireStationDto> getFireStations() {
        return fireStationService.listFireStationToFireStationDto(fireStationService.getFireStations());
    }

    @GetMapping("/firestation/{number}")
    public FireStationDto getFireStation (@PathVariable("number") final int number) {
        return fireStationService.fireStationToFireStationDto(fireStationService.getFireStation(number));
    }

    @DeleteMapping("/firestation/{number}")
    public void deleteFireStation(@PathVariable("number") final int number) {
        fireStationService.deleteFireStation(number);
    }

    @PostMapping("firestation/{number},{addresses}")
    public FireStationDto saveFireStation(@PathVariable("number") final int number,
                                @PathVariable("addresses") final List<String> addresses) {
        FireStation fireStation = new FireStation(number, addresses);
        return fireStationService.fireStationToFireStationDto(fireStationService.saveFireStation(fireStation));
    }
}

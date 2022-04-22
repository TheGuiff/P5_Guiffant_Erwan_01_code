package com.safetynet.alerts.web.controller;

import com.safetynet.alerts.web.dto.FireStationDto;
import com.safetynet.alerts.domain.service.FireStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/firestation")
public class FireStationController {

    @Autowired
    FireStationService fireStationService;

    @GetMapping("")
    public List<FireStationDto> getFireStations() {
        return fireStationService.listFireStationToFireStationDto(fireStationService.getFireStations());
    }

    @GetMapping("/{station}")
    public FireStationDto getFireStation (@PathVariable("station") final int station) {
        return fireStationService.fireStationToFireStationDto(fireStationService.getFireStation(station));
    }

    @PostMapping("")
    public FireStationDto addMappingFireStationAddress(@RequestParam("station") final int station,
                                                       @RequestParam("address") final String address) {
        return fireStationService.fireStationToFireStationDto(fireStationService.addMappingFiresStationAddress(station, address));
    }

    @DeleteMapping("/{station}")
    public void deleteMappingStation(@PathVariable("station") final int station) {
        fireStationService.deleteMappingFireStation(station);
    }

    @DeleteMapping("")
    public void deleteMappingAddress(@RequestParam("address") final String address) {
        fireStationService.deleteMappingAddress(address);
    }

    @PutMapping("")
    public FireStationDto updateMappingFireStationAddress (@RequestParam("station") final int station,
                                                           @RequestParam("address") final String address) {
        return fireStationService.fireStationToFireStationDto(fireStationService.updateMappingFireStationAddress(station, address));
    }

}

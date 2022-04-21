package com.safetynet.alerts.web.controller;

import com.safetynet.alerts.web.dto.FireStationDto;
import com.safetynet.alerts.domain.service.FireStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FireStationController {

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

    @PostMapping("firestation/{number},{address}")
    public FireStationDto addMappingFireStationAddress(@PathVariable("number") final int number,
                                                       @PathVariable("address") final String address) {
        return fireStationService.fireStationToFireStationDto(fireStationService.addMappingFiresStationAddress(number, address));
    }

    @DeleteMapping("/firestation/{numberoraddress}")
    public void deleteMapping(@PathVariable("numberoraddress") final String numberOrAddress) {
        if (numberOrAddress.matches("-?\\d+")) {
            fireStationService.deleteMappingFireStation(Integer.parseInt(numberOrAddress));
        }else {
            fireStationService.deleteMappingAddress(numberOrAddress);
        }
    }

    @PutMapping("firestation/{number},{address}")
    public FireStationDto updateMappingFireStationAddress (@PathVariable("number") final int number,
                                                           @PathVariable("address") final String address) {
        return fireStationService.fireStationToFireStationDto(fireStationService.updateMappingFireStationAddress(number, address));
    }

}

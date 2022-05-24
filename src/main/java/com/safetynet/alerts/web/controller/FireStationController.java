package com.safetynet.alerts.web.controller;

import com.safetynet.alerts.domain.service.PersonService;
import com.safetynet.alerts.web.dto.FireStationDto;
import com.safetynet.alerts.domain.service.FireStationService;
import com.safetynet.alerts.web.dto.ListPersonsOfAFireStationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@Slf4j
@RequestMapping("/firestation")
public class FireStationController {

    @Autowired
    FireStationService fireStationService;

    @Autowired
    PersonService personService;

    @GetMapping("/{station}")
    public ResponseEntity<?> getFireStation (@PathVariable("station") final int station) {
        log.info("get firestation number : {}",station);
        try {
            FireStationDto fireStationDto = fireStationService.fireStationToFireStationDto(fireStationService.getFireStation(station));
            return ResponseEntity.ok(fireStationDto);
        } catch (NoSuchElementException e) {
            log.error("GET /firestation error:{}",e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("")
    public ResponseEntity<?> personsByFireStation(@RequestParam("stationNumber") int station) {
        log.info("List of persons depending on firestation n°{}", station);
        try {
            ListPersonsOfAFireStationDto listPersonsOfAFireStationDto = personService
                    .listPersonsByAFireSation(personService
                            .listPersonsByListAddresses(fireStationService.listAddressesByStation(station)));
            return ResponseEntity.ok(listPersonsOfAFireStationDto);
        } catch (NoSuchElementException e) {
            log.error("GET /list persons covered by a firestation error:{}",e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public FireStationDto addMappingFireStationAddress(@RequestParam("station") final int station,
                                                       @RequestParam("address") final String address) {
        log.info("Add mapping between firestation {} and address {}", station, address);
        return fireStationService.fireStationToFireStationDto(fireStationService.addMappingFiresStationAddress(station, address));
    }

    @PutMapping("")
    public FireStationDto updateMappingFireStationAddress (@RequestParam("station") final int station,
                                                           @RequestParam("address") final String address) {
        log.info("Update mapping between firestation {} and address {}", station, address);
        return fireStationService.fireStationToFireStationDto(fireStationService.updateMappingFireStationAddress(station, address));
    }

    @DeleteMapping("/{station}")
    public ResponseEntity<?> deleteMappingStation(@PathVariable("station") final int station) {
        log.info("Delete mapping firestation {}", station);
        try {
            fireStationService.deleteMappingFireStation(station);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            log.error("GET /firestation error:{}",e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("")
    public void deleteMappingAddress(@RequestParam("address") final String address) {
        log.info("Delete mapping address {}", address);
        fireStationService.deleteMappingAddress(address);
    }

}

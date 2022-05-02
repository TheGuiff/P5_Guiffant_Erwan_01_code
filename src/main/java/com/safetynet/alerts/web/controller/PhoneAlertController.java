package com.safetynet.alerts.web.controller;

import com.safetynet.alerts.domain.service.FireStationService;
import com.safetynet.alerts.domain.service.PersonService;
import com.safetynet.alerts.domain.service.PhoneAlertService;
import com.safetynet.alerts.web.dto.PhoneAlertDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@Slf4j
@RequestMapping("/phoneAlert")
public class PhoneAlertController {

    @Autowired
    PersonService personService;

    @Autowired
    FireStationService fireStationService;

    @Autowired
    PhoneAlertService phoneAlertService;

    @GetMapping("")
    public ResponseEntity<?> phoneAlert(@RequestParam("firestation") int station) {
        log.info("phoneAlert for firestation {}", station);
        try {
            PhoneAlertDto phoneAlertDto = phoneAlertService.listPersonToPhoneAlertDto(personService.listPersonsByListAddresses(fireStationService.listAddressesByStation(station)));
            return ResponseEntity.ok(phoneAlertDto);
        } catch (NoSuchElementException e) {
            log.error("GET /phoneAlert error:{}",e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}

package com.safetynet.alerts.web.controller;

import com.safetynet.alerts.domain.service.FireStationService;
import com.safetynet.alerts.domain.service.PersonService;
import com.safetynet.alerts.web.dto.PhoneAlertDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/phoneAlert")
public class PhoneAlertController {

    @Autowired
    PersonService personService;

    @Autowired
    FireStationService fireStationService;

    @GetMapping("")
    public PhoneAlertDto phoneAlert(@RequestParam("firestation") int station) {
        return personService.listPersonToPhoneAlertDto(personService.listPersonsByListAddresses(fireStationService.listAddressesByStation(station)));
    }
}

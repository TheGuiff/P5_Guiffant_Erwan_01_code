package com.safetynet.alerts.web.controller;

import com.safetynet.alerts.domain.service.ChildAlertService;
import com.safetynet.alerts.domain.service.PersonService;
import com.safetynet.alerts.web.dto.ChildAlertDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/childAlert")
public class ChildAlertController {

    @Autowired
    ChildAlertService childAlertService;

    @Autowired
    PersonService personService;

    @GetMapping("")
    public ChildAlertDto getListOfChildrenByAddress(@RequestParam("address") String address) {
        log.info("ChilAlert on address {}", address);
        List<String> listAddress = new ArrayList<>();
        listAddress.add(address);
        return childAlertService.childAlert(personService.listPersonsByListAddresses(listAddress));
    }
}

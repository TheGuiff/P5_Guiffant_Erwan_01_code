package com.safetynet.alerts.web.controller;

import com.safetynet.alerts.domain.service.PersonService;
import com.safetynet.alerts.web.dto.CommunityEmailDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
@RequestMapping("/communityEmail")
public class CommunityEmailController {

    @Autowired
    PersonService personService;

    @GetMapping("")
    public CommunityEmailDto getListOfEmailsByCity(@RequestParam("city") String city) {
        return personService.communityEmail(city);
    }
}
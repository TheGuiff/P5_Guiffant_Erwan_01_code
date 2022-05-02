package com.safetynet.alerts.web.controller;

import com.safetynet.alerts.domain.service.PersonInfoService;
import com.safetynet.alerts.web.dto.PersonInfoDto;
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
@RequestMapping("/personInfo")
public class PersonInfoController {

    @Autowired
    PersonInfoService personInfoService;

    @GetMapping("")
    public ResponseEntity<?> personInfo(@RequestParam("firstName") String firstName,
                                        @RequestParam("lastName") String lastName) {
        log.info("personInfo on {}, {}", firstName, lastName );
        try {
            PersonInfoDto personInfoDto = personInfoService.personInfo(firstName, lastName);
            return ResponseEntity.ok(personInfoDto);
        } catch (NoSuchElementException e) {
            log.error("GET /personInfo error:{}",e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}

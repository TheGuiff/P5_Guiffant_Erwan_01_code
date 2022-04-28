package com.safetynet.alerts.web.controller;

import com.safetynet.alerts.domain.service.FireService;
import com.safetynet.alerts.web.dto.ListFireDto;
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
@RequestMapping("/fire")
public class FireController {

    @Autowired
    FireService fireService;

    @GetMapping("")
    public ResponseEntity<?> personInfo(@RequestParam("address") String address) {
        try {
            ListFireDto listFireDto = fireService.fire(address);
            return ResponseEntity.ok(listFireDto);
        } catch (NoSuchElementException e) {
            log.error("GET /fire error:{}",e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}

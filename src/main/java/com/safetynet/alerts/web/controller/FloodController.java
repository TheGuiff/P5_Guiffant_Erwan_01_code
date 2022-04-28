package com.safetynet.alerts.web.controller;

import com.safetynet.alerts.domain.service.FloodService;
import com.safetynet.alerts.web.dto.FireAndFloodByStationDto;
import com.safetynet.alerts.web.dto.FloodStationsInDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@Slf4j
@RequestMapping("/flood")
public class FloodController {

    @Autowired
    FloodService floodService;

    @GetMapping("/stations")
    public ResponseEntity<?> personInfo(@RequestBody FloodStationsInDto stationsIn) {
        try {
            List<FireAndFloodByStationDto> fireAndFloodByStationDtos = floodService.flood(stationsIn.getStations());
            return ResponseEntity.ok(fireAndFloodByStationDtos);
        } catch (NoSuchElementException e) {
            log.error("GET /flood/stations error:{}",e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}

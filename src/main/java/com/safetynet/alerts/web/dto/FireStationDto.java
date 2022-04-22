package com.safetynet.alerts.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FireStationDto {

    private int station;
    private List<String> adresses;

}

package com.safetynet.alerts.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListFireDto {

    private int station;
    private List<FireDto> inhabitantsList;

}

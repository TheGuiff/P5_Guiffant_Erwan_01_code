package com.safetynet.alerts.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FireAndFloodByStationDto {

    private int station;
    private List<FireAndFloodByAddressDto> listAddresses;

}

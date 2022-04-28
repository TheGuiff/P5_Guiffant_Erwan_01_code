package com.safetynet.alerts.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FireAndFloodByAddressDto {

    private String address;
    private List<FireAndFloodDto> inhabitantsList;

}

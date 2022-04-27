package com.safetynet.alerts.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListPersonsOfAFireStationDto {

    List<PersonsCoveredByAFireStationDto> personsCovered;
    long numberOfAdults;
    long numberOfChilds;

}

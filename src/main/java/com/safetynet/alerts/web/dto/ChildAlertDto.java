package com.safetynet.alerts.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChildAlertDto {

    private List<ChildAlertMemberDto> listChildren;
    private List<ChildAlertMemberDto> listAdults;

}

package com.safetynet.alerts.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Address {

    private String addressNAme;
    private String city;
    private int zipCode;
    private int fireStation;

}

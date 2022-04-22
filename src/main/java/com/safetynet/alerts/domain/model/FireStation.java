package com.safetynet.alerts.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FireStation {

    private int station;
    private List<String> addresses;

    public FireStation (int number,
                        List<String> addresses) {
        this.setStation(number);
        this.setAddresses(addresses);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof FireStation &&  ((FireStation) obj).getStation() == this.getStation();
    }

    @Override
    public int hashCode() {
        return this.getStation();
    }
}

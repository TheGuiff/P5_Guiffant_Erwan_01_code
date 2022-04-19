package com.safetynet.alerts.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FireStation {

    private int number;
    private List<String> addresses;

    public FireStation (int number,
                        List<String> addresses) {
        this.setNumber(number);
        this.setAddresses(addresses);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof FireStation &&  ((FireStation) obj).getNumber() == this.getNumber();
    }

    @Override
    public int hashCode() {
        return this.getNumber();
    }
}

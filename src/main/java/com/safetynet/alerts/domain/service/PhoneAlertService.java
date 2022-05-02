package com.safetynet.alerts.domain.service;

import com.safetynet.alerts.domain.model.Person;
import com.safetynet.alerts.web.dto.PhoneAlertDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhoneAlertService {

    public PhoneAlertDto listPersonToPhoneAlertDto(List<Person> listPerson) {
        PhoneAlertDto phoneAlertDto = new PhoneAlertDto();
        phoneAlertDto.setListPhones(listPerson.stream().map(Person::getPhone).collect(Collectors.toList()));
        return phoneAlertDto;
    }

}

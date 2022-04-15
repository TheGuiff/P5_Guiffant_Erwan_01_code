package com.safetynet.alerts.web.controller;

import com.safetynet.alerts.web.dto.PersonDto;
import com.safetynet.alerts.domain.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonCrudController {

    @Autowired
    PersonService personService;

    @GetMapping("/person")
    public Iterable<PersonDto> getPersons() {
        return personService.getPersons();
    }

    @GetMapping("/person/{firstName},{lastName}")
    public PersonDto getPerson(@PathVariable("firstName") final String firstName,  @PathVariable("lastName") final String lastName) {
        return personService.getPerson(firstName, lastName);
    }

}

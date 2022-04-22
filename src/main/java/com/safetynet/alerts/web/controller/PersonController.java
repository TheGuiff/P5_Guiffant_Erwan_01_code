package com.safetynet.alerts.web.controller;

import com.safetynet.alerts.web.dto.PersonDto;
import com.safetynet.alerts.domain.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@Slf4j
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping("")
    public List<PersonDto> getListPersons() {
        return personService.listPersonToPersonDto(personService.getPersons());
    }

    @GetMapping("/{firstName},{lastName}")
    public PersonDto getPersonByFirstNameAndLastName(@PathVariable("firstName") final String firstName,
                                                     @PathVariable("lastName") final String lastName) {
        return personService.personToPersonDto(personService.getPerson(firstName, lastName));
    }

    @DeleteMapping("") //Idem au-dessus
    public ResponseEntity<?> deletePerson(@RequestParam("firstName") final String firstName,
                                       @RequestParam("lastName") final String lastName) {
        log.info("");
        try {
            personService.deletePerson(firstName, lastName);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            log.error("DELETE /person error:{}",e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public PersonDto addPerson(@RequestBody PersonDto personDto) {
        return personService.personToPersonDto(personService.savePerson(personDto.getFirstName(),
                personDto.getLastName(),
                personDto.getAddress(),
                personDto.getCity(),
                personDto.getZip(),
                personDto.getPhone(),
                personDto.getEmail()));
    }

    @PutMapping("")
    public PersonDto updatePerson(@RequestBody PersonDto personDto) {
        return personService.personToPersonDto(personService.savePerson(personDto.getFirstName(),
                personDto.getLastName(),
                personDto.getAddress(),
                personDto.getCity(),
                personDto.getZip(),
                personDto.getPhone(),
                personDto.getEmail()));
    }

}

package com.iabdinur.person;

import com.iabdinur.SortingOrder;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/persons")
public class PersonController {

    private final PersonService personService;
    private final Validator validator;

    public PersonController(PersonService personService,
                            Validator validator) {
        this.personService = personService;
        this.validator = validator;
    }

    @GetMapping
    public List<Person> getPeople(
            @RequestParam(
                    value = "sort",
                    required = false,
                    defaultValue = "ASC"
            ) SortingOrder sort) {
        return personService.getPeople(sort);
    }


    @GetMapping("{id}")
    public ResponseEntity<Person> getPersonById(
            @Valid @Positive @PathVariable("id") Integer id
    ) {
        Person person = personService.getPersonById(id);
        return ResponseEntity.ok().body(person);
    }

    @DeleteMapping("{id}")
    public void deletePersonById(
            @Valid @Positive @PathVariable("id") Integer id) {
        personService.deletePersonById(id);
    }

    @PostMapping
    public void addPerson(@Valid @RequestBody NewPersonRequest person) {
        /*
            Set<ConstraintViolation<NewPersonRequest>> validate =
                    validator.validate(person);
            validate.forEach(error -> System.out.println(error.getMessage()));
            if(!validate.isEmpty()) {
                throw new ConstraintViolationException(validate);
            }
        */
        personService.addPerson(person);
    }

    @PutMapping("{id}")
    public void updatePerson(@Valid @Positive @PathVariable("id") Integer id,
                             @RequestBody PersonUpdateRequest request) {
        personService.updatePerson(id, request);
    }

}
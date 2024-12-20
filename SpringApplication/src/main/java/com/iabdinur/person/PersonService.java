package com.iabdinur.person;

import com.iabdinur.SortingOrder;
import com.iabdinur.exception.DuplicateResourceException;
import com.iabdinur.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    public List<Person> getPeople(SortingOrder sort) {
        if (sort == SortingOrder.ASC) {
            return personRepository.getPeople().stream()
                    .sorted(Comparator.comparing(Person::id))
                    .collect(Collectors.toList());
        }

        return personRepository.getPeople().stream()
                .sorted(Comparator.comparing(Person::id).reversed())
                .collect(Collectors.toList());
    }


    public Person getPersonById(Integer id) {
        return personRepository.getPeople().stream()
                .filter(person -> person.id() == id)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Person with id: " + id + " does not exist"));
    }


    public void deletePersonById(Integer id) {
        Person person = personRepository.getPeople().stream()
                .filter(p -> p.id() == id)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Person with id: " + id + " does not exist"));
        personRepository.getPeople().remove(person);
    }


    public void addPerson(NewPersonRequest person) {

        if(person.email() != null && !person.email().isEmpty()) {
            boolean exists = personRepository.getPeople().stream()
                    .anyMatch(p -> p.email().equalsIgnoreCase(person.email()));
            if(exists) {
                throw new DuplicateResourceException("This email is taken ");
            }
        }

        personRepository.getPeople().add(new Person(
                personRepository.getIdCounter().incrementAndGet(),
                person.name(),
                person.age(),
                person.gender(),
                person.email()));
    }


    public void updatePerson(Integer id, PersonUpdateRequest request) {
        Person p = personRepository.getPeople().stream()
                .filter(person -> person.id() == id)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Person with id: " + id + " does not exist"));

                    var index = personRepository.getPeople().indexOf(p);

                    if (request.name() != null &&
                            !request.name().isEmpty() &&
                            !request.name().equals(p.name())) {
                        Person person = new Person(
                                p.id(),
                                request.name(),
                                p.age(),
                                p.gender(),
                                p.email()
                        );
                        personRepository.getPeople().set(index, person);
                    }

                    if (request.age() != null && !request.age().equals(p.age())) {
                        Person person = new Person(
                                p.id(),
                                p.name(),
                                request.age(),
                                p.gender(),
                                p.email()
                        );
                        personRepository.getPeople().set(index, person);
                    }
    }

}
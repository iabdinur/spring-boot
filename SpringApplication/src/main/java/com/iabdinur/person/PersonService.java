package com.iabdinur.person;

import com.iabdinur.SortingOrder;
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


    public Optional<Person> getPersonById(Integer id) {
        return personRepository.getPeople().stream()
                .filter(person -> person.id() == id)
                .findFirst();
    }


    public void deletePersonById(Integer id) {
        personRepository.getPeople().removeIf(person -> person.id() == id);
    }


    public void addPerson(NewPersonRequest person) {
        personRepository.getPeople().add(new Person(
                personRepository.getIdCounter().incrementAndGet(),
                person.name(),
                person.age(),
                person.gender()));
    }


    public void updatePerson(Integer id, PersonUpdateRequest request) {
        personRepository.getPeople().stream()
                .filter(p -> p.id() == id)
                .findFirst()
                .ifPresent(p -> {
                    var index = personRepository.getPeople().indexOf(p);

                    if (request.name() != null &&
                            !request.name().isEmpty() &&
                            !request.name().equals(p.name())) {
                        Person person = new Person(
                                p.id(),
                                request.name(),
                                p.age(),
                                p.gender()
                        );
                        personRepository.getPeople().set(index, person);
                    }

                    if (request.age() != null && !request.age().equals(p.age())) {
                        Person person = new Person(
                                p.id(),
                                p.name(),
                                request.age(),
                                p.gender()
                        );
                        personRepository.getPeople().set(index, person);
                    }
                });
    }

}
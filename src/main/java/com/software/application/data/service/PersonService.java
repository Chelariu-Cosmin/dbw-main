package com.software.application.data.service;

import com.software.application.data.entity.Person;
import com.software.application.data.repositories.PersonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Optional<Person> get(Long id) {
        return personRepository.findById(id);
    }

    public void addPerson(Person person) {
        if (person == null) {
            System.err.println("Person is null. Are you sure you have connected your form to the application?");
            return;
        }
         personRepository.save(person);
    }

    public void deletePerson(Person person) {
        personRepository.delete(person);
    }

    public Page<Person> list(Pageable pageable) {
        return personRepository.findAll(pageable);
    }

    public Page<Person> list(Pageable pageable, Specification<Person> filter) {
        return personRepository.findAll(filter, pageable);
    }

    public int count() {
        return (int) personRepository.count();
    }

    public List<Person> findAll(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return personRepository.findAll();
        } else {
            return personRepository.search(stringFilter);
        }
    }
}

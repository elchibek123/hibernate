package java15.service;

import java15.entities.Person;

public interface PersonService {
    String createPerson(Person person);

    Person getPersonById(Long id);

    String deletePerson(Long id);
}

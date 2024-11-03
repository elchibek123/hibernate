package java15.service.impl;

import java15.dao.PersonDao;
import java15.entities.Person;
import java15.service.PersonService;

public class PersonServiceImpl implements PersonService {
    private final PersonDao personDao;

    public PersonServiceImpl(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    public String createPerson(Person person) {
        return personDao.save(person);
    }

    @Override
    public Person getPersonById(Long id) {
        return personDao.findById(id);
    }

    @Override
    public String deletePerson(Long id) {
        return personDao.delete(id);
    }
}

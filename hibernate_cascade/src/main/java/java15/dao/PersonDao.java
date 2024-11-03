package java15.dao;

import java15.entities.Person;

public interface PersonDao {
    String save(Person person);

    Person findById(Long id);

    String delete(Long id);
}

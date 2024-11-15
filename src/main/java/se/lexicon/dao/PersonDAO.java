package se.lexicon.dao;

import se.lexicon.model.Person;

import java.util.Collection;

public interface PersonDAO {
    void persist(Person person);
    Person findById(int id);
    Person findByEmail(String Email);
    Collection<Person> findAll();
    void remove (int id);
}

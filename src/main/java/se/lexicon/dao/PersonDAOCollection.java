package se.lexicon.dao;

import se.lexicon.model.Person;
import se.lexicon.sequencers.PersonIdSequencer;
import se.lexicon.sequencers.TodoItemTaskIdSequencer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;


public class PersonDAOCollection implements PersonDAO {
    private ArrayList<Person> persons = new ArrayList<>();


    @Override
    public void persist(Person person) {
        int id = PersonIdSequencer.getInstance().nextId();
        person.setId(id);
        persons.add(person);
    }

    @Override
    public Person findById(int id) {
        for (Person person : persons)
            if (person.getId()==id){
                return person;
            }
        return null;
    }

    @Override
    public Person findByEmail(String email) {
        for (Person person : persons) {
            if (person.getEmail().equals(email)) {
                return person;
            }
        }
        return null;
    }

    @Override
    public Collection<Person> findAll() {
        return new ArrayList<>(persons);
    }

    @Override
    public void remove(int id) {
        Person toRemove = null;
        for (Person person : persons){
            if(Objects.equals(person.getId(), id)) toRemove = person;
        }
        if (toRemove != null) persons.remove(toRemove);
    }
}

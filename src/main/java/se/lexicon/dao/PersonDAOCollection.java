package se.lexicon.dao;

import se.lexicon.Person;
import se.lexicon.sequencers.PersonIdSequencer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class PersonDAOCollection implements PersonDAO {
    private List<Person> personList = new ArrayList<>();


    @Override
    public void persist(Person person) {
        if (person.getId() == 1){
            person.setId(PersonIdSequencer.nextId());
        }
        personList.add(person);
    }

    @Override
    public Person findById(int id) {
        for (Person person : personList)
            if (person.getId()==id){
                return person;
            }
        return null;
    }

    @Override
    public Person findByEmail(String email) {
        for (Person person : personList) {
            if (person.getEmail().equals(email)) {
                return person;
            }
        }
        return null;
    }

    @Override
    public Collection<Person> findAll() {
        return new ArrayList<>(personList);
    }

    @Override
    public void remove(int id) {
        Iterator<Person> iterator = personList.iterator();
        while (iterator.hasNext()) {
            Person person = iterator.next();
            if (person.getId() == id) {
                iterator.remove();;
                break;
            }
        }
    }
}

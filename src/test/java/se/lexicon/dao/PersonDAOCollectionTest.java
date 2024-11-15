package se.lexicon.dao;


import org.junit.Test;
import org.junit.Before;
import se.lexicon.model.Person;

import static org.junit.Assert.*;
import java.util.Collection;





public class PersonDAOCollectionTest {
    private PersonDAO personDAO;

    @Before
    public void setUp(){
        personDAO = new PersonDAOCollection();

    }


    @Test
    public void persist() {
        Person person = new Person("Linus", "Carl", "Linus.Carl@example.com");
        personDAO.persist(person);
        assertEquals(1, personDAO.findAll().size());
    }

    @Test
    public void findById() {
        Person person = new Person("Linus", "Carl", "Linus.Carl@example.com");
        personDAO.persist(person);
        Person foundPerson = personDAO.findById(person.getId());
        assertNotNull(foundPerson);
        assertEquals("Linus", foundPerson.getFirstName());
    }

    @Test
    public void findByEmail() {
        Person person = new Person("Linus", "Carl", "Linus.Carl@example.com");
        personDAO.persist(person);
        Person foundPerson= personDAO.findByEmail("Linus.Carl@example.com");
        assertNotNull(foundPerson);
        assertEquals("Linus", foundPerson.getFirstName());

    }

    @Test
    public void findAll() {
        Person person1 = new Person("Linus", "Carl", "Linus.Carl@example.com");
        Person person2 = new Person("Carl", "Linus", "Linus.Carl@example.com");
        personDAO.persist(person1);
        personDAO.persist(person2);
        Collection<Person> allPersons = personDAO.findAll();
        assertEquals(2, allPersons.size());
    }

    @Test
    public void remove() {
        Person person = new Person("Linus", "Carl", "Linus.Carl@example.com");
        personDAO.persist(person);
        personDAO.remove(person.getId());
        assertNull(personDAO.findById(person.getId()));
        assertEquals(0, personDAO.findAll().size());
    }
}
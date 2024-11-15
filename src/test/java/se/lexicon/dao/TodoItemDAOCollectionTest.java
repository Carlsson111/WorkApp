package se.lexicon.dao;

import org.junit.Before;
import org.junit.Test;
import se.lexicon.model.Person;
import se.lexicon.model.TodoItem;

import java.time.LocalDate;
import java.util.Collection;

import static org.junit.Assert.*;

public class TodoItemDAOCollectionTest {
    private TodoItemDAO todoItemDAO;
    private Person creator;

    @Before
    public void setUp() {
        todoItemDAO= new TodoItemDAOCollection();
        creator =new Person("Linus","Carlsson","test.te@tesu,com");

    }

    @Test
    public void persist() {
        TodoItem todoItem= new TodoItem(0,"Task 1","Change tires", LocalDate.of(2024,11,15),false, creator);
        todoItemDAO.persist(todoItem);
        assertEquals(1,todoItemDAO.findAll().size());

    }

    @Test
    public void findById() {
        TodoItem todoItem= new TodoItem(0,"Task 1","Change tires", LocalDate.of(2024,11,15),false, creator);
        todoItemDAO.persist(todoItem);
        TodoItem foundId = todoItemDAO.findById(todoItem.getId());
        assertEquals("Task 1", foundId.getTitle());
    }

    @Test
    public void findAll() {
    }

    @Test
    public void findAllByDoneStatus() {
    }

    @Test
    public void findByTitleContains() {
    }

    @Test
    public void findByPersonId() {
    }

    @Test
    public void findByDeadlineBefore() {
    }

    @Test
    public void findByDeadlineAfter() {
    }

    @Test
    public void remove() {
    }
}
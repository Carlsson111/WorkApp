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
        TodoItem todoItem1= new TodoItem(0,"Task 1","Change tires", LocalDate.of(2024,11,15),false, creator);
        TodoItem todoItem2= new TodoItem(1,"Task 2","Change back tires", LocalDate.of(2024,11,15),true, creator);
        todoItemDAO.persist(todoItem1);
        todoItemDAO.persist(todoItem2);
        Collection<TodoItem> allItems = todoItemDAO.findAll();
        assertEquals(2, allItems.size());
    }

    @Test
    public void findAllByDoneStatus() {
        TodoItem todoItem1= new TodoItem(0,"Task 1","Change tires", LocalDate.of(2024,11,15),false, creator);
        TodoItem todoItem2= new TodoItem(1,"Task 2","Change back tires", LocalDate.of(2024,11,15),true, creator);
        todoItemDAO.persist(todoItem1);
        todoItemDAO.persist(todoItem2);
        Collection<TodoItem> doneItems = todoItemDAO.findAllByDoneStatus(true);
        assertEquals(1,doneItems.size());
    }

    @Test
    public void findByTitleContains() {
        TodoItem todoItem1= new TodoItem(0,"Task 1","Change tires", LocalDate.of(2024,11,15),false, creator);
        TodoItem todoItem2= new TodoItem(1,"Task 2","Change back tires", LocalDate.of(2024,11,15),true, creator);
        todoItemDAO.persist(todoItem1);
        todoItemDAO.persist(todoItem2);
        Collection<TodoItem> taskItems = todoItemDAO.findByTitleContains("Task");
        assertEquals(2,taskItems.size());
    }

    @Test
    public void findByPersonId() {
        TodoItem todoItem1= new TodoItem(0,"Task 1","Change tires", LocalDate.of(2024,11,15),false, creator);
        TodoItem todoItem2= new TodoItem(0,"Task 2","Change back tires", LocalDate.of(2024,11,15),true, creator);
        todoItemDAO.persist(todoItem1);
        todoItemDAO.persist(todoItem2);
        Collection<TodoItem> personItems = todoItemDAO.findByPersonId(1);
        assertEquals(2,personItems.size());

    }

    @Test
    public void findByDeadlineBefore() {
        TodoItem todoItem1= new TodoItem(0,"Task 1","Change tires", LocalDate.of(2024,11,15),false, creator);
        TodoItem todoItem2= new TodoItem(0,"Task 2","Change back tires", LocalDate.of(2024,11,17),true, creator);
        todoItemDAO.persist(todoItem1);
        todoItemDAO.persist(todoItem2);
        Collection<TodoItem> beforeDeadlineItems = todoItemDAO.findByDeadlineBefore(LocalDate.of(2024, 11, 16));
        assertEquals(1,beforeDeadlineItems.size());
    }

    @Test
    public void findByDeadlineAfter() {
        TodoItem todoItem1= new TodoItem(0,"Task 1","Change tires", LocalDate.of(2024,11,15),false, creator);
        TodoItem todoItem2= new TodoItem(0,"Task 2","Change back tires", LocalDate.of(2024,11,17),true, creator);
        todoItemDAO.persist(todoItem1);
        todoItemDAO.persist(todoItem2);
        Collection<TodoItem> afterDeadlineItems = todoItemDAO.findByDeadlineAfter(LocalDate.of(2024, 11, 16));
        assertEquals(1, afterDeadlineItems.size());
    }

    @Test
    public void remove() {
        TodoItem todoItem1= new TodoItem(0,"Task 1","Change tires", LocalDate.of(2024,11,15),false, creator);
        todoItemDAO.persist(todoItem1);
        todoItemDAO.remove(todoItem1.getId());
        assertEquals(0, todoItemDAO.findAll().size());


    }
}
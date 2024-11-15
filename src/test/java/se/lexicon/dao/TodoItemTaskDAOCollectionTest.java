package se.lexicon.dao;

import org.junit.Before;
import org.junit.Test;
import se.lexicon.model.Person;
import se.lexicon.model.TodoItem;
import se.lexicon.model.TodoItemTask;
import java.util.Collection;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class TodoItemTaskDAOCollectionTest {
    private TodoItemTaskDAO todoItemTaskDAO;
    private Person assignee;
    private TodoItem todoItem;

    @Before
    public void setUp() {
        todoItemTaskDAO = new TodoItemTaskDAOCollection();
        assignee = new Person(1, "Linus", "Carlsson", "linus.Carlsson@example.com");
        todoItem = new TodoItem(1, "Task 1", "Description 1", LocalDate.of(2024, 11, 15), false, assignee);
    }

    @Test
    public void persist() {
        TodoItemTask todoItemTask = new TodoItemTask(0, todoItem, assignee, true);
        todoItemTaskDAO.persist(todoItemTask);
        assertEquals(1, todoItemTaskDAO.findAll().size());
    }

    @Test
    public void findById() {
        TodoItemTask todoItemTask = new TodoItemTask(0, todoItem, assignee, true);
        todoItemTaskDAO.persist(todoItemTask);
        TodoItemTask foundTask = todoItemTaskDAO.findById(todoItemTask.getId());
        assertEquals(todoItem.getTitle(), foundTask.getTodoItem().getTitle());
    }

    @Test
    public void findAll() {
        TodoItemTask todoItemTask1 = new TodoItemTask(0, todoItem, assignee, true);
        TodoItemTask todoItemTask2 = new TodoItemTask(0, todoItem, assignee, false);
        todoItemTaskDAO.persist(todoItemTask1);
        todoItemTaskDAO.persist(todoItemTask2);
        Collection<TodoItemTask> allTasks = todoItemTaskDAO.findAll(); assertEquals(2, allTasks.size());
    }

    @Test
    public void findByAssignedStatus() {
        TodoItemTask todoItemTask1 = new TodoItemTask(0, todoItem, assignee, true);
        TodoItemTask todoItemTask2 = new TodoItemTask(0, todoItem, assignee, false);
        todoItemTaskDAO.persist(todoItemTask1);
        todoItemTaskDAO.persist(todoItemTask2);
        Collection<TodoItemTask> assignedTask = todoItemTaskDAO.findByAssignedStatus(true);
        assertEquals(1, assignedTask.size());
    }

    @Test
    public void findByPersonId() {
        TodoItemTask todoItemTask1 = new TodoItemTask(0, todoItem, assignee, true);
        TodoItemTask todoItemTask2 = new TodoItemTask(0, todoItem, assignee, false);
        todoItemTaskDAO.persist(todoItemTask1);
        todoItemTaskDAO.persist(todoItemTask2);
        Collection<TodoItemTask> personTask = todoItemTaskDAO.findByPersonId(1);
        assertEquals(2, personTask.size());
    }

    @Test
    public void remove() {
        TodoItemTask todoItemTask = new TodoItemTask(0, todoItem, assignee, true); todoItemTaskDAO.persist(todoItemTask);
        todoItemTaskDAO.remove(todoItemTask.getId());
        assertEquals(0, todoItemTaskDAO.findAll().size());
    }
}
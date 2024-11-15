package se.lexicon;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.lexicon.model.Person;
import se.lexicon.model.TodoItem;
import se.lexicon.model.TodoItemTask;

import java.time.LocalDate;

public class TodoItemTaskTest {

    @Test
    public void testTodoItemTaskCreation() {
        TodoItem todoItem = new TodoItem(1, "Task 1", "Description 1", LocalDate.now(), false, new Person(1, "John", "Doe", "john.doe@example.com"));
        Person assignee = new Person(2, "Jane", "Doo", "jane.doo@example.com");
        TodoItemTask task = new TodoItemTask(1, todoItem, assignee);
        Assertions.assertEquals(1, task.getId());
        Assertions.assertEquals(todoItem, task.getTodoItem());
        Assertions.assertEquals(assignee, task.getAssignee());
        Assertions.assertTrue(task.isAssigned());
    }

    @Test
    public void testSetAssigned() {
        TodoItemTask task = new TodoItemTask(1, new TodoItem(1, "Task 1", "Description 1", LocalDate.now(), false,
                new Person(1, "John", "Doe", "john.doe@example.com")),
                new Person(2, "Jane", "Doe", "jane.doe@example.com"));
        task.setAssigned(false);
        Assertions.assertFalse(task.isAssigned());
    }

    @Test
    public void testSetTodoItem() {
        TodoItemTask task = new TodoItemTask(1, new TodoItem(1, "Task 1", "Description 1", LocalDate.now(), false, new Person(1, "Jane", "Doo", "jane.doo@example.com")), new Person(2, "Jane", "Doe", "jane.doe@example.com"));
        TodoItem newTodoItem = new TodoItem(2, "New Task", "New Description", LocalDate.now(), false, new Person(3, "Alice", "Alison", "alice.alison@example.com"));
        task.setTodoItem(newTodoItem);
        Assertions.assertEquals(newTodoItem, task.getTodoItem());
    }

    @Test
    public void testSetAssignee() {
        TodoItemTask task = new TodoItemTask(1, new TodoItem(1, "Task 1", "Description 1", LocalDate.now(), false, new Person(1, "John", "Doe", "john.doe@example.com")), new Person(2, "Jane", "Doe", "jane.doe@example.com"));
        Person newAssignee = new Person(3, "Alice", "Alison", "alice.alison@example.com");
        task.setAssignee(newAssignee);
        Assertions.assertEquals(newAssignee, task.getAssignee());
        Assertions.assertTrue(task.isAssigned());
    }

    @Test
    public void testToString() {
        TodoItem todoItem = new TodoItem(1, "Task 1", "Description 1", LocalDate.now(), false, new Person(1, "John", "Doe", "john.doe@example.com"));
        Person assignee = new Person(2, "Jane", "Doe", "jane.doe@example.com");
        TodoItemTask task = new TodoItemTask(1, todoItem, assignee);
        String expected = "TodoItemTask{todoItem=" + todoItem + ", assigned=true, id=1}";
        Assertions.assertEquals(expected, task.toString());
    }

    @Test
    public void testEqualsAndHashCode() {
        TodoItem todoItem = new TodoItem(1, "Task 1", "Description 1", LocalDate.now(), false, new Person(1, "John", "Doe", "john.doe@example.com"));
        Person assignee = new Person(2, "Jane", "Doe", "jane.doe@example.com");
        TodoItemTask task1 = new TodoItemTask(1, todoItem, assignee);
        TodoItemTask task2 = new TodoItemTask(1, todoItem, assignee);
        Assertions.assertEquals(task1, task2);
        Assertions.assertEquals(task1.hashCode(), task2.hashCode());
    }
}

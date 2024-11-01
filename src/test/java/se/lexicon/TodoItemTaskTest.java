package se.lexicon;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class TodoItemTaskTest {
    @Test
    public void testTodoItemTaskCreation() {
        Person creator = new Person(4,"Linus", "Carlsson", "Test@test.se");
        TodoItem todoItem = new TodoItem(4, "Change tires", "Change car tires", LocalDate.now().plusDays(1), false, creator);
        TodoItemTask task = new TodoItemTask(4, todoItem, creator);
        Assertions.assertEquals(todoItem, task.getTodoItem());
        Assertions.assertEquals(creator, task.getAssignee());
        Assertions.assertTrue(task.isAssigned());
    }

    @Test
    public void testTodoItemTaskCreationWithNullTodoItem() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Person creator = new Person(4,"Linus", "Carlsson", "Test@test.se");
            new TodoItemTask(4, null, creator);
        })


    ;}
}
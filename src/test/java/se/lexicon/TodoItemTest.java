package se.lexicon;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.lexicon.model.Person;
import se.lexicon.model.TodoItem;

import java.time.LocalDate;

public class TodoItemTest {

    @Test
    public void testTodoItemCreation() {
        Person creator = new Person(4, "Linus", "Carlsson", "Test@test.se");
        TodoItem todoItem = new TodoItem(1, "Change tires", "Change car tires", LocalDate.now().plusDays(1), false, creator);
        Assertions.assertEquals(1, todoItem.getId());
        Assertions.assertEquals("Change tires", todoItem.getTitle());
        Assertions.assertEquals("Change car tires", todoItem.getDescription());
        Assertions.assertEquals(LocalDate.now().plusDays(1), todoItem.getDeadLine());
        Assertions.assertFalse(todoItem.isDone());
        Assertions.assertEquals(creator, todoItem.getCreator());
    }
    @Test
    public void testTodoItemCreationWithNullTitle() {
        Person creator = new Person(4, "Linus", "Carlsson", "Test@test.se");
        Assertions.assertThrows(IllegalArgumentException.class, () -> new TodoItem(1, null, "Change car tires", LocalDate.now().plusDays(1), false, creator));
    }
    @Test
    public void testTodoItemCreationWithNullDeadline() {
        Person creator = new Person(4, "Linus", "Carlsson", "Test@test.se");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new TodoItem(1, "Change tires", "Change car tires", null, false, creator); });
    }
    @Test
    public void testIsOverdue() {
        Person creator = new Person(4, "Linus", "Carlsson", "Test@test.se");
        TodoItem todoItem = new TodoItem(1, "Change tires", "Change car tires", LocalDate.now().minusDays(1), false, creator);
        Assertions.assertTrue(todoItem.isOverdue());
    }
    @Test
    public void testToString(){
        Person creator = new Person(4, "Linus", "Carlsson", "Test@test.se");
        TodoItem todoItem = new TodoItem(1, "Change tires", "Change car tires", LocalDate.now().plusDays(1), false, creator);
        String expectedToString = "TodoItem{done=false, deadLine=" + LocalDate.now().plusDays(1) + ", description='Change car tires', title='Change tires', id=1}";
        Assertions.assertEquals(expectedToString, todoItem.toString());

    }
}


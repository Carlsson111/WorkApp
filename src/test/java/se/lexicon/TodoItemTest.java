package se.lexicon;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class TodoItemTest {
    @Test
    public void testTodoItemCreation() {
        Person creator = new Person(4,"Linus", "Carlsson", "Test@test.se");
        TodoItem todoItem = new TodoItem(4, "Change tires", "Change car tires", LocalDate.now().plusDays(1), false, creator);
        Assertions.assertEquals("Change tires", todoItem.getTitle());
        Assertions.assertEquals("Change car tires", todoItem.getDescription());
        Assertions.assertEquals(creator, todoItem.getCreator());
    }

    @Test
    public void testTodoItemCreationWithNullTitle() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Person creator = new Person(4, "Linus", "Carlsson", "Test@test.se");
            new TodoItem(4, null, "Change car tires", LocalDate.now().plusDays(1), false, creator);

    });
}}


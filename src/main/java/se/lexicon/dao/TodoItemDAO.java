package se.lexicon.dao;

import se.lexicon.model.TodoItem;
import se.lexicon.model.Person;


import java.time.LocalDate;
import java.util.Collection;
;

public interface TodoItemDAO {

    TodoItem create(TodoItem todo);
    Collection<TodoItem> findAll();
    TodoItem findById(int id);
    Collection<TodoItem> findByDoneStatus(boolean done);
    Collection<TodoItem> findByAssignee(int personId);
    Collection<TodoItem> findByAssignee(Person assignee);
    Collection<TodoItem> findByUnassignedTodoItems();
    TodoItem update(TodoItem todo);
    boolean deleteById(int id);


}

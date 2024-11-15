package se.lexicon.dao;

import se.lexicon.model.TodoItem;
import se.lexicon.model.TodoItemTask;

import java.util.Collection;

public interface TodoItemTaskDAO {
    void persist (TodoItemTask todoItemTask);
    TodoItemTask findById(int id);
    Collection<TodoItemTask> findAll();
    Collection<TodoItemTask> findByAssignedStatus(boolean assigned);
    Collection<TodoItemTask> findByPersonId(int personId);
    void remove(int id);


}

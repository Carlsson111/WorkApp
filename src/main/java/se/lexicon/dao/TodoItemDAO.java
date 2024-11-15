package se.lexicon.dao;

import se.lexicon.model.TodoItem;


import java.time.LocalDate;
import java.util.Collection;
;

public interface TodoItemDAO {
    void persist (TodoItem todoItem);
    TodoItem findById(int id);
    Collection<TodoItem> findAll();
    Collection<TodoItem> findAllByDoneStatus(boolean done);
    Collection<TodoItem> findByTitleContains(String title);
    Collection<TodoItem> findByPersonId(int personId);
    Collection<TodoItem>findByDeadlineBefore(LocalDate deadline);
    Collection<TodoItem> findByDeadlineAfter(LocalDate deadline);
    void remove (int id);


}

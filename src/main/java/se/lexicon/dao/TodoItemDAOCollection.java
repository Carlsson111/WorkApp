package se.lexicon.dao;

import se.lexicon.model.TodoItem;
import se.lexicon.sequencers.TodoItemIdSequencer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TodoItemDAOCollection implements TodoItemDAO{
    private List<TodoItem> todoItemList= new ArrayList<>();
    private TodoItemIdSequencer sequencer = TodoItemIdSequencer.getInstance();


    @Override
    public void persist(TodoItem todoItem) {
        if (todoItem.getId() == 0){
            todoItem.setId(sequencer.nextId());
        }


    }

    @Override
    public Collection<TodoItem> findAll() {
        return null;
    }

    @Override
    public Collection<TodoItem> findAllByDoneStatus(boolean done) {
        return List.of();
    }

    @Override
    public Collection<TodoItem> findByTitleContains(String title) {
        return List.of();
    }

    @Override
    public Collection<TodoItem> findByPersonId(int personId) {
        return List.of();
    }

    @Override
    public Collection<TodoItem> findByDeadlineBefore(LocalDate deadline) {
        return List.of();
    }

    @Override
    public Collection<TodoItem> findByDeadlineAfter(LocalDate deadline) {
        return List.of();
    }

    @Override
    public void remove(int id) {

    }
}

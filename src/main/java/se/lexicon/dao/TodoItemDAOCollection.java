package se.lexicon.dao;

import se.lexicon.model.Person;
import se.lexicon.model.TodoItem;

import se.lexicon.sequencers.TodoItemIdSequencer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class TodoItemDAOCollection implements TodoItemDAO{
    private List<TodoItem> todoItemList= new ArrayList<>();


    @Override
    public void persist(TodoItem todoItem) {
        int id = TodoItemIdSequencer.nextId();
        todoItem.setId(id);
        todoItemList.add(todoItem);


    }

    @Override
    public TodoItem findById(int id) {
        for (TodoItem todoItem : todoItemList){
            if (todoItem.getId()==id){
                return todoItem;
            }
        }
        return null;
    }

    @Override
    public Collection<TodoItem> findAll() {
        return new ArrayList<>(todoItemList);
    }

    @Override
    public Collection<TodoItem> findAllByDoneStatus(boolean done) {
        List<TodoItem> doneList = new ArrayList<>();
        for (TodoItem todoItem : todoItemList){
            if (todoItem.isDone()==done){
                doneList.add(todoItem);
            }
        }

        return doneList;
    }

    @Override
    public Collection<TodoItem> findByTitleContains(String title) {
        List<TodoItem> findTitle = new ArrayList<>();
        for (TodoItem todoItem : todoItemList){
            if (todoItem.getTitle().contains(title)){
                findTitle.add(todoItem);
            }
        }
        return findTitle;
    }

    @Override
    public Collection<TodoItem> findByPersonId(int personId) {
        List<TodoItem> findPersonId = new ArrayList<>();
        for (TodoItem todoItem : todoItemList){
            if (todoItem.getCreator().getId()== personId) {
                findPersonId.add(todoItem);
            }

        }
        return findPersonId;
    }

    @Override
    public Collection<TodoItem> findByDeadlineBefore(LocalDate deadline) {
        List<TodoItem> findDeadlineBefore = new ArrayList<>();
        for (TodoItem todoItem : todoItemList){
            if (todoItem.getDeadLine().isBefore(deadline)){
                findDeadlineBefore.add(todoItem);
            }
        }
        return findDeadlineBefore;
    }

    @Override
    public Collection<TodoItem> findByDeadlineAfter(LocalDate deadline) {
            List<TodoItem> findDeadlineAfter = new ArrayList<>();
            for (TodoItem todoItem : todoItemList){
                if (todoItem.getDeadLine().isAfter(deadline)){
                    findDeadlineAfter.add(todoItem);

                }
        }
        return findDeadlineAfter;
    }

    @Override
    public void remove(int id) {
        TodoItem toRemove = null;
        for (TodoItem todoItem : todoItemList){
            if(Objects.equals(todoItem.getId(), id)) toRemove = todoItem;
        }
        if (toRemove != null) todoItemList.remove(toRemove);
    }


}

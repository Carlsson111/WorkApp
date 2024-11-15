package se.lexicon.dao;


import se.lexicon.model.TodoItemTask;

import se.lexicon.sequencers.TodoItemTaskIdSequencer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class TodoItemTaskDAOCollection implements TodoItemTaskDAO{
    private List<TodoItemTask> todoItemTaskList = new ArrayList<>();





    @Override
    public void persist(TodoItemTask todoItemTask) {
        int id = TodoItemTaskIdSequencer.getInstance().nextId();
        todoItemTask.setId(id);
        todoItemTaskList.add(todoItemTask);

        }

    @Override
    public TodoItemTask findById(int id) {
        for (TodoItemTask todoItemTask : todoItemTaskList){
            if (todoItemTask.getId()==id){
                return todoItemTask;
            }
        }
        return null;
    }

    @Override
    public Collection<TodoItemTask> findAll() {
        return new ArrayList<>(todoItemTaskList);
    }

    @Override
    public Collection<TodoItemTask> findByAssignedStatus(boolean assigned) {
        List<TodoItemTask> findByStatus = new ArrayList<>();
        for (TodoItemTask todoItemTask : todoItemTaskList) {
            if (todoItemTask.isAssigned() == assigned) {
                findByStatus.add(todoItemTask);

            }
        }
        return findByStatus;
    }

    @Override
    public Collection<TodoItemTask> findByPersonId(int personId) {
        List<TodoItemTask> findPersonId = new ArrayList<>();
        for (TodoItemTask todoItemTask : todoItemTaskList){
            if (todoItemTask.getAssignee().getId()== personId) {
                findPersonId.add(todoItemTask);
            }

        }
        return findPersonId;
    }


    @Override
    public void remove(int id) {
        TodoItemTask toRemove = null;
        for (TodoItemTask todoItemTask : todoItemTaskList){
            if(Objects.equals(todoItemTask.getId(), id)) toRemove = todoItemTask;
        }
        if (toRemove != null) todoItemTaskList.remove(toRemove);
    }

    

}

package se.lexicon.model;

import java.time.LocalDate;
import java.util.Objects;

public class TodoItem {
    private int id;
    private String title;
    private String description;
    private LocalDate deadLine;
    private boolean done;
    private Person assignee;

    public TodoItem(String title, String description, LocalDate deadLine, boolean done, Person assignee) {
        this.title = title;
        this.description = description;
        this.deadLine = deadLine;
        this.done = done;
        this.assignee = assignee;
    }

    public TodoItem(int id, String title, String description, LocalDate deadLine, boolean done, Person assignee) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        if (deadLine == null) {
            throw new IllegalArgumentException("Deadline cannot be null");
        }
        this.id = id;
        this.title = title;
        this.description = description;
        this.deadLine = deadLine;
        this.done = done;
        this.assignee = assignee;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(LocalDate deadLine) {
        if (deadLine == null) {
            throw new IllegalArgumentException("Deadline cannot be null");
        }
        this.deadLine = deadLine;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Person getAssignee() {
        return assignee;
    }

    public void setAssignee(Person assignee) {
        this.assignee = assignee;
    }

    public boolean isOverdue() {
        return LocalDate.now().isAfter(deadLine);
    }

    @Override
    public String toString() {
        return "TodoItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", deadLine=" + deadLine +
                ", done=" + done +
                ", assignee=" + assignee +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TodoItem todoItem = (TodoItem) o;
        return id == todoItem.id && done == todoItem.done && Objects.equals(title, todoItem.title) && Objects.equals(description, todoItem.description) && Objects.equals(deadLine, todoItem.deadLine) && Objects.equals(assignee, todoItem.assignee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, deadLine, done, assignee);
    }
}


package se.lexicon;

import java.time.LocalDate;

public class TodoItem {
    private int id;
    private String title;
    private String description;
    private LocalDate deadline;
    private boolean done;
    private Person creator;


    public TodoItem(int id, String title, String description, LocalDate deadline, boolean done, Person creator) {
        if (title==null || title.isEmpty()){
            throw new IllegalArgumentException("Title cannot be null or empty ");
        }
        if (deadline==null){
            throw new IllegalArgumentException("Deadline Cannot be null");
        }
        this.id = id;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.done = done;
        this.creator = creator;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title==null || title.isEmpty()){
            throw new IllegalArgumentException("Title cannot be null or empty ");
        }
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        if (deadline==null){
            throw new IllegalArgumentException("Deadline Cannot be null");
        }
        this.deadline = deadline;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
    public boolean isOverdue(){
        return LocalDate.now().isAfter(deadline);

    }
    public String getSummary(){
        return "TodoIteam {id:"+ id + " , title: " + title + ", description: " + description + ", deadLine: " + deadline + ", done: " + done + ", creator: " + creator.getFirstName() + " " + creator.getLastName() + "}";
    }


}

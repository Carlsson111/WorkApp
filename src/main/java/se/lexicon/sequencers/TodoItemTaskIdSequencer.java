package se.lexicon.sequencers;

public class TodoItemTaskIdSequencer {
    private static int currentId;

    private TodoItemTaskIdSequencer(){
    }

    private static class SingletonHelper {
        private static final TodoItemTaskIdSequencer  INSTANCE = new TodoItemTaskIdSequencer();
    }
    public static TodoItemTaskIdSequencer getInstance() {
        return TodoItemTaskIdSequencer.SingletonHelper.INSTANCE;
    }
    public static int nextId() {
        return ++currentId;
    }
    public int getCurrentId(){
        return currentId;
    }
    public void setCurrentId(int id){
        currentId=id;
    }

}



package se.lexicon.sequencers;

public class TodoItemIdSequencer {
    private static int currentId;

    private TodoItemIdSequencer(){
    }

    private static class SingletonHelper {
        private static final TodoItemIdSequencer INSTANCE = new TodoItemIdSequencer();
    }
    public static TodoItemIdSequencer getInstance() {
        return TodoItemIdSequencer.SingletonHelper.INSTANCE;
    }
    public int nextId() {
        return ++currentId;
    }
    public int getCurrentId(){
        return currentId;
    }
    public void setCurrentId(int id){
        currentId=id;
    }

}

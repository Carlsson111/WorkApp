package se.lexicon.sequencers;

public class PersonIdSequencer {
    private static int currentId;

    private PersonIdSequencer(){
    }

    private static class SingletonHelper {
        private static final PersonIdSequencer INSTANCE = new PersonIdSequencer();
    }
    public static PersonIdSequencer getInstance() {
        return SingletonHelper.INSTANCE;
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


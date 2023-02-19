package utility7thsea.singletons;

public class DataTransitSingleton {

    private static DataTransitSingleton instance = null;

    private long editId = -1;
    public DataTransitSingleton() {
    }

    public static DataTransitSingleton getInstance(){
        if(instance == null){
            return new DataTransitSingleton();
        }
        return instance;
    }

    public long getEditId() {
        return editId;
    }

    public void setEditId(long editId) {
        this.editId = editId;
    }
}

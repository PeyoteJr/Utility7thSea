package utility7thsea.singletons;

public class DataTransitSingleton {

    private static DataTransitSingleton instance = null;
    private int editId = -1;
    public DataTransitSingleton() {
    }

    public static DataTransitSingleton getInstance(){
        if(instance == null){
            instance = new DataTransitSingleton();
        }
        return instance;
    }

    public int getEditId() {
        return editId;
    }

    public void setEditId(int editId) {
        this.editId = editId;
    }
}

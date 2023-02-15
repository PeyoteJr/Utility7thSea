package utility7thsea.singletons;

import javafx.collections.ObservableList;
import utility7thsea.model.Character;

import java.util.ArrayList;

public class ListsSingleton {
    private  ObservableList<Character> characters;
    private  ObservableList<Character> presets;
    private ArrayList<Character> abilities;

    private static ListsSingleton instance = null;
    public ListsSingleton() {
    }

    public static ListsSingleton getInstance(){
        if (instance == null) {
            instance = new ListsSingleton();
        }
        return instance;
    }

    public ObservableList<Character> getCharacters() {
        return characters;
    }

    public void setCharacters( ObservableList<Character> characters) {
        this.characters = characters;
    }

    public ObservableList<Character> getPresets() {
        return presets;
    }

    public void setPresets(ObservableList<Character> presets) {
        this.presets = presets;
    }

    public ArrayList<Character> getAbilities() {
        return abilities;
    }

    public void setAbilities(ArrayList<Character> abilities) {
        this.abilities = abilities;
    }
}

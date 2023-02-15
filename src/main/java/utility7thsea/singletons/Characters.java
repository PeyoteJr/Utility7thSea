package utility7thsea.singletons;

import javafx.collections.ObservableList;
import utility7thsea.model.Character;

import java.util.ArrayList;

public class Characters {
    private  ObservableList<Character> characters;

    private static Characters instance = null;
    public Characters() {
    }

    public static Characters getInstance(){
        if (instance == null) {
            instance = new Characters();
        }
        return instance;
    }

    public ObservableList<Character> getCharacters() {
        return characters;
    }

    public void setCharacters( ObservableList<Character> characters) {
        this.characters = characters;
    }
}

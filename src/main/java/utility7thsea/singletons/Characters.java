package utility7thsea.singletons;

import utility7thsea.model.Character;

import java.util.ArrayList;

public class Characters {
    private ArrayList<Character> characters;

    private static Characters instance = null;
    public Characters() {
    }

    public static Characters getInstance(){
        if (instance == null) {
            instance = new Characters();
        }
        return instance;
    }

    public ArrayList<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(ArrayList<Character> characters) {
        this.characters = characters;
    }
}

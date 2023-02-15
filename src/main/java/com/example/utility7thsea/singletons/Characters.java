package com.example.utility7thsea.singletons;

import com.example.utility7thsea.model.Character;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Characters {

    private ObservableList<Character> characters;

    private static Characters instance = null;

    private Characters(){}

    public static Characters getInstance(){
        if(instance == null){
            instance = new Characters();
        }
        return instance;
    }

    public ObservableList<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(ObservableList<Character> characters) {
        this.characters = characters;
    }
}

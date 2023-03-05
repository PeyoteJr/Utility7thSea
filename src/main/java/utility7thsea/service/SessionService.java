package utility7thsea.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utility7thsea.model.Character;
import utility7thsea.singletons.ListsSingleton;

import java.util.ArrayList;

public class SessionService {

    public static void createSession(ObservableList<Character> characters){
        ListsSingleton.getInstance().setCharactersInSession(characters);
    }


}

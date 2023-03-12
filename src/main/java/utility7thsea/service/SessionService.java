package utility7thsea.service;

import javafx.collections.ObservableList;
import utility7thsea.model.Character;
import utility7thsea.singletons.ListsSingleton;


public class SessionService {

    public static void createSession(ObservableList<Character> characters){
        ListsSingleton.getInstance().setCharactersInSession(characters);
    }

    public static void cleanSession() {
        CharacterService.rewriteCharactersFile();
        ListsSingleton.getInstance().getCharactersInSession().removeAll();
    }
}

package utility7thsea.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utility7thsea.model.Character;
import utility7thsea.singletons.ListsSingleton;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CharacterService {

    public static int getAllCharacters() throws URISyntaxException {
        ListsSingleton characters = ListsSingleton.getInstance();
        int status = 200;
        List<Character> characterList;
        URI uri = CharacterService.class.getResource("/data/charactersFile.csv").toURI();
        String mainPath = Paths.get(uri).toString();
        Path path = Paths.get(mainPath);
        try (Stream<String> stream = Files.lines(path)) {
            characterList = stream
                    .map(CharacterService::stringToCharacter)
                    .collect(Collectors.toList());
            characters.setCharacters(FXCollections.observableArrayList(characterList));
        } catch (Exception e) {
            e.printStackTrace();
            status = 500;
        }
        return status;
    }

    public static boolean createCharacter(){

        return true;
    }

    protected static Character stringToCharacter(String inputString) {
        String[] values = inputString.split(";");
        return new Character(Long.parseLong(values[0]), values[1], values[2], values[3], values[4]);
    }

}

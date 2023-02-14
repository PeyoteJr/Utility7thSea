package utility7thsea.service;

import utility7thsea.model.Character;
import utility7thsea.singletons.Characters;

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
        Characters characters = Characters.getInstance();
        int status = 200;
        List<Character> characterList;
        URI uri = CharacterService.class.getResource("/data/charactersFile.csv").toURI();
        String mainPath = Paths.get(uri).toString();
        Path path = Paths.get(mainPath);
        try (Stream<String> stream = Files.lines(path)) {
            characterList = stream
                    .map(CharacterService::stringToCharacter)
                    .collect(Collectors.toList());
            characters.setCharacters((ArrayList<Character>) characterList);
        } catch (Exception e) {
            e.printStackTrace();
            status = 500;
        }
        return status;
    }

    protected static Character stringToCharacter(String inputString) {
        String[] values = inputString.split(";");
        return new Character(Long.parseLong(values[0]), values[1], values[2], values[3].split(","), Boolean.parseBoolean(values[4]));
    }

}

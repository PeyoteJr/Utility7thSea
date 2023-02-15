package com.example.utility7thsea.service;

import javafx.collections.FXCollections;
import com.example.utility7thsea.model.Character;
import com.example.utility7thsea.singletons.Characters;


import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CharacterService {

    public static int getAllCharacters(){
        Characters characters = Characters.getInstance();
        int status = 200;
        List<Character> characterList;
        try (Stream<String> stream = Files.lines(getFilePath())) {
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

    public static void removeCharacter(Long id) {
        Characters characters = Characters.getInstance();
        for (Character c : characters.getCharacters()) {
            if (c.getId() == id) {
                characters.getCharacters().remove(c);
                break;
            }
        }
        writeCharactersToFile();
    }

    private static void writeCharactersToFile() {
        Path path = getFilePath();
        FileOutputStream fos;
        File file = new File(path.toUri());
        try {
            file.delete();
            file.createNewFile();
            fos = new FileOutputStream(file);
        for (Character c : Characters.getInstance().getCharacters()){
            fos.write(c.toCsv().getBytes());
            fos.close();
        }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    private static Character stringToCharacter(String inputString) {
        String[] values = inputString.split(";");
        return new Character(Long.parseLong(values[0]), values[1], values[2], values[3], values[4]);
    }

    private static Path getFilePath() {
        try {
            File file = new File(CharacterService.class.getResource("/data/charactersFile.csv").toURI());
            if (!file.exists()) {
                file.createNewFile();
            }
            return Paths.get(file.getPath());
        } catch (Exception e) {
            return null;
        }

    }

}

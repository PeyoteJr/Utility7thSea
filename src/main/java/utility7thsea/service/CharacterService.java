package utility7thsea.service;

import javafx.collections.FXCollections;
import utility7thsea.model.Character;
import utility7thsea.singletons.DataTransitSingleton;
import utility7thsea.singletons.ListsSingleton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CharacterService {

    public static int getAllCharacters() throws URISyntaxException {
        int status = 200;
        List<Character> characterList;
        URI uri = CharacterService.class.getResource("/data/charactersFile.csv").toURI();
        String mainPath = Paths.get(uri).toString();
        Path path = Paths.get(mainPath);
        try (Stream<String> stream = Files.lines(path)) {
            characterList = stream
                    .map(CharacterService::stringToCharacter)
                    .collect(Collectors.toList());
            ListsSingleton.getInstance().setCharacters(FXCollections.observableArrayList(characterList));
        } catch (Exception e) {
            e.printStackTrace();
            status = 500;
        }
        return status;
    }

    public static void createCharacter(String name, String nation, List<String> fast_reflexes, List<String> duelist,int dramatic, int startingHeroPoints) {
        ListsSingleton.getInstance().getCharacters().sort(Comparator.comparingInt(Character::getId));
        for (Character c: ListsSingleton.getInstance().getCharacters()){
            if(c.getId() == DataTransitSingleton.getInstance().getEditId()){
                ListsSingleton.getInstance().getCharacters().remove(c);
                break;
            }
        }
        Character toAdd = new Character(getFirstFreeId(), name, nation, fast_reflexes, duelist,dramatic,startingHeroPoints);
        ListsSingleton.getInstance().getCharacters().add(toAdd);
        rewriteCharactersFile();
    }

    public static void removeCharacter(int id) {

        ListsSingleton.getInstance().removeCharacterById(id);

        rewriteCharactersFile();
    }

    private static Character stringToCharacter(String inputString) {
        String[] values = inputString.split(";");
                return new Character(Integer.parseInt(values[0]), values[1], values[2],
                List.of(values[3].replaceAll(Pattern.quote("["), "").replaceAll("]", "").split(","))
                , List.of(values[4].replaceAll(Pattern.quote("["), "").replaceAll("]", "").split(","))
                ,Integer.parseInt(values[5]),Integer.parseInt(values[6]));
    }

    private static int getFirstFreeId() {
        if (DataTransitSingleton.getInstance().getEditId() != -1)
            return DataTransitSingleton.getInstance().getEditId();
        int index = 0;
        for (Character c : ListsSingleton.getInstance().getCharacters()) {
            if (index != c.getId()) {
                return index;
            }
            index++;
        }
        return index;
    }

    private static void rewriteCharactersFile(){
        try {
            File file = new File(CharacterService.class.getResource("/data/charactersFile.csv").toURI());
            file.delete();
            file.createNewFile();
            try (FileOutputStream fos = new FileOutputStream(file)) {
                ListsSingleton.getInstance().getCharacters().forEach(character -> {
                    try {
                        fos.write(character.toCsv().getBytes(StandardCharsets.UTF_8));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

package utility7thsea.service;

import javafx.collections.FXCollections;
import utility7thsea.model.Character;
import utility7thsea.singletons.ListsSingleton;

import java.io.FileOutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
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

    public static boolean createCharacter(String name, String nation, String fast_reflexes, String duelist){
        ListsSingleton.getInstance().getCharacters().sort(Comparator.comparingLong(Character::getId));
        Character toAdd = new Character(getFirstFreeId(),name,nation,fast_reflexes,duelist);
        ListsSingleton.getInstance().getCharacters().add(toAdd);
        try {
            URI uri = CharacterService.class.getResource("/data/charactersFile.csv").toURI();
            String mainPath = Paths.get(uri).toString();
            FileOutputStream fos = new FileOutputStream(mainPath, true);
            fos.write(toAdd.toCsv().getBytes());
            fos.close();
            return true;
        }catch(Exception e){
            return false;
        }
    }

    private static Character stringToCharacter(String inputString) {
        String[] values = inputString.split(";");
        if(values.length<5){
            values =  Arrays.copyOf(values,5);
        }
        return new Character(Long.parseLong(values[0]), values[1], values[2], values[3], values[4]);
    }

    private static long getFirstFreeId(){
        long index = 0;
        for(Character c:ListsSingleton.getInstance().getCharacters()){
            if(index!=c.getId()){
                return index;
            }
            index ++;
        }
        return index;
    }

}

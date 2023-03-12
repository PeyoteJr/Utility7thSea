package utility7thsea.model;

import utility7thsea.singletons.ListsSingleton;

import java.util.List;

public class Preset {
    private int id;
    private String name;
    private List<Integer> characterIds;
    private List<String> characterNames;

    public Preset(int id, String name, List<Integer> characterIds) {
        this.id = id;
        this.name = name;
        this.characterIds = characterIds;
        this.characterNames = ListsSingleton.getInstance().getCharacters().stream()
                .filter(character -> characterIds.contains(character.getId())).map(Character::getName).toList();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getCharacterIds() {
        return characterIds;
    }

    public List<String> getCharacterNames() {
        return characterNames;
    }

    public String toCsv() {
        StringBuilder csvLine = new StringBuilder(id + ";" + name + ";");
        for (Integer id:characterIds) {
            csvLine.append(id);
            if(!(characterIds.lastIndexOf(id) == characterIds.size()-1)){
                csvLine.append(",");
            }
        }
        return csvLine.toString();
    }

    @Override
    public String toString() {
        return id +"-"+name;
    }
}

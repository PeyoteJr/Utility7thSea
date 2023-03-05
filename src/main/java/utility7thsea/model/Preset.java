package utility7thsea.model;

import utility7thsea.singletons.ListsSingleton;

import java.util.List;

public class Preset {
    private long id;
    private String name;
    private List<Long> characterIds;
    private List<String> characterNames;

    public Preset(long id, String name, List<Long> characterIds) {
        this.id = id;
        this.name = name;
        this.characterIds = characterIds;
        this.characterNames = ListsSingleton.getInstance().getCharacters().stream()
                .filter(character -> characterIds.contains(character.getId())).map(Character::getName).toList();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getCharacterIds() {
        return characterIds;
    }

    public List<String> getCharacterNames() {
        return characterNames;
    }

    public String toCsv() {
        StringBuilder csvLine = new StringBuilder(id + ";" + name + ";");
        for (Long id:characterIds) {
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

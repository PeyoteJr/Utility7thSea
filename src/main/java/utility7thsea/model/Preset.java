package utility7thsea.model;

import java.util.List;

public class Preset {
    private long id;
    private String name;
    private List<Long> characterIds;

    public Preset(long id, String name, List<Long> characterIds) {

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

    public void setCharacterIds(List<Long> characterIds) {
        this.characterIds = characterIds;
    }

    public String toCsv() {
        return null;
    }
}

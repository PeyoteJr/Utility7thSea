package utility7thsea.model;


import javafx.scene.control.Button;

import java.util.Arrays;
import java.util.List;


public class Character {

    private long id;
    private String name;
    private String nation;
    private List<String> fast_reflexes;
    private List<String> duelist;

    public Character(long id, String name, String nation, List<String> fast_reflexes, List<String> duelist) {
        this.id = id;
        this.name = name;
        this.nation = nation;
        this.fast_reflexes = fast_reflexes;
        this.duelist = duelist;

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

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public List<String> getFast_reflexes() {
        return fast_reflexes;
    }

    public void setFast_reflexes(List<String> fast_reflexes) {
        this.fast_reflexes = fast_reflexes;
    }

    public List<String> getDuelist() {
        return duelist;
    }

    public void setDuelist(List<String> duelist) {
        this.duelist = duelist;
    }

    public String toCsv() {
        String nationToAppend = nation.length() > 0?nation:"";
        StringBuilder csvLine = new StringBuilder(id + ";" + name + ";" + nationToAppend + ";");
        for(String value:fast_reflexes){
            csvLine.append(fast_reflexes);
            if(!(fast_reflexes.lastIndexOf(value) == fast_reflexes.size()-1)){
                csvLine.append(",");
            }
        }
        for(String value:duelist){
            csvLine.append(duelist);
            if(!(duelist.lastIndexOf(value) == duelist.size()-1)){
                csvLine.append(",");
            }
        }

        csvLine.append("\n");
        return csvLine.toString();
    }
}

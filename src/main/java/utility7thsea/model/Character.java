package utility7thsea.model;


import java.util.List;


public class Character {

    private int id;
    private String name;
    private String nation;
    private List<String> fast_reflexes;
    private List<String> duelist;

    private int dramatic = 0;
    private int startingHeroPoints;

    private int currentHeroPoints;

    public Character(int id, String name, String nation, List<String> fast_reflexes, List<String> duelist, int dramatic, int startingHeroPoints) {
        this.id = id;
        this.name = name;
        this.nation = nation;
        this.fast_reflexes = fast_reflexes;
        this.duelist = duelist;
        this.dramatic = dramatic;
        this.startingHeroPoints = startingHeroPoints;
        this.currentHeroPoints = startingHeroPoints;

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

    public String getNation() {
        return nation;
    }

    public List<String> getFast_reflexes() {
        return fast_reflexes;
    }

    public List<String> getDuelist() {
        return duelist;
    }

    public int getDramatic() {
        return dramatic;
    }

    public void setDramatic(int dramatic) {
        this.dramatic = dramatic;
    }

    public int getStartingHeroPoints() {
        return startingHeroPoints;
    }

    public void setStartingHeroPoints(int startingHeroPoints) {
        this.startingHeroPoints = startingHeroPoints;
    }

    public int getCurrentHeroPoints() {
        return currentHeroPoints;
    }

    public void setCurrentHeroPoints(int currentHeroPoints) {
        this.currentHeroPoints = currentHeroPoints;
    }

    public String toCsv() {
        String nationToAppend = nation.length() > 0?nation:"";
        StringBuilder csvLine = new StringBuilder(id + ";" + name + ";" + nationToAppend + ";");
        for(String value:fast_reflexes){
            csvLine.append(value);
            if(!(fast_reflexes.lastIndexOf(value) == fast_reflexes.size()-1)){
                csvLine.append(",");
            }
        }
        csvLine.append(";");
        for(String value:duelist){
            csvLine.append(value);
            if(!(duelist.lastIndexOf(value) == duelist.size()-1)){
                csvLine.append(",");
            }
        }
        csvLine.append(";").append(dramatic).append(";").append(startingHeroPoints);
        csvLine.append("\n");
        return csvLine.toString();
    }

    @Override
    public String toString() {
        return name + " da " + nation;
    }
}

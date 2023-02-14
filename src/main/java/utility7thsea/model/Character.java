package utility7thsea.model;

public class Character {

    private long id;
    private String name;
    private String nation;
    private String[] fast_reflex;
    private boolean duelist;

    public Character(long id, String name, String nation, String[] fast_reflex, boolean duelist) {
        this.id = id;
        this.name = name;
        this.nation = nation;
        this.fast_reflex = fast_reflex;
        this.duelist = duelist;
    }
}

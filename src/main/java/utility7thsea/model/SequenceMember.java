package utility7thsea.model;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Optional;

public class SequenceMember {

    private int id;
    private String name;
    private int increments;
    private Pair<String,String> current_combination;
    private ArrayList<Pair<String,String>> previous_combinations;
    private int dramatic;

    public SequenceMember(Optional<Integer> id, String name, int dramatic) {

        id.ifPresent(aint -> this.id = aint);
        this.name = name;
        this.increments = 0;
        this.current_combination = null;
        this.previous_combinations = null;
        this.dramatic = dramatic;
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

    public int getIncrements() {
        return increments;
    }

    public void setIncrements(int increments) {
        this.increments = increments;
    }

    public Pair<String, String> getCurrent_combination() {
        return current_combination;
    }

    public void setCurrent_combination(Pair<String, String> current_combination) {
        this.current_combination = current_combination;
    }

    public ArrayList<Pair<String, String>> getPrevious_combinations() {
        return previous_combinations;
    }

    public void setPrevious_combinations(ArrayList<Pair<String, String>> previous_combinations) {
        this.previous_combinations = previous_combinations;
    }

    public int getDramatic() {
        return dramatic;
    }

    public void setDramatic(int dramatic) {
        this.dramatic = dramatic;
    }
}

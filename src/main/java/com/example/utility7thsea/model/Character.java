package com.example.utility7thsea.model;


import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import com.example.utility7thsea.service.CharacterService;


public class Character {

    private SimpleLongProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty nation;
    private SimpleStringProperty fast_reflexes;
    private SimpleStringProperty duelist;
    private Button removeButton;

    public Character(long id, String name, String nation, String fast_reflexes, String duelist) {
        this.id = new SimpleLongProperty(id);
        this.name = new SimpleStringProperty(name);
        this.nation = new SimpleStringProperty(nation);
        this.fast_reflexes = new SimpleStringProperty(fast_reflexes);
        this.duelist = new SimpleStringProperty(duelist);
        this.removeButton = new Button("Rimuovi");
        removeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CharacterService.removeCharacter(idProperty().get());
            }

        });
    }

    @Override
    public String toString() {
        return name + " da " + nation + " ------------- Riflessi rapidi su: " + fast_reflexes +" ------- Duellante=" + duelist;
    }

    public String toCsv(){
        return id+";"+name+";"+nation+";"+fast_reflexes+";"+duelist+"\n";
    }

    public long getId() {
        return id.get();
    }

    public SimpleLongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getNation() {
        return nation.get();
    }

    public SimpleStringProperty nationProperty() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation.set(nation);
    }

    public String getFast_reflexes() {
        return fast_reflexes.get();
    }

    public SimpleStringProperty fast_reflexesProperty() {
        return fast_reflexes;
    }

    public void setFast_reflexes(String fast_reflexes) {
        this.fast_reflexes.set(fast_reflexes);
    }

    public String getDuelist() {
        return duelist.get();
    }

    public SimpleStringProperty duelistProperty() {
        return duelist;
    }

    public void setDuelist(String duelist) {
        this.duelist.set(duelist);
    }

    public Button getRemoveButton() {
        return removeButton;
    }

    public void setRemoveButton(Button removeButton) {
        this.removeButton = removeButton;
    }
}

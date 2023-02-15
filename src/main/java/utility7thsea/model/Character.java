package utility7thsea.model;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import utility7thsea.singletons.ListsSingleton;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

public class Character {

    private long id;
    private String name;
    private String nation;
    private String fast_reflexes;
    private String duelist;
    private Button removeButton;

    public Character(long id, String name, String nation, String fast_reflexes, String duelist) {
        this.id = id;
        this.name = name;
        this.nation = nation;
        this.fast_reflexes = fast_reflexes;
        this.duelist = duelist;
        this.removeButton = new Button("Cancella");

        removeButton.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent actionEvent) {
                for(Character c:ListsSingleton.getInstance().getCharacters()) {
                    if (c.getId() == getId()) {
                        ListsSingleton.getInstance().getCharacters().remove(c);
                        break;
                    }
                }
                try {
                    File file = new File(getClass().getResource("/data/charactersFile.csv").toURI());
                    file.delete();
                    file.createNewFile();
                    try (FileOutputStream fos = new FileOutputStream(file)) {
                        for (Character c : ListsSingleton.getInstance().getCharacters()) {
                            fos.write(c.toCsv().getBytes(StandardCharsets.UTF_8));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
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

    public String getFast_reflexes() {
        return fast_reflexes;
    }

    public void setFast_reflexes(String fast_reflexes) {
        this.fast_reflexes = fast_reflexes;
    }

    public String getDuelist() {
        return duelist;
    }

    public void setDuelist(String duelist) {
        this.duelist = duelist;
    }

    public Button getRemoveButton() {
        return removeButton;
    }

    public void setRemoveButton(Button removeButton) {
        this.removeButton = removeButton;
    }


    public String toCsv() {
        return id +";"+ name +";"+ nation +";"+ fast_reflexes +";"+ duelist+"\n";
    }
}

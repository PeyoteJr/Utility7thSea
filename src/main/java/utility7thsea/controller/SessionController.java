package utility7thsea.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Window;
import utility7thsea.service.SessionService;
import utility7thsea.singletons.ListsSingleton;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class SessionController implements Initializable {

    @FXML
    private Button startSequenceButton;

    @FXML
    private Button endSessionButton;

    @FXML
    private VBox characterList;

    @FXML
    protected void onStartSequenceButtonClick() {

    }

    @FXML
    protected void onendSessionButtonClick() throws IOException {
        SessionService.cleanSession();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/utility7thsea/main.fxml")));
        Window window = startSequenceButton.getScene().getWindow();
        window.getScene().setRoot(root);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int characters = ListsSingleton.getInstance().getCharactersInSession().size();
        ListsSingleton.getInstance().getCharactersInSession().stream().forEach(character -> {
            HBox charachterBox = new HBox();
            charachterBox.setSpacing(50);
            charachterBox.setAlignment(Pos.CENTER);
            charachterBox.setPrefWidth(characterList.getPrefWidth());
            charachterBox.setPrefHeight(characterList.getPrefHeight()/characters);

            Label descr = new Label(character.toString());
            descr.setFont(Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 20));
            charachterBox.getChildren().add(descr);

            Label heroLabel = new Label("Punti eroe: ");
            heroLabel.setFont(Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 20));
            charachterBox.getChildren().add(heroLabel);


            TextField heroPoints = new TextField(String.valueOf(character.getStartingHeroPoints()));
            heroPoints.setFont(Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 20));
            charachterBox.getChildren().add(heroPoints);

            Label dramaticLabel = new Label("Ferite drammatiche: ");
            dramaticLabel.setFont(Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 20));
            charachterBox.getChildren().add(dramaticLabel);

            TextField dramatic = new TextField(String.valueOf(character.getDramatic()));
            dramatic.setFont(Font.font("System", FontWeight.NORMAL, FontPosture.REGULAR, 20));
            charachterBox.getChildren().add(dramatic);

            characterList.getChildren().add(charachterBox);
            }
        );
    }
}

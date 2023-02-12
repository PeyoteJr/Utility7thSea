package com.example.utility7thsea.controller;

import com.example.utility7thsea.application.CharacterMain;
import com.example.utility7thsea.application.MainSession;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML
    Button startButton;
    @FXML
    Button characterButton;
    @FXML
    Button exitButton;

    private Stage newStage;

    @FXML
    protected void onStartClick() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("mainSession.fxml"));
        newStage.getScene().setRoot(root);
        newStage.show();
    }

    @FXML
    protected void onCharacterClick() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("com/example/utility7thsea/mainCharacters.fxml"));
        newStage.getScene().setRoot(root);
        newStage.show();
    }

    @FXML
    protected void onExitClick() {
        System.exit(0);
    }

}
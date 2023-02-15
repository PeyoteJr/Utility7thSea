package com.example.utility7thsea.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Window;
import com.example.utility7thsea.model.Character;
import com.example.utility7thsea.service.CharacterService;
import com.example.utility7thsea.singletons.Characters;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class CharacterController implements Initializable {

    @FXML
    private Button backButton;

    @FXML
    private Button createButton;

    @FXML
    private Button presetButton;

    @FXML
    private TableView<Character> charactersList;

    @FXML
    private TableColumn idColumn;
    @FXML
    private TableColumn nameColumn;
    @FXML
    private TableColumn nationColumn;
    @FXML
    private TableColumn fast_reflexesColumn;
    @FXML
    private TableColumn duelistColumn;
    @FXML
    private TableColumn removeColumn;

    Characters characters;
    private Window window;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        characters = Characters.getInstance();

        System.out.println(CharacterService.getAllCharacters());


        idColumn.setCellValueFactory(
                new PropertyValueFactory<Character, Long>("id"));

        nameColumn.setCellValueFactory(
                new PropertyValueFactory<Character, String>("name"));

        nationColumn.setCellValueFactory(
                new PropertyValueFactory<Character, String>("nation"));

        fast_reflexesColumn.setCellValueFactory(
                new PropertyValueFactory<Character, String>("fast_reflexes"));

        duelistColumn.setCellValueFactory(
                new PropertyValueFactory<Character, String>("duelist"));

        removeColumn.setCellValueFactory(
                new PropertyValueFactory<Character, Button>("removeButton"));

        charactersList.setItems(characters.getCharacters());
    }

    @FXML
    protected void onBackButtonClick() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/utility7thsea/main.fxml")));
        window = backButton.getScene().getWindow();
        window.getScene().setRoot(root);
    }

    @FXML
    protected void onCreateButtonClick() {

    }

    @FXML
    protected void onPresetButtonClick() {

    }
}

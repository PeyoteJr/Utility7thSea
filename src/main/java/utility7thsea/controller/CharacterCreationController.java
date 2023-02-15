package utility7thsea.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Window;

import java.io.IOException;
import java.util.Objects;


public class CharacterCreationController {
    @FXML
    private Button backButton;

    @FXML
    private Button createButton;

    @FXML
    private Button resetButton;

    private Window window;


    @FXML
    protected void onBackButtonClick() throws IOException {
        back();
    }
    @FXML
    protected void onCreateButtonClick() throws IOException {
        back();
    }

    @FXML
    protected void onResetButtonClick() throws IOException{

    }

    private void back() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/utility7thsea/mainCharacters.fxml")));
        window = backButton.getScene().getWindow();
        window.getScene().setRoot(root);
    }
}

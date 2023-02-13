package utility7thsea.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    Button startButton;
    @FXML
    Button characterButton;
    @FXML
    Button exitButton;

    private Stage window;

    @FXML
    protected void onStartClick() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainSession.fxml")));
        window.getScene().setRoot(root);
        window.show();
    }

    @FXML
    protected void onCharacterClick() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("mainCharacters.fxml")));
        window.getScene().setRoot(root);
        window.show();
    }

    @FXML
    protected void onExitClick() {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //carica personaggi e preset
    }
}
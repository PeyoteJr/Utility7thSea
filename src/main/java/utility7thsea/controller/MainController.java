package utility7thsea.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Window;

import java.io.IOException;
import java.util.Objects;


public class MainController{

    @FXML
    Button startButton;
    @FXML
    Button characterButton;
    @FXML
    Button exitButton;

    private Window window;

    @FXML
    protected void onStartClick() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/utility7thsea/mainSession.fxml")));
        window = startButton.getScene().getWindow();
        window.getScene().setRoot(root);
    }

    @FXML
    protected void onCharacterClick() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/utility7thsea/mainCharacters.fxml")));
        window = startButton.getScene().getWindow();
        window.getScene().setRoot(root);

    }

    @FXML
    protected void onExitClick() {
        System.exit(0);
    }

}
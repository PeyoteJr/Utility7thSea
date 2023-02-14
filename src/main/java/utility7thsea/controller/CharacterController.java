package utility7thsea.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Window;
import utility7thsea.service.CharacterService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class CharacterController implements Initializable {

    @FXML
    private Button backButton;

    @FXML
    private Button createButton;

    @FXML
    private Button removeButton;

    @FXML
    private Button presetButton;

    @FXML
    private ListView characterList;

    private Window window;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            System.out.println(CharacterService.getAllCharacters());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    @FXML
    protected void onBackButtonClick() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/utility7thsea/main.fxml")));
        window = backButton.getScene().getWindow();
        window.getScene().setRoot(root);
    }
    @FXML
    protected void onCreateButtonClick(){

    }
    @FXML
    protected void onRemoveButtonClick(){

    }
    @FXML
    protected void onPresetButtonClick(){

    }
}

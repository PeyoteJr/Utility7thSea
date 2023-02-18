package utility7thsea.controller;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import utility7thsea.service.CharacterService;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class CharacterCreationController implements Initializable {
    @FXML
    private Button backButton;

    @FXML
    private Button createButton;

    @FXML
    private TextField name;

    @FXML
    private TextField nation;

    @FXML
    private TextField fast_reflexes;

    @FXML
    private TextField duelist;

    private Window window;



    @FXML
    protected void onBackButtonClick() throws IOException {
        back();
    }
    @FXML
    protected void onCreateButtonClick() throws IOException {
        CharacterService.createCharacter(name.getText(),nation.getText(),fast_reflexes.getText(),duelist.getText());
        back();
    }

    @FXML
    protected void onResetButtonClick() throws IOException{
        name.setText("");
        nation.setText("");
        fast_reflexes.setText("");
        duelist.setText("");

    }

    private void back() throws IOException {
        name.setText("");
        nation.setText("");
        fast_reflexes.setText("");
        duelist.setText("");
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/utility7thsea/mainCharacters.fxml")));
        window = backButton.getScene().getWindow();
        window.getScene().setRoot(root);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createButton.disableProperty().bind(
                Bindings.isEmpty(name.textProperty())
                        .and(Bindings.isEmpty(nation.textProperty()))
        );
    }
}

package utility7thsea.controller;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import utility7thsea.service.CharacterService;
import utility7thsea.singletons.ListsSingleton;

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
    private ComboBox nation;

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
        CharacterService.createCharacter(name.getText(),nation.getValue().toString(),fast_reflexes.getText(),duelist.getText());
        back();
    }

    @FXML
    protected void onResetButtonClick() throws IOException{
        name.setText("");
        nation.setValue(null);
        fast_reflexes.setText("");
        duelist.setText("");

    }

    private void back() throws IOException {
        name.setText("");
        nation.setValue(null);
        fast_reflexes.setText("");
        duelist.setText("");
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/utility7thsea/mainCharacters.fxml")));
        window = backButton.getScene().getWindow();
        window.getScene().setRoot(root);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createButton.disableProperty().bind(
                //TODO non funziona correttamente
                Bindings.isEmpty(name.textProperty())
                        .and(Bindings.isNull(nation.valueProperty()))
        );

        nation.setItems(ListsSingleton.getInstance().getNations());
    }
}

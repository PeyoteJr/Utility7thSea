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
import org.controlsfx.control.CheckComboBox;
import utility7thsea.model.Character;
import utility7thsea.service.CharacterService;
import utility7thsea.singletons.DataTransitSingleton;
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
    private ComboBox<String> nation;

    @FXML
    private CheckComboBox<String> fast_reflexes;

    @FXML
    private CheckComboBox<String> duelist;

    private Window window;



    @FXML
    protected void onBackButtonClick() throws IOException {
        back();
    }
    @FXML
    protected void onCreateButtonClick() throws IOException {
        CharacterService.createCharacter(name.getText(),nation.getValue(),fast_reflexes.getCheckModel().getCheckedItems(),duelist.getCheckModel().getCheckedItems());
        back();
    }

    @FXML
    protected void onResetButtonClick() throws IOException{
        name.setText("");
        nation.setValue("");
        fast_reflexes.getCheckModel().clearChecks();
        duelist.getCheckModel().clearChecks();

    }

    private void back() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/utility7thsea/mainCharacters.fxml")));
        window = backButton.getScene().getWindow();
        window.getScene().setRoot(root);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        nation.getItems().addAll(ListsSingleton.getInstance().getNations());
        fast_reflexes.getItems().addAll(ListsSingleton.getInstance().getAbilities());
        duelist.getItems().addAll(ListsSingleton.getInstance().getDuel_styles());
        if(DataTransitSingleton.getInstance().getEditId() != -1){
            Character c = ListsSingleton.getInstance().getCharacters().get(Math.toIntExact((Long) window.getUserData()));
            nation.setValue(c.getNation());
            fast_reflexes.getCheckModel().getCheckedItems().addAll(c.getFast_reflexes());
            duelist.getCheckModel().getCheckedItems().addAll(c.getDuelist());
        }
        createButton.disableProperty().bind(
                Bindings.isEmpty(name.textProperty())
        );

    }
}

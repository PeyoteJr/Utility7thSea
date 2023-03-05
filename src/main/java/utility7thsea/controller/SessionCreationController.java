package utility7thsea.controller;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import utility7thsea.model.Character;
import utility7thsea.model.Preset;
import utility7thsea.service.CharacterService;
import utility7thsea.service.PresetService;
import utility7thsea.singletons.DataTransitSingleton;
import utility7thsea.singletons.ListsSingleton;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class SessionCreationController implements Initializable {

    private ObservableList<Character> availableCharacters = FXCollections.observableArrayList(new ArrayList<>());
    private final ObservableList<Character> inSessionList = FXCollections.observableArrayList(new ArrayList<>());

    @FXML
    private Button backButton;

    @FXML
    private Button createButton;

    @FXML
    private Button moveLeftButton;

    @FXML
    private Button moveRightButton;
    
    @FXML
    private ComboBox<Preset> presetComboBox;

    @FXML
    private TextField name;
    @FXML
    private ListView<Character> available;

    @FXML
    private ListView<Character> inSession;

    @FXML
    protected void onBackButtonClick() throws IOException {
        back();
    }
    @FXML
    protected void onCreateButtonClick() throws IOException {
        List<Long> characterIds = inSession.getItems().stream().map(Character::getId).toList();
        PresetService.createPreset(name.getText(),characterIds);
        back();
    }

    @FXML
    protected void onResetButtonClick() {
        presetComboBox.setValue(null);
        availableCharacters.clear();
        inSessionList.clear();
        availableCharacters.addAll(ListsSingleton.getInstance().getCharacters());
        available.getSelectionModel().clearSelection();
        inSession.getSelectionModel().clearSelection();

    }

    @FXML
    protected void onMoveLeftButtonClick(){
        availableCharacters.addAll(inSession.getSelectionModel().getSelectedItems());
        inSessionList.removeAll(inSession.getSelectionModel().getSelectedItems());
        inSession.getSelectionModel().clearSelection();
    }
    @FXML
    protected void onMoveRightButtonClick(){
        inSessionList.addAll(available.getSelectionModel().getSelectedItems());
        availableCharacters.removeAll(available.getSelectionModel().getSelectedItems());
        available.getSelectionModel().clearSelection();
    }

    private void back() throws IOException {
        DataTransitSingleton.getInstance().setEditId(-1);
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/utility7thsea/main.fxml")));
        Window window = backButton.getScene().getWindow();
        window.getScene().setRoot(root);
    }

    @FXML
    protected void onPresetComboBoxValueChange(){

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            if (ListsSingleton.getInstance().getCharacters() == null) {
                System.out.println(CharacterService.getAllCharacters());
            }
            if(ListsSingleton.getInstance().getPresets() == null){
                System.out.println(PresetService.getAllPresets());
            }

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        availableCharacters.addAll(ListsSingleton.getInstance().getCharacters());
        available.setItems(availableCharacters);
        inSession.setItems(inSessionList);
        presetComboBox.setItems(ListsSingleton.getInstance().getPresets());
        createButton.disableProperty().bind(Bindings.isEmpty(inSessionList));
    }
}

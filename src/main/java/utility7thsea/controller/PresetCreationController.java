package utility7thsea.controller;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import utility7thsea.model.Character;
import utility7thsea.model.Preset;
import utility7thsea.service.PresetService;
import utility7thsea.singletons.DataTransitSingleton;
import utility7thsea.singletons.ListsSingleton;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class PresetCreationController implements Initializable {

    private ObservableList<Character> availableCharacters = FXCollections.observableArrayList(new ArrayList<>());
    private final ObservableList<Character> inPresetList = FXCollections.observableArrayList(new ArrayList<>());

    @FXML
    private Button backButton;

    @FXML
    private Button createButton;

    @FXML
    private Button moveLeftButton;

    @FXML
    private Button moveRightButton;

    @FXML
    private TextField name;
    @FXML
    private ListView<Character> available;

    @FXML
    private ListView<Character> inPreset;


    @FXML
    protected void onBackButtonClick() throws IOException {
        back();
    }
    @FXML
    protected void onCreateButtonClick() throws IOException {
        List<Long> characterIds = inPreset.getItems().stream().map(Character::getId).toList();
        PresetService.createPreset(name.getText(),characterIds);
        back();
    }

    @FXML
    protected void onResetButtonClick() {
        name.setText("");
        availableCharacters.clear();
        inPresetList.clear();
        availableCharacters.addAll(ListsSingleton.getInstance().getCharacters());
        available.getSelectionModel().clearSelection();
        inPreset.getSelectionModel().clearSelection();

    }

    @FXML
    protected void onMoveLeftButtonClick(){
        availableCharacters.addAll(inPreset.getSelectionModel().getSelectedItems());
        inPresetList.removeAll(inPreset.getSelectionModel().getSelectedItems());
        inPreset.getSelectionModel().clearSelection();
    }
    @FXML
    protected void onMoveRightButtonClick(){
        inPresetList.addAll(available.getSelectionModel().getSelectedItems());
        availableCharacters.removeAll(available.getSelectionModel().getSelectedItems());
        available.getSelectionModel().clearSelection();
    }

    private void back() throws IOException {
        DataTransitSingleton.getInstance().setEditId(-1);
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/utility7thsea/mainPreset.fxml")));
        Window window = backButton.getScene().getWindow();
        window.getScene().setRoot(root);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        availableCharacters.addAll(ListsSingleton.getInstance().getCharacters());
        available.setItems(availableCharacters);
        inPreset.setItems(inPresetList);
        if(DataTransitSingleton.getInstance().getEditId() != -1){
            Preset p = ListsSingleton.getInstance().getPresets().get(Math.toIntExact((DataTransitSingleton.getInstance().getEditId())));
            name.setText(p.getName());
            inPresetList.addAll(FXCollections.observableArrayList(ListsSingleton.getInstance().getCharacters().stream()
                    .filter(character -> p.getCharacterIds().contains(character.getId())).toList()));
            availableCharacters.removeAll(inPresetList);
        }
        createButton.disableProperty().bind(
                Bindings.isEmpty(name.textProperty()).or(Bindings.isEmpty(inPreset.getItems()))
        );

    }

}

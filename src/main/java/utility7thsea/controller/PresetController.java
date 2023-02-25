package utility7thsea.controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Window;
import utility7thsea.model.Character;
import utility7thsea.model.Preset;
import utility7thsea.service.PresetService;
import utility7thsea.singletons.DataTransitSingleton;
import utility7thsea.singletons.ListsSingleton;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class PresetController implements Initializable {

    @FXML
    private Button backButton;

    @FXML
    private Button createButton;

    @FXML
    private Button presetButton;
    @FXML
    private Button editButton;

    @FXML
    private TableView<Preset> presetTable;
    @FXML
    private TableColumn<Preset, String> nameColumn;
    @FXML
    private TableColumn<Preset, String> charactersColumn;

    @FXML
    private TableColumn<Preset, Preset> operationColumn;

    private Window window;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            if (ListsSingleton.getInstance().getPresets() == null) {
                System.out.println(PresetService.getAllPresets());
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        charactersColumn.setCellValueFactory(new PropertyValueFactory<>("nation"));

        operationColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));

        operationColumn.setCellFactory(column -> new TableCell<>() {

            private final Button editButton = new Button("Modifica");
            private final Button removeButton = new Button("Cancella");

            protected void updateItem(Preset Preset, boolean empty) {
                super.updateItem(Preset, empty);

                if (Preset == null) {
                    setGraphic(null);
                } else {
                    removeButton.setOnAction(actionEvent -> PresetService.removePreset(Preset.getId()));

                    editButton.setOnAction(actionEvent -> {
                        DataTransitSingleton.getInstance().setEditId(Preset.getId());
                        try {
                            onCreateButtonClick();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });

                    HBox pane = new HBox(editButton, removeButton);
                    setGraphic(pane);
                }
            }

        });

        presetTable.setItems(ListsSingleton.getInstance().getPresets());
    }

    @FXML
    protected void onBackButtonClick() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/utility7thsea/mainCharacters.fxml")));
        window = backButton.getScene().getWindow();
        window.getScene().setRoot(root);
    }

    @FXML
    protected void onCreateButtonClick() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/utility7thsea/addPreset.fxml")));
        window = backButton.getScene().getWindow();
        window.getScene().setRoot(root);
    }

    @FXML
    protected void onEditButtonClick() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/utility7thsea/addPreset.fxml")));
        window = backButton.getScene().getWindow();
        window.getScene().setRoot(root);
    }
}

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
import utility7thsea.service.CharacterService;
import utility7thsea.singletons.DataTransitSingleton;
import utility7thsea.singletons.ListsSingleton;

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
    private Button presetButton;
    @FXML
    private Button editButton;

    @FXML
    private TableView<Character> characterTable;
    @FXML
    private TableColumn<Character, String> nameColumn;
    @FXML
    private TableColumn<Character, String> nationColumn;
    @FXML
    private TableColumn<Character, String> fastReflexesColumn;
    @FXML
    private TableColumn<Character, String> duelistColumn;
    @FXML
    private TableColumn<Character, Character> operationColumn;

    private Window window;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            if (ListsSingleton.getInstance().getCharacters() == null) {
                System.out.println(CharacterService.getAllCharacters());
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nationColumn.setCellValueFactory(new PropertyValueFactory<>("nation"));
        fastReflexesColumn.setCellValueFactory(new PropertyValueFactory<>("fast_reflexes"));
        duelistColumn.setCellValueFactory(new PropertyValueFactory<>("duelist"));

        operationColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue()));

        operationColumn.setCellFactory(column -> new TableCell<>() {

            private final Button editButton = new Button("Modifica");
            private final Button removeButton = new Button("Cancella");

            protected void updateItem(Character character, boolean empty) {
                super.updateItem(character, empty);

                if (character == null) {
                    setGraphic(null);
                } else {
                    removeButton.setOnAction(actionEvent -> CharacterService.removeCharacter(character.getId()));

                    editButton.setOnAction(actionEvent -> {
                        DataTransitSingleton.getInstance().setEditId(character.getId());
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

        characterTable.setItems(ListsSingleton.getInstance().getCharacters());
    }

    @FXML
    protected void onBackButtonClick() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/utility7thsea/main.fxml")));
        window = backButton.getScene().getWindow();
        window.getScene().setRoot(root);
    }

    @FXML
    protected void onCreateButtonClick() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/utility7thsea/addCharacter.fxml")));
        window = backButton.getScene().getWindow();
        window.getScene().setRoot(root);
    }

    @FXML
    protected void onPresetButtonClick() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/utility7thsea/mainPreset.fxml")));
        window = backButton.getScene().getWindow();
        window.getScene().setRoot(root);
    }
}

package utility7thsea.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Window;
import utility7thsea.model.Character;
import utility7thsea.service.CharacterService;
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
    private TableView characterTable;
    @FXML
    private TableColumn nameColumn;
    @FXML
    private TableColumn nationColumn;
    @FXML
    private TableColumn fastReflexesColumn;
    @FXML
    private TableColumn duelistColumn;
    @FXML
    private TableColumn removeColumn;

    private Window window;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            if(ListsSingleton.getInstance().getCharacters()==null) {
                System.out.println(CharacterService.getAllCharacters());
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        nameColumn.setCellValueFactory(new PropertyValueFactory<Character,String>("name"));
        nationColumn.setCellValueFactory(new PropertyValueFactory<Character,String>("nation"));
        fastReflexesColumn.setCellValueFactory(new PropertyValueFactory<Character,String>("fast_reflexes"));
        duelistColumn.setCellValueFactory(new PropertyValueFactory<Character,String>("duelist"));
        removeColumn.setCellValueFactory(new PropertyValueFactory<Character,Button>("removeButton"));

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
    protected void onPresetButtonClick() throws IOException{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/utility7thsea/mainPreset.fxml")));
        window = backButton.getScene().getWindow();
        window.getScene().setRoot(root);
    }
}

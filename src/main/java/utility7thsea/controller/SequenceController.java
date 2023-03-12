package utility7thsea.controller;

import javafx.fxml.Initializable;
import utility7thsea.mapper.CharacterToSequenceMemberMapper;
import utility7thsea.singletons.ListsSingleton;

import java.net.URL;
import java.util.ResourceBundle;

public class SequenceController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ListsSingleton.getInstance().getCharactersInSession().forEach(
                character -> ListsSingleton.getInstance().getSequenceMembers().add(CharacterToSequenceMemberMapper.map(character))
        );
    }
}

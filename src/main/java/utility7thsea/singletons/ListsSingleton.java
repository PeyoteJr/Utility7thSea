package utility7thsea.singletons;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utility7thsea.model.Character;
import utility7thsea.model.Preset;
import utility7thsea.model.SequenceMember;

import java.util.ArrayList;
import java.util.Arrays;

public class ListsSingleton {
    private  ObservableList<Character> characters;
    private  ObservableList<Preset> presets;
    private ObservableList<Character> charactersInSession;
    private ObservableList<SequenceMember> sequenceMembers;
    private ArrayList<String> abilities;
    private ArrayList<String> traits;
    private ArrayList<String> duel_styles;
    private ObservableList<String> nations;

    private static ListsSingleton instance = null;
    public ListsSingleton() {
        abilities = new ArrayList<>(Arrays.asList("ALLETTARE","ARTE DELLA GUERRA","ATLETICA","CAVALCARE","CONVINCERE","EMPATIA","ESIBIRSI","FURTO","INTIMIDIRE","ISTRUZIONE","MIRA","MISCHIA","NASCONDERSI","NAVIGAZIONE","NOTARE","RISSA"));
        traits = new ArrayList<>(Arrays.asList("VIGORE","GRAZIA","RISOLUTEZZA","ACUME","PANACHE"));
        nations = FXCollections.observableArrayList(new ArrayList<>(Arrays.asList("Avalon","Inismore","Marche delle Highlands","Vestenmennavenjar","Eisen","Castille","Montaigne","Vodacce","Confederazione Sarmatiana","Ussura")));
        duel_styles = new ArrayList<>(Arrays.asList("Aldana","Ambrogia","Boucher","Cleasa di Skatha","De Vore","Donovan","Drexel","Eisenfaust","Hallbjorn","Kulachniy Boi","Kummerholt","Lakedaimon Agoge","Leegstra","Mantovani","Mireli","Sabat","Siqueira","Le Strade","Szybowanie","Torres","Valroux"));
    }

    public static ListsSingleton getInstance(){
        if (instance == null) {
            instance = new ListsSingleton();
        }
        return instance;
    }

    public void removeCharacterById(int id){
        for (Character c:this.getCharacters()) {
            if(c.getId() == id){
                this.characters.remove(c);
                return;
            }
        }
    }
    public void removePresetById(int id){
        for (Preset p:this.getPresets()) {
            if(p.getId() == id){
                this.presets.remove(p);
                return;
            }
        }
    }

    public ObservableList<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(ObservableList<Character> characters) {
        this.characters = characters;
    }

    public ObservableList<Preset> getPresets() {
        return presets;
    }

    public void setPresets(ObservableList<Preset> presets) {
        this.presets = presets;
    }

    public ArrayList<String> getAbilities() {
        return abilities;
    }

    public ArrayList<String> getTraits() {
        return traits;
    }

    public ArrayList<String> getDuel_styles() {
        return duel_styles;
    }

    public ObservableList<String> getNations() {
        return nations;
    }

    public ObservableList<Character> getCharactersInSession() {
        return charactersInSession;
    }

    public void setCharactersInSession(ObservableList<Character> charactersInSession) {
        this.charactersInSession = charactersInSession;
    }

    public ObservableList<SequenceMember> getSequenceMembers() {
        return sequenceMembers;
    }

    public void setSequenceMembers(ObservableList<SequenceMember> sequenceMembers) {
        this.sequenceMembers = sequenceMembers;
    }
}

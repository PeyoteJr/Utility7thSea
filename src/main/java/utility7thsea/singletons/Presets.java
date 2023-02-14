package utility7thsea.singletons;

import utility7thsea.model.Preset;

import java.util.ArrayList;

public class Presets {
    private ArrayList<Preset> Presets;

    private static Presets instance = null;
    public Presets() {
    }

    public static Presets getInstance(){
        if (instance == null) {
            instance = new Presets();
        }
        return instance;
    }

    public ArrayList<Preset> getPresets() {
        return Presets;
    }

    public void setPresets(ArrayList<Preset> Presets) {
        this.Presets = Presets;
    }
}

package utility7thsea.service;

import javafx.collections.FXCollections;
import utility7thsea.model.Preset;
import utility7thsea.model.Preset;
import utility7thsea.singletons.DataTransitSingleton;
import utility7thsea.singletons.ListsSingleton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PresetService {
    public static void createPreset(String name, List<Integer> presetIds){
        ListsSingleton.getInstance().getPresets().sort(Comparator.comparingInt(Preset::getId));
        Preset toAdd = new Preset(getFirstFreeId(), name, presetIds);
        for (Preset p: ListsSingleton.getInstance().getPresets()){
            if(p.getId() == DataTransitSingleton.getInstance().getEditId()){
                ListsSingleton.getInstance().getPresets().remove(p);
                break;
            }
        }
        ListsSingleton.getInstance().getPresets().add(toAdd);

        rewritePresetFile();

    }

    private static int getFirstFreeId() {
        if(DataTransitSingleton.getInstance().getEditId() != -1)
            return DataTransitSingleton.getInstance().getEditId();
        int index = 0;
        for (Preset p : ListsSingleton.getInstance().getPresets()) {
            if (index != p.getId()) {
                return index;
            }
            index++;
        }
        return index;
    }


    public static void removePreset(int id) {

        ListsSingleton.getInstance().removePresetById(id);
        rewritePresetFile();

    }

    public static int getAllPresets() throws URISyntaxException {
        int status = 200;
        List<Preset> presetList;
        URI uri = Objects.requireNonNull(PresetService.class.getResource("/data/presetsFile.csv")).toURI();
        String mainPath = Paths.get(uri).toString();
        Path path = Paths.get(mainPath);
        try (Stream<String> stream = Files.lines(path)) {
            presetList = stream
                    .map(PresetService::stringToPreset)
                    .collect(Collectors.toList());
            ListsSingleton.getInstance().setPresets(FXCollections.observableArrayList(presetList));
        } catch (Exception e) {
            e.printStackTrace();
            status = 500;
        }
        return status;

    }

    private static Preset stringToPreset(String s) {
        String[] values = s.split(";");
        List<String> stringIds = List.of(values[2].replaceAll(Pattern.quote("["), "").replaceAll("]", "").split(","));
        return new Preset(Integer.parseInt(values[0]), values[1], stringIds.stream().map(Integer::parseInt).toList());
    }

    private static void rewritePresetFile(){
        try {
            File file = new File(Objects.requireNonNull(PresetService.class.getResource("/data/presetsFile.csv")).toURI());
            file.delete();
            file.createNewFile();
            try (FileOutputStream fos = new FileOutputStream(file)) {
                ListsSingleton.getInstance().getPresets().forEach(Preset -> {
                    try {
                        fos.write(Preset.toCsv().getBytes(StandardCharsets.UTF_8));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

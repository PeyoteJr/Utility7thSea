package utility7thsea.service;

import javafx.collections.FXCollections;
import utility7thsea.model.Preset;
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
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PresetService {
    public static boolean createPreset(String name, List<Long> presetIds){
        ListsSingleton.getInstance().getPresets().sort(Comparator.comparingLong(Preset::getId));
        Preset toAdd = new Preset(getFirstFreeId(),name,presetIds);
        ListsSingleton.getInstance().getPresets().add(toAdd);
        try {
            URI uri = PresetService.class.getResource("/data/PresetsFile.csv").toURI();
            String mainPath = Paths.get(uri).toString();
            FileOutputStream fos = new FileOutputStream(mainPath, true);
            fos.write(toAdd.toCsv().getBytes());
            fos.close();
            return true;
        }catch(Exception e){
            return false;
        }
    }

    private static long getFirstFreeId(){
        long index = 0;
        for(Preset p:ListsSingleton.getInstance().getPresets()){
            if(index!=p.getId()){
                return index;
            }
            index ++;
        }
        return index;
    }


    public static void removePreset(long id){

        ListsSingleton.getInstance().removePresetById(id);

        try {
            File file = new File(PresetService.class.getResource("/data/PresetsFile.csv").toURI());
            file.delete();
            file.createNewFile();
            try (FileOutputStream fos = new FileOutputStream(file)) {
                ListsSingleton.getInstance().getPresets().forEach(Preset-> {
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

    public static int getAllPresets() throws URISyntaxException {
        int status = 200;
        List<Preset> presetList;
        URI uri = PresetService.class.getResource("/data/presetsFile.csv").toURI();
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
        List<String> stringIds = List.of(values[2].replaceAll(Pattern.quote("["),"").replaceAll("]","").split(","));
        return new Preset(Long.parseLong(values[0]), values[1], stringIds.stream().map(Long::parseLong).toList());
    }
}

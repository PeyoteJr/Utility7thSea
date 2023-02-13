package utility7thsea.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class MainSession extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        try {
            System.out.println("MM");
            Scene scene = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("com/example/utility7thsea/mainSession.fxml"))), 1280, 720);
            stage.setTitle("7thSeaApp");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}

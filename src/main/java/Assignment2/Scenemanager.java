package Assignment2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Scenemanager extends Application {
    Parent root;
    Stage mainstage = new Stage();
    @Override
    public void start(Stage stage) {
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Login.fxml")));
            Scene scene = new Scene(root);
            mainstage.setTitle("MyTimetable - Login");
            mainstage.setScene(scene);
            mainstage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

package pl.zdrov;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.zdrov.utilies.FxmlUtilies;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setScene(new Scene(FxmlUtilies.setFxml("/fxml/Main.fxml")));
        primaryStage.show();

    }
}

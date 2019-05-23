package pl.zdrov;

import javafx.application.Application;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import pl.zdrov.controllers.BackgroundController;
import pl.zdrov.database.DbConnector;
import pl.zdrov.utilies.FxmlUtilies;
import pl.zdrov.utilies.Utils;

import java.time.LocalDate;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public static  void newWindow(Parent parent,String title)
    {
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        scene.getStylesheets().addAll(FxmlUtilies.class.getResource(Path.CSS_PATH).toExternalForm());
        stage.setScene(scene);
        stage.setTitle(title);
        if(stage.getTitle().equals("Okno rejestracji"))
        {
            stage.setOnCloseRequest((WindowEvent event1) -> {
                BackgroundController.setRegistrationController(null);
            });
        }
        stage.show();
    }

    @Override
    public void start(Stage primaryStage) {


        primaryStage.setScene(new Scene(FxmlUtilies.setFxmlParent("/fxml/Main.fxml")));
        primaryStage.setMaximized(true);
        primaryStage.show();

        DbConnector.initDatabase();
    }
}

package pl.zdrov;

import javafx.application.Application;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import pl.zdrov.controllers.BackgroundController;
import pl.zdrov.database.DbConnector;
import pl.zdrov.utilies.FxmlUtilies;

/**
 * Główna klasa programu
 */
public class Main extends Application {

    /**
     * Metoda rozpoczynająca działanie programu
     * @param args dane wejściowe dla programu
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Tworzenie sceny w nowym oknie
     * @param parent nowa scena
     * @param title nazwa sceny
     */
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

    /**
     * Metoda wyświetlająca główne okno programu
     * @param primaryStage główne okno programu
     */
    @Override
    public void start(Stage primaryStage) {


        primaryStage.setScene(new Scene(FxmlUtilies.setFxmlParent("/fxml/Main.fxml")));
        primaryStage.setMaximized(true);
        primaryStage.show();
        primaryStage.setTitle("Przychodnia");

        DbConnector.initDatabase();
    }
}

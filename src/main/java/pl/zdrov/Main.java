package pl.zdrov;

import javafx.application.Application;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.zdrov.database.DbConnector;
import pl.zdrov.utilies.FxmlUtilies;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public static  void newWindow(Parent parent)
    {
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
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

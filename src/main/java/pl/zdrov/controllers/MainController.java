package pl.zdrov.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pl.zdrov.utilies.FxmlUtilies;

public class MainController {
    @FXML
    public AnchorPane anchorPane;

    @FXML
    public void initialize()
    {

    }

    @FXML
    public void itemShowDoctors()
    {
        setCenter("/fxml/ShowDoctors.fxml");
    }
    @FXML

    public void itemAddDoctor() {
        Stage stage = new Stage();
        stage.setScene(new Scene(FxmlUtilies.setFxml("/fxml/AddDoctor.fxml")));
        stage.setResizable(false);
        stage.setAlwaysOnTop(true);
        stage.setTitle("Wprowadz dane dla nowego lekarza");
        stage.showAndWait();
    }

    public void setCenter(String fxmlPath)
    {
        anchorPane.getChildren().addAll(FxmlUtilies.setFxml(fxmlPath));
    }
}

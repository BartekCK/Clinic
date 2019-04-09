package pl.zdrov.controllers;


import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import pl.zdrov.utilies.FxmlUtilies;

public class MainController {

    @FXML
    private BackgroundController backController;

    @FXML
    private FooterController downController;

    @FXML
    private StackPane stackPane;

    @FXML
    public void itemShowDoctors()
    {
        setCenter("/fxml/ShowDoctors.fxml");
    }

    @FXML
    public void itemAddDoctor()
    {
        setCenter("/fxml/AddDoctor.fxml");
    }

    @FXML
    public void initialize()
    {
        backController.setMainController(this);
    }

    public void setCenter(String fxmlPath)
    {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(FxmlUtilies.setFxml(fxmlPath));
    }

    public void cleanWindow()
    {
        stackPane.getChildren().clear();
    }
}

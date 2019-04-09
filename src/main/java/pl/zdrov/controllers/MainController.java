package pl.zdrov.controllers;


import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import pl.zdrov.utilies.FxmlUtilies;

public class MainController {

    @FXML
    private FooterController downController;

    @FXML
    private StackPane stackPane;

    private static MainController mainController;

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

    public MainController() {
        mainController=this;
    }

    public void setCenter(String fxmlPath)
    {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(FxmlUtilies.setFxml(fxmlPath));
    }

    public static MainController getMainController() {
        return mainController;
    }

    public void cleanWindow()
    {
        stackPane.getChildren().clear();
    }
}

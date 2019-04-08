package pl.zdrov.controllers;


import javafx.fxml.FXML;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import pl.zdrov.utilies.FxmlUtilies;

public class MainController {

    @FXML
    public FooterController downController;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    public void initialize()
    {
        downController.setLabel();
    }

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

    public void setCenter(String fxmlPath)
    {
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(FxmlUtilies.setFxml(fxmlPath));
    }
}

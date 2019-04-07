package pl.zdrov.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import pl.zdrov.utilies.FxmlUtilies;

public class MainController {
    @FXML
    public AnchorPane anchorPane;

    @FXML
    public void itemShowDoctors()
    {
        setCenter("/fxml/ShowDoctors.fxml");
    }

    @FXML
    public void initialize()
    {

    }
    
    public void setCenter(String fxmlPath)
    {
        anchorPane.getChildren().addAll(FxmlUtilies.setFxml(fxmlPath));
    }


}

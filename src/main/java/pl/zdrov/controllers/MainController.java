package pl.zdrov.controllers;


import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import pl.zdrov.Path;
import pl.zdrov.utilies.DialogCatch;
import pl.zdrov.utilies.FxmlUtilies;

public class MainController {

    @FXML
    private BackgroundController backController;

    @FXML
    private FooterController downController;

    @FXML
    private StackPane stackPane;



    @FXML
    public void initialize()
    {
        backController.setMainController(this);
    }

    public void setCenter(String fxmlPath)
    {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(FxmlUtilies.setFxmlParent(fxmlPath));
    }
    public void setCenterParent(Parent parent)
    {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(parent);
    }


    public void cleanWindow()
    {
        stackPane.getChildren().clear();
    }

    @FXML
    public void itemShowDoctors()
    {
        setCenter(Path.SHOW_DOCTOR_PATH);
    }

    @FXML
    public void itemAddDoctor()
    {
        setCenter(Path.ADD_DOCTOR_PATH);
    }

    @FXML
    public void itemAddPatient() {
        setCenter(Path.ADD_PATIENT_PATH);
    }

    @FXML
    public void itemShowPatient() {
        setCenter(Path.SHOW_PATIENT_PATH);
    }

    public void projectInformation() {
        DialogCatch.projectInf();
    }

    public void projectClose() {
        System.exit(0);
    }
}

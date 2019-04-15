package pl.zdrov.controllers;


import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class FooterController {

    @FXML
    public Label countDoctorLabel;
    @FXML
    public Label countPatientLabel;
    @FXML
    public Label dateLabel;
    @FXML
    public Label hoursLabel;

    @FXML
    public void showBackGround() {
        BackgroundController.getMainController().cleanWindow();
    }
}

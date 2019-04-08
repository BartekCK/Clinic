package pl.zdrov.controllers;

import javafx.beans.binding.When;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import pl.zdrov.Main;
import pl.zdrov.database.models.Specialization;
import pl.zdrov.database.modelsFX.SpecializationFx;

public class AddDoctorController {

    MainController mainController;

    @FXML
    private Button saveButton;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField surnameTextField;

    @FXML
    private TextField peselTextField;

    @FXML
    private TextField pwzTextField;

    @FXML
    private TextField mailTextField;

    @FXML
    private TextField phoneTextField;

    @FXML
    private ComboBox<Specialization> specializationComboBox;

    @FXML
    private void initialize()
    {
        saveButton.disableProperty().bind(nameTextField.textProperty().isEmpty());
        setMainController(MainController.getMainController());
        specializationComboBox.setItems(SpecializationFx.returnAllSpecialization());
    }

    @FXML
    private void commitDoctor() {
        mainController.setCenter("/fxml/AddWorkHours.fxml");
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}

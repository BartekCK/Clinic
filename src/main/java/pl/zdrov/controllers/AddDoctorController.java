package pl.zdrov.controllers;

import javafx.beans.binding.When;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddDoctorController {

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
    private TextField specializationTextField;

    @FXML
    private void initialize()
    {
        saveButton.disableProperty().bind(nameTextField.textProperty().isEmpty());
        saveButton.disableProperty().bind(surnameTextField.textProperty().isEmpty());
        saveButton.disableProperty().bind(peselTextField.textProperty().isEmpty());
        saveButton.disableProperty().bind(pwzTextField.textProperty().isEmpty());
        saveButton.disableProperty().bind(pwzTextField.textProperty().isEmpty());
        saveButton.disableProperty().bind(mailTextField.textProperty().isEmpty());
        saveButton.disableProperty().bind(phoneTextField.textProperty().isEmpty());
        saveButton.disableProperty().bind(specializationTextField.textProperty().isEmpty());

    }

    @FXML
    private void commitDoctor() {
    }
}

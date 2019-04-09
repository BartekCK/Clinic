package pl.zdrov.controllers;

import javafx.beans.binding.When;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import pl.zdrov.Main;
import pl.zdrov.database.models.Doctor;
import pl.zdrov.database.models.Specialization;
import pl.zdrov.database.modelsFX.DoctorModel;
import pl.zdrov.database.modelsFX.SpecializationFx;
import pl.zdrov.utilies.DialogCatch;
import pl.zdrov.utilies.exceptions.CorrectDataCommit;

public class AddDoctorController {

    private MainController mainController;

    private Doctor doctor;

    private DoctorModel doctorModel;

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
        hoverButtonToCommit();
        doctorModel = new DoctorModel();
        setMainController(MainController.getMainController());
        specializationComboBox.setItems(SpecializationFx.returnAllSpecialization());
    }

    @FXML
    private void commitDoctor() {
        try {
            doctor = new Doctor(nameTextField.getText(),surnameTextField.getText(),peselTextField.getText(),mailTextField.getText(),phoneTextField.getText(),
                    pwzTextField.getText(),specializationComboBox.getValue());
            //CorrectDataCommit.checkDoctor(doctor);
            doctorModel.saveDoctorInDataBase(doctor);
            mainController.setCenter("/fxml/AddWorkHours.fxml");
        }catch (Exception e)
        {
            DialogCatch.errorCommitDoctor(e.getMessage());
        }
    }

    private void hoverButtonToCommit() {
        saveButton.disableProperty().bind(nameTextField.textProperty().isEmpty().or(surnameTextField.textProperty().isEmpty()).or(peselTextField.textProperty().isEmpty())
                .or(pwzTextField.textProperty().isEmpty()).or(mailTextField.textProperty().isEmpty()).or(phoneTextField.textProperty().isEmpty()));
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}

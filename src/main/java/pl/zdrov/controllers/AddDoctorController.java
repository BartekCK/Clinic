package pl.zdrov.controllers;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import pl.zdrov.database.models.Doctor;
import pl.zdrov.database.models.Specialization;
import pl.zdrov.database.modelsFX.DoctorModel;
import pl.zdrov.database.modelsFX.SpecializationModel;
import pl.zdrov.utilies.DialogCatch;

public class AddDoctorController extends BackgroundController{


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
        specializationComboBox.setItems(SpecializationModel.returnAllSpecialization());
    }

    @FXML
    private void commitDoctor() {
        try {
            doctor = new Doctor(nameTextField.getText(),surnameTextField.getText(),peselTextField.getText(),mailTextField.getText(),phoneTextField.getText(),
                    pwzTextField.getText(),specializationComboBox.getValue());
            //CorrectDataCommit.checkDoctor(doctor);
            if(DialogCatch.infoCommitWorkHours())
            {
                doctorModel.saveDoctorInDataBase(doctor);
                setDoctor(doctor);
                getMainController().setCenter("/fxml/AddWorkHours.fxml");
            }

        }catch (Exception e)
        {
            DialogCatch.errorCommitDoctor(e.getMessage());
        }
    }

    private void hoverButtonToCommit() {
        saveButton.disableProperty().bind(nameTextField.textProperty().isEmpty().or(surnameTextField.textProperty().isEmpty()).or(peselTextField.textProperty().isEmpty())
                .or(pwzTextField.textProperty().isEmpty()).or(mailTextField.textProperty().isEmpty()).or(phoneTextField.textProperty().isEmpty()));
    }


}

package pl.zdrov.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import pl.zdrov.Path;
import pl.zdrov.database.models.Doctor;
import pl.zdrov.database.models.Specialization;
import pl.zdrov.database.modelsFX.DoctorModel;
import pl.zdrov.database.modelsFX.SpecializationModel;
import pl.zdrov.utilies.DialogCatch;

public class AddDoctorController  {

    private Doctor doctor;

    private DoctorModel doctorModel;

    private boolean isNewDoctor;

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
        this.doctorModel = new DoctorModel();
        this.specializationComboBox.setItems(SpecializationModel.returnAllSpecialization());
        this.isNewDoctor = true;

    }

    @FXML
    private void commitDoctor() {
        try {
            if (isNewDoctor)
            {
                doctor = new Doctor();
            }
            doctor.setName(nameTextField.getText());
            doctor.setSurName(surnameTextField.getText());
            doctor.setPesel(peselTextField.getText());
            doctor.setPwz(pwzTextField.getText());
            doctor.setMail(mailTextField.getText());
            doctor.setPhone(phoneTextField.getText());
            doctor.setSpecialization(specializationComboBox.getValue());

            //CorrectDataCommit.checkDoctor(doctor);
            if(DialogCatch.infoCommitWorkHours())
            {
                doctorModel.saveDoctorInDataBase(doctor);
                BackgroundController.setDoctor(doctor);
                BackgroundController.getMainController().setCenter(Path.ADD_WORK_HOURS_PATH);
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

    public void setTextFields(Doctor doctor)
    {
        nameTextField.setText(doctor.getName());
        surnameTextField.setText(doctor.getSurName());
        peselTextField.setText(doctor.getPesel());
        pwzTextField.setText(doctor.getPwz());
        mailTextField.setText(doctor.getMail());
        phoneTextField.setText(doctor.getPhone());
        specializationComboBox.setValue(doctor.getSpecialization());
        this.doctor = doctor;
        this.isNewDoctor = false;

    }


}

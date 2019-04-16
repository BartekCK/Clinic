package pl.zdrov.controllers;

import com.sun.glass.ui.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pl.zdrov.database.models.Doctor;
import pl.zdrov.database.models.Patient;
import pl.zdrov.database.modelsFX.RegistrationFx;
import pl.zdrov.database.modelsFX.RegistrationModel;
import pl.zdrov.database.modelsFX.WorkHoursFx;

import java.awt.event.ActionEvent;

public class RegistrationController {


    private Patient patient;
    private RegistrationModel registrationModel;

    @FXML
    private TextField doctorNameTextField;

    @FXML
    private TextField doctorSurnameTextField;

    @FXML
    private TextField doctorSpecializationTextField;

    @FXML
    private TableView<WorkHoursFx> workTableView;

    @FXML
    private TableColumn<WorkHoursFx, String> dayWorkTableView;

    @FXML
    private TableColumn<WorkHoursFx, String> fromWorkTableView;

    @FXML
    private TableColumn<WorkHoursFx, String> toWorkTableView;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField patientNameTextField;

    @FXML
    private TextField patientSurnameTextField;

    @FXML
    private TextField patientPeselTextField;

    @FXML
    private TextField chooseDayTextField;

    @FXML
    private TableView<String> registrationtableView;

    @FXML
    private TableColumn<String, String> idTableColumn;

    @FXML
    private TableColumn<String, String> fromTableColumn;

    @FXML
    private TableColumn<String, String> toTableColumn;

    @FXML
    public void initialize()
    {
        registrationModel = new RegistrationModel();

        workTableView.setItems(registrationModel.getWorkHoursModel().getWorkHoursFxList());
        dayWorkTableView.setCellValueFactory(cellData-> cellData.getValue().dayProperty());
        fromWorkTableView.setCellValueFactory(cellData-> cellData.getValue().timeFromProperty());
        toWorkTableView.setCellValueFactory(cellData-> cellData.getValue().timeToProperty());




    }

    @FXML
    void commitRegistration() {

    }

    public void setDoctor(Doctor doctor)
    {
        registrationModel.setDoctor(doctor);
        doctorNameTextField.setText(registrationModel.getDoctor().getName());
        doctorSurnameTextField.setText(registrationModel.getDoctor().getSurName());
        doctorSpecializationTextField.setText(registrationModel.getDoctor().getSpecialization().getTitle());

    }


    public void setPatient(Patient patient) {
        registrationModel.setPatient(patient);
        patientNameTextField.setText(registrationModel.getPatient().getName());
        patientSurnameTextField.setText(registrationModel.getPatient().getSurName());
        patientPeselTextField.setText(registrationModel.getPatient().getPesel());
    }
}

package pl.zdrov.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pl.zdrov.database.models.Doctor;
import pl.zdrov.database.models.Patient;
import pl.zdrov.database.models.WorkHours;
import pl.zdrov.database.modelsFX.RegistrationModel;
import pl.zdrov.database.modelsFX.WorkHoursFx;
import pl.zdrov.utilies.FxmlUtilies;

import java.time.LocalDate;
import java.time.LocalTime;

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
    private TableView<WorkHoursFx> registrationtableView;

    @FXML
    private TableColumn<WorkHoursFx, String> idTableColumn;

    @FXML
    private TableColumn<WorkHoursFx, String> fromTableColumn;

    @FXML
    private TableColumn<WorkHoursFx, String> toTableColumn;

    @FXML
    public void initialize()
    {
        registrationModel = new RegistrationModel();

        workTableView.setItems(registrationModel.getWorkHoursModel().getWorkHoursFxList());
        dayWorkTableView.setCellValueFactory(cellData-> cellData.getValue().dayProperty());
        fromWorkTableView.setCellValueFactory(cellData-> cellData.getValue().timeFromProperty());
        toWorkTableView.setCellValueFactory(cellData-> cellData.getValue().timeToProperty());


        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            chooseDayTextField.setText(newValue.toString());
            checkFreeDates(newValue);
        });



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

    public void checkFreeDates(LocalDate localDate)
    {

        registrationtableView.setItems(registrationModel.checkFreeRegistration(localDate));
        idTableColumn.setCellValueFactory(cellData-> cellData.getValue().dayProperty());
        fromTableColumn.setCellValueFactory(cellData-> cellData.getValue().timeFromProperty());
        toTableColumn.setCellValueFactory(cellData-> cellData.getValue().timeToProperty());

    }


}

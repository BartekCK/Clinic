package pl.zdrov.controllers;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import pl.zdrov.database.models.Doctor;
import pl.zdrov.database.models.Patient;
import pl.zdrov.database.models.Registration;
import pl.zdrov.database.modelsFX.RegistrationModel;
import pl.zdrov.database.modelsFX.WorkHoursFx;
import pl.zdrov.utilies.DialogCatch;
import pl.zdrov.utilies.Utils;
import pl.zdrov.utilies.exceptions.ApplicationException;
import java.time.LocalDate;


public class RegistrationController {

    private RegistrationModel registrationModel;

    private WorkHoursFx workHoursFx;

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
    private TableView<WorkHoursFx> registrationTableView;

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

        datePicker.disableProperty().bind(doctorNameTextField.textProperty().isEmpty());
        doctorNameTextField.textProperty().addListener(observable -> {
            datePicker.setValue(LocalDate.now());
        });

        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            chooseDayTextField.setText(newValue.toString());
            checkFreeDates(newValue);
        });

        registrationTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            this.workHoursFx = newValue;
        });



    }

    @FXML
    void commitRegistration(ActionEvent actionEvent) {

        try {
            if (registrationModel.getDoctor() == null || registrationModel.getPatient() == null)
                throw new ApplicationException("Lekarz/Pacjent nie został wybrany");
            if(this.workHoursFx == null)
                throw new ApplicationException("Dzień wizyty nie został wybrany");

            Registration registration = new Registration(registrationModel.getDoctor(),registrationModel.getPatient(),
                    Utils.convertToDate(datePicker.getValue()),this.workHoursFx.getTimeFrom(),this.workHoursFx.getTimeTo());
            if(DialogCatch.infoCommitWorkHours())
            {
                registrationModel.saveRegistrationInDataBase(registration);
                datePicker.setValue(LocalDate.now());
            }


        } catch (ApplicationException e) {
            DialogCatch.errorCommitDoctor(e.getMessage());
        }

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
        try
        {
            if(localDate.getDayOfWeek().getValue() == 6 || localDate.getDayOfWeek().getValue() == 7)
                throw new ApplicationException("Przychodnia nie pracuje w weekendy");
            if(localDate.isBefore(LocalDate.now()))
                throw new ApplicationException("Błąd wyboru daty");

            registrationTableView.setItems(registrationModel.checkFreeRegistration(localDate));
            idTableColumn.setCellValueFactory(cellData-> cellData.getValue().dayProperty());
            fromTableColumn.setCellValueFactory(cellData-> cellData.getValue().timeFromProperty());
            toTableColumn.setCellValueFactory(cellData-> cellData.getValue().timeToProperty());
        } catch (ApplicationException e) {
            DialogCatch.errorCommitDoctor(e.getMessage());
            datePicker.setValue(LocalDate.now());
        }

    }

}

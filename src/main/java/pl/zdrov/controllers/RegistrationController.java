package pl.zdrov.controllers;

import javafx.fxml.FXML;
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

/**
 * Klasa kontroluje wykonywane akcje na pliku Registration.fxml
 */
public class RegistrationController {

    /**
     * Przechowuje referencje obiektu, który odpowiada za akcje wykonywane na obiektach typu Registration
     */
    private RegistrationModel registrationModel;

    /**
     * Obiekt służacy do wyświetlania godzin pracy wybranego lekarza
     */
    private WorkHoursFx workHoursFx;

    /**
     * Referencja w celu wyświetlenia tekstu
     */
    @FXML
    private TextField doctorNameTextField;

    /**
     * Referencja w celu wyświetlenia tekstu
     */
    @FXML
    private TextField doctorSurnameTextField;

    /**
     * Referencja w celu wyświetlenia tekstu
     */
    @FXML
    private TextField doctorSpecializationTextField;
    /**
     * Wyświela godziny pracy
     */
    @FXML
    private TableView<WorkHoursFx> workTableView;

    /**
     * Wyświela godziny pracy
     */
    @FXML
    private TableColumn<WorkHoursFx, String> dayWorkTableView;

    /**
     * Wyświela godziny pracy
     */
    @FXML
    private TableColumn<WorkHoursFx, String> fromWorkTableView;

    /**
     * Wyświela godziny pracy
     */
    @FXML
    private TableColumn<WorkHoursFx, String> toWorkTableView;

    /**
     * Wybór dnia wuzyty
     */
    @FXML
    private DatePicker datePicker;

    /**
     * Referencja w celu wyświetlenia tekstu
     */
    @FXML
    private TextField patientNameTextField;

    /**
     * Referencja w celu wyświetlenia tekstu
     */
    @FXML
    private TextField patientSurnameTextField;

    /**
     * Referencja w celu wyświetlenia tekstu
     */
    @FXML
    private TextField patientPeselTextField;

    /**
     * Referencja w celu wyświetlenia tekstu
     */
    @FXML
    private TextField chooseDayTextField;

    /**
     * Wyświela godziny pracy
     */
    @FXML
    private TableView<WorkHoursFx> registrationTableView;

    /**
     * Wyświela godziny pracy
     */
    @FXML
    private TableColumn<WorkHoursFx, String> idTableColumn;

    /**
     * Wyświela godziny pracy
     */
    @FXML
    private TableColumn<WorkHoursFx, String> fromTableColumn;

    /**
     * Wyświela godziny pracy
     */
    @FXML
    private TableColumn<WorkHoursFx, String> toTableColumn;

    /**
     * Metoda wykonująca się tuż po konstruktorze
     */
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

    /**
     * Metoda wykonująca się po kliknięciu przycisku
     *
     * Służy do zapisu rejestracji
     */
    @FXML
    void commitRegistration() {

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

    /**
     * Ustala doktora
     * @param doctor wybrany lekarz
     */
    public void setDoctor(Doctor doctor)
    {
        registrationModel.setDoctor(doctor);
        doctorNameTextField.setText(registrationModel.getDoctor().getName());
        doctorSurnameTextField.setText(registrationModel.getDoctor().getSurName());
        doctorSpecializationTextField.setText(registrationModel.getDoctor().getSpecialization().getTitle());

    }

    /**
     * Ustala pacjenta
     * @param patient wybrany pacjent
     */
    public void setPatient(Patient patient) {
        registrationModel.setPatient(patient);
        patientNameTextField.setText(registrationModel.getPatient().getName());
        patientSurnameTextField.setText(registrationModel.getPatient().getSurName());
        patientPeselTextField.setText(registrationModel.getPatient().getPesel());
    }

    /**
     * Sprawdza wolne godziny do rejestracji pacjenta
     * @param localDate data wybranej wizyty
     */
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

package pl.zdrov.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import pl.zdrov.Path;
import pl.zdrov.database.modelsFX.PatientFx;
import pl.zdrov.database.modelsFX.PatientModel;
import pl.zdrov.database.modelsFX.RegistrationModel;
import pl.zdrov.utilies.DialogCatch;
import pl.zdrov.utilies.FxmlUtilies;
import pl.zdrov.utilies.converters.ConverterPatient;
import pl.zdrov.utilies.exceptions.ApplicationException;

/**
 * Klasa kontroluje wykonywane akcje na pliku ShowPatient.fxml
 */
public class ShowPatientController {
    /**
     * Przechowuje referencje obiektu, który odpowiada za akcje wykonywane na obiekcie patient
     */
    private PatientModel patientModel;

    /**
     * Referencja w celu pobrania tekstu
     */
    @FXML
    private TextField nameTextField;
    /**
     * Referencja w celu pobrania tekstu
     */
    @FXML
    private TextField surnameTextField;
    /**
     * Referencja w celu pobrania tekstu
     */
    @FXML
    private TextField peselTextField;
    /**
     * Wyświetlenie wszystkich pacjentów
     */
    @FXML
    private TableView<PatientFx> patientTableView;
    /**
     * Wyświetlenie wszystkich pacjentów
     */
    @FXML
    private TableColumn<PatientFx, String> nameTableView;
    /**
     * Wyświetlenie wszystkich pacjentów
     */
    @FXML
    private TableColumn<PatientFx, String> surnameTableView;
    /**
     * Wyświetlenie wszystkich pacjentów
     */
    @FXML
    private TableColumn<PatientFx, String> mailTableView;
    /**
     * Wyświetlenie wszystkich pacjentów
     */
    @FXML
    private TableColumn<PatientFx, String> phoneTableView;
    /**
     * Wyświetlenie wszystkich pacjentów
     */
    @FXML
    private TableColumn<PatientFx, String> cityTableView;
    /**
     * Wyświetlenie wszystkich pacjentów
     */
    @FXML
    private TableColumn <PatientFx, String>nfzTableView;

    /**
     * Metoda wykonująca się tuż po konstruktorze
     */
    @FXML
    public void initialize()
    {
        patientModel = new PatientModel();
        patientModel.init();
        patientTableView.setItems(patientModel.getPatientFxShowObservableList());
        nameTableView.setCellValueFactory(cellData-> cellData.getValue().nameProperty());
        surnameTableView.setCellValueFactory(cellData-> cellData.getValue().surNameProperty());
        mailTableView.setCellValueFactory(cellData-> cellData.getValue().mailProperty());
        phoneTableView.setCellValueFactory(cellData-> cellData.getValue().phoneProperty());
        cityTableView.setCellValueFactory(cellData-> cellData.getValue().cityProperty());
        nfzTableView.setCellValueFactory(cellData-> cellData.getValue().branchNfzProperty());



        patientTableView.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            patientModel.setPatientFx(newValue);
        }));

        nameTextField.textProperty().addListener(((observable, oldValue, newValue) -> {
            patientTableView.setItems(patientModel.nameSearch(newValue));
            setMainObservableList(newValue);
        }));
        surnameTextField.textProperty().addListener(((observable, oldValue, newValue) -> {
            patientTableView.setItems(patientModel.surnameSearch(newValue));
            setMainObservableList(newValue);
        }));
        peselTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            patientTableView.setItems(patientModel.peselSearch(newValue));
            setMainObservableList(newValue);
        });

    }

    /**
     * Metoda wykonująca się po kliknięcu prawym przyciskiem myszy
     */
    @FXML
    private void registerPatient() {
        RegistrationController registrationController;
        if(BackgroundController.getRegistrationController() == null)
        {
            registrationController = FxmlUtilies.getControllerNewWindow(Path.REGISTRATION_PATH,"Okno rejestracji").getController();
            BackgroundController.setRegistrationController(registrationController);
        }else {
            registrationController = BackgroundController.getRegistrationController();
        }
        registrationController.setPatient(ConverterPatient.convertToPatient(patientModel.getPatientFx()));
    }

    /**
     * Metoda wykonująca się po kliknięcu prawym przyciskiem myszy
     */
    @FXML
    private void showPatient() {

        ShowPersonController showPersonController = FxmlUtilies.getControllerNewWindow(Path.SHOW_PERSON_PATH,"").getController();
        showPersonController.setImage(ConverterPatient.convertToPatient(patientModel.getPatientFx()));
    }

    /**
     * Metoda wykonująca się po kliknięcu prawym przyciskiem myszy
     */
    @FXML
    private void editPatient() {

        AddPatientController addPatientController = FxmlUtilies.getControllerMainWindow(Path.ADD_PATIENT_PATH).getController();
        addPatientController.setTextFields(ConverterPatient.convertToPatient(patientModel.getPatientFx()));
    }

    /**
     * Metoda wykonująca się po kliknięcu prawym przyciskiem myszy
     */
    @FXML
    private void deletePatient() {
        try
        {
            if(RegistrationModel.isDoctorInRegistration("PATIENT_ID",ConverterPatient.convertToPatient(patientModel.getPatientFx())))
                throw new ApplicationException("Nie można usunąć pacjenta, ktory ma zaplanowane wizyty");
            patientModel.deletePatientInDataBase();
        }catch (ApplicationException e)
        {
            DialogCatch.errorCommitDoctor(e.getMessage());
        }
    }

    /**
     * Ustala aktualnego wybranego pacjenta w celu wykonywania na nim akcji
     * @param newValue sprawdza czy ma wyświetlić wszystkie dane
     */
    private void setMainObservableList(String newValue) {
        if(newValue.isEmpty())
        {
            patientTableView.setItems(patientModel.getPatientFxShowObservableList());
        }
    }
}

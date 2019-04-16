package pl.zdrov.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import pl.zdrov.Main;
import pl.zdrov.Path;
import pl.zdrov.database.models.Patient;
import pl.zdrov.database.modelsFX.PatientFx;
import pl.zdrov.database.modelsFX.PatientModel;
import pl.zdrov.utilies.FxmlUtilies;
import pl.zdrov.utilies.converters.ConverterDoctor;
import pl.zdrov.utilies.converters.ConverterPatient;

import java.io.IOException;

public class ShowPatientController {

    private PatientModel patientModel;
    private Patient patient;

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField surnameTextField;
    @FXML
    private TextField peselTextField;
    @FXML
    private TableView<PatientFx> patientTableView;
    @FXML
    private TableColumn<PatientFx, Number> idTableView;
    @FXML
    private TableColumn<PatientFx, String> nameTableView;
    @FXML
    private TableColumn<PatientFx, String> surnameTableView;
    @FXML
    private TableColumn<PatientFx, String> mailTableView;
    @FXML
    private TableColumn<PatientFx, String> phoneTableView;
    @FXML
    private TableColumn<PatientFx, String> cityTableView;
    @FXML
    private TableColumn <PatientFx, String>nfzTableView;

    @FXML
    public void initialize()
    {
        patientModel = new PatientModel();
        patientModel.init();
        patientTableView.setItems(patientModel.getPatientFxShowObservableList());
        idTableView.setCellValueFactory(cellData-> cellData.getValue().idProperty());
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
    @FXML
    private void showPatient() {

        ShowPersonController showPersonController = FxmlUtilies.getControllerNewWindow(Path.SHOW_PERSON_PATH,"").getController();
        showPersonController.setImage(ConverterPatient.convertToPatient(patientModel.getPatientFx()));
    }
    @FXML
    private void editPatient() {

        AddPatientController addPatientController = FxmlUtilies.getControllerMainWindow(Path.ADD_PATIENT_PATH).getController();
        addPatientController.setTextFields(ConverterPatient.convertToPatient(patientModel.getPatientFx()));
    }
    @FXML
    private void deletePatient() {
        patientModel.deletePatientInDataBase();
    }


    private void setMainObservableList(String newValue) {
        if(newValue.isEmpty())
        {
            patientTableView.setItems(patientModel.getPatientFxShowObservableList());
        }
    }
}

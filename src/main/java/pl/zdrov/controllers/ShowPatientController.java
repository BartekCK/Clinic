package pl.zdrov.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import pl.zdrov.database.models.Patient;
import pl.zdrov.database.modelsFX.PatientFx;
import pl.zdrov.database.modelsFX.PatientModel;

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
    private TableColumn<PatientFx, String> peselTableView;
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
        peselTableView.setCellValueFactory(cellData-> cellData.getValue().peselProperty());
        mailTableView.setCellValueFactory(cellData-> cellData.getValue().mailProperty());
        phoneTableView.setCellValueFactory(cellData-> cellData.getValue().phoneProperty());
        cityTableView.setCellValueFactory(cellData-> cellData.getValue().cityProperty());
        nfzTableView.setCellValueFactory(cellData-> cellData.getValue().branchNfzProperty());

    }

    @FXML
    private void registerPatient() {
    }
    @FXML
    private void showPatient() {
    }
    @FXML
    private void editPatient() {
    }
    @FXML
    private void deletePatient() {
    }
}

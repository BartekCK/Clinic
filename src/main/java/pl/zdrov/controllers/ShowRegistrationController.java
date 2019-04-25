package pl.zdrov.controllers;

import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import pl.zdrov.database.models.Registration;
import pl.zdrov.database.models.Specialization;
import pl.zdrov.database.modelsFX.DoctorFx;
import pl.zdrov.database.modelsFX.RegistrationFx;
import pl.zdrov.database.modelsFX.RegistrationModel;
import pl.zdrov.database.modelsFX.SpecializationModel;

import java.time.LocalDate;

public class ShowRegistrationController {

    private RegistrationModel registrationModel;

    @FXML
    private TextField nameDoctorTextField;

    @FXML
    private TextField surnameDoctorTextField;

    @FXML
    private ComboBox<Specialization> specializationConboBox;

    @FXML
    private ComboBox<String> hourComboBox;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField namePatientTextField;

    @FXML
    private TextField surnamePatientTextField;

    @FXML
    private TableView<RegistrationFx> tableView;

    @FXML
    private TableColumn<RegistrationFx, String> nameDoctorTableColumn;

    @FXML
    private TableColumn<RegistrationFx, String> surnameDoctorTableColumn;

    @FXML
    private TableColumn<RegistrationFx, String> specializationTableColumn;

    @FXML
    private TableColumn<RegistrationFx, LocalDate> dateTableColumn;

    @FXML
    private TableColumn<RegistrationFx, String> hourTableColumn;

    @FXML
    private TableColumn<RegistrationFx, String> namePatientTableColumn;

    @FXML
    private TableColumn<RegistrationFx, String> surnamePatientTableColumn;

    @FXML
    public void initialize()
    {
        registrationModel = new RegistrationModel();
        registrationModel.init();
        ObservableList<String> hourString = FXCollections.observableArrayList("08:00","08:30","09:00","09:30","10:00","10:30","11:00","11:30","12:00",
                "12:30","13:00","13:30","14:00","14:30","15:00","15:30","16:00");
        hourComboBox.setItems(hourString);
        specializationConboBox.setItems(SpecializationModel.returnAllSpecialization());

        tableView.setItems(registrationModel.getAllRegistrationListFx());
        nameDoctorTableColumn.setCellValueFactory(cellData -> cellData.getValue().getDoctorFx().nameProperty());
        surnameDoctorTableColumn.setCellValueFactory(cellData -> cellData.getValue().getDoctorFx().surNameProperty());
        specializationTableColumn.setCellValueFactory(cellData -> cellData.getValue().getDoctorFx().getSpecializationFx().titleProperty());
        dateTableColumn.setCellValueFactory(cellData -> cellData.getValue().addedDateProperty());
        hourTableColumn.setCellValueFactory(cellData -> cellData.getValue().timeFromProperty());
        namePatientTableColumn.setCellValueFactory(cellData -> cellData.getValue().getPatientFx().nameProperty());
        surnamePatientTableColumn.setCellValueFactory(cellData -> cellData.getValue().getPatientFx().surNameProperty());

        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            registrationModel.setRegistrationFx(newValue);
        });

    }

    @FXML
    void clearSearch(){
    }

    @FXML
    void deleteRegistration() {
        registrationModel.deleteRegistrationInDataBase();
    }

    @FXML
    void showDoctor() {

    }

    @FXML
    void showPatient() {

    }

}

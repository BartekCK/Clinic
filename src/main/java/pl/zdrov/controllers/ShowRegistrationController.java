package pl.zdrov.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ShowRegistrationController {

    @FXML
    private TextField nameDoctorTextField;

    @FXML
    private TextField surnameDoctorTextField;

    @FXML
    private ComboBox<?> specializationConboBox;

    @FXML
    private ComboBox<?> hourComboBox;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField namePatientTextField;

    @FXML
    private TextField surnamePatientTextField;

    @FXML
    private TableView<?> tableView;

    @FXML
    private TableColumn<?, ?> nameDoctorTableColumn;

    @FXML
    private TableColumn<?, ?> surnameDoctorTableColumn;

    @FXML
    private TableColumn<?, ?> specializationTableColumn;

    @FXML
    private TableColumn<?, ?> dateTableColumn;

    @FXML
    private TableColumn<?, ?> hourTableColumn;

    @FXML
    private TableColumn<?, ?> namePatientTableColumn;

    @FXML
    private TableColumn<?, ?> surnamePatientTableColumn;

    @FXML
    void clearSearch(){
    }

    @FXML
    void deleteRegistration() {

    }

    @FXML
    void showDoctor() {

    }

    @FXML
    void showPatient() {

    }

}

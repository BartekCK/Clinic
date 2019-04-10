package pl.zdrov.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ShowDoctorController {
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField surnameTextField;
    @FXML
    private ComboBox specializationComboBox;
    @FXML
    private TableView tableView;
    @FXML
    private TableColumn idTableView;
    @FXML
    private TableColumn nameTableView;
    @FXML
    private TableColumn surnameTableView;
    @FXML
    private TableColumn mailTableView;
    @FXML
    private TableColumn peselTableView;
    @FXML
    private TableColumn phoneTableView;
    @FXML
    private TableColumn pwzTableView;
    @FXML
    private TableColumn specializationTableView;

    @FXML
    public void initialize()
    {
        
    }

    @FXML
    public void searchDoctor(ActionEvent actionEvent) {
    }
}

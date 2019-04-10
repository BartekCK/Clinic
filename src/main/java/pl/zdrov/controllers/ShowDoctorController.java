package pl.zdrov.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pl.zdrov.database.models.Specialization;
import pl.zdrov.database.modelsFX.DoctorFx;
import pl.zdrov.database.modelsFX.DoctorModel;
import pl.zdrov.database.modelsFX.SpecializationModel;

public class ShowDoctorController {

    private DoctorModel doctorModel;

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField surnameTextField;
    @FXML
    private ComboBox<Specialization> specializationComboBox;

    @FXML
    private TableView<DoctorFx> tableView;
    @FXML
    private TableColumn<DoctorFx, Number> idTableView;
    @FXML
    private TableColumn<DoctorFx,String> nameTableView;
    @FXML
    private TableColumn<DoctorFx,String> surnameTableView;
    @FXML
    private TableColumn<DoctorFx,String> mailTableView;
    @FXML
    private TableColumn<DoctorFx,String> peselTableView;
    @FXML
    private TableColumn<DoctorFx,String> phoneTableView;
    @FXML
    private TableColumn<DoctorFx,String> pwzTableView;
    @FXML
    private TableColumn<DoctorFx,String> specializationTableView;

    @FXML
    public void initialize()
    {
        doctorModel = new DoctorModel();
        doctorModel.init();
        specializationComboBox.setItems(SpecializationModel.returnAllSpecialization());

        tableView.setPlaceholder(new Label("Brak wyników"));

        tableView.setItems(doctorModel.getDoctorFxShowObservableList());
        idTableView.setCellValueFactory(cellData-> cellData.getValue().idProperty());
        nameTableView.setCellValueFactory(cellData-> cellData.getValue().nameProperty());
        surnameTableView.setCellValueFactory(cellData-> cellData.getValue().surNameProperty());
        mailTableView.setCellValueFactory(cellData-> cellData.getValue().mailProperty());
        peselTableView.setCellValueFactory(cellData-> cellData.getValue().peselProperty());
        phoneTableView.setCellValueFactory(cellData-> cellData.getValue().phoneProperty());
        pwzTableView.setCellValueFactory(cellData-> cellData.getValue().pwzProperty());
        specializationTableView.setCellValueFactory(cellData->  cellData.getValue().getSpecializationFx().titleProperty());

        tableView.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {

        }));

        nameTextField.textProperty().addListener(((observable, oldValue, newValue) -> {
            tableView.setItems(doctorModel.nameSearch(newValue));
            setMainObservableList(newValue);
        }));
        surnameTextField.textProperty().addListener(((observable, oldValue, newValue) -> {
            tableView.setItems(doctorModel.surnameSearch(newValue));
            setMainObservableList(newValue);
        }));
        specializationComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            tableView.setItems(doctorModel.specializationSearch(newValue));
            setMainObservableList(newValue.getTitle());
        });


    }


    @FXML
    public void clearSearch() {
        specializationComboBox.setItems(SpecializationModel.returnAllSpecialization());
        tableView.setItems(doctorModel.getDoctorFxShowObservableList());

    }

    private void setMainObservableList(String newValue) {
        if(newValue.isEmpty())
        {
            tableView.setItems(doctorModel.getDoctorFxShowObservableList());
        }
    }


    public void registerPatient() {

    }

    public void checkWorkHourWork() {
    }

    public void editDoctor() {
    }

    public void deleteDoctor() {
    }
}

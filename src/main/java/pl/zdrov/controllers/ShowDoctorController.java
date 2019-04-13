package pl.zdrov.controllers;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import pl.zdrov.Main;
import pl.zdrov.Path;
import pl.zdrov.database.models.Specialization;
import pl.zdrov.database.modelsFX.DoctorFx;
import pl.zdrov.database.modelsFX.DoctorModel;
import pl.zdrov.database.modelsFX.SpecializationModel;
import pl.zdrov.utilies.FxmlUtilies;
import pl.zdrov.utilies.converters.ConverterDoctor;

import java.io.IOException;


public class ShowDoctorController{

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
    private TableColumn<DoctorFx,String> nameTableView,surnameTableView,mailTableView, peselTableView, phoneTableView,pwzTableView,specializationTableView;

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
            doctorModel.setDoctorFx(newValue);
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

    @FXML
    public void registerPatient() {
        //Object for late
    }
    @FXML
    public void checkWorkHourWork(){

        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            Main.newWindow(fxmlLoader.load(getClass().getResource(Path.ADD_WORK_HOURS_PATH).openStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        AddWorkHoursController addWorkHoursController = fxmlLoader.getController();
        addWorkHoursController.setVisibleHbox();
        addWorkHoursController.showWorkHours(ConverterDoctor.convertToDoctor(doctorModel.getDoctorFx()));


    }
    @FXML
    public void editDoctor() {

        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            BackgroundController.getMainController().setCenterParent(fxmlLoader.load(getClass().getResource(Path.ADD_DOCTOR_PATH).openStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        AddDoctorController addDoctorController = fxmlLoader.getController();
        addDoctorController.setTextFields(ConverterDoctor.convertToDoctor(doctorModel.getDoctorFx()));

    }
    @FXML
    public void deleteDoctor() {
        doctorModel.deleteDoctorInDataBase();
    }

    private void setMainObservableList(String newValue) {
        if(newValue.isEmpty())
        {
            tableView.setItems(doctorModel.getDoctorFxShowObservableList());
        }
    }

}

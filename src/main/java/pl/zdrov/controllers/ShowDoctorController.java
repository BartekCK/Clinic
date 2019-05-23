package pl.zdrov.controllers;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import pl.zdrov.Main;
import pl.zdrov.Path;
import pl.zdrov.database.models.Specialization;
import pl.zdrov.database.modelsFX.DoctorFx;
import pl.zdrov.database.modelsFX.DoctorModel;
import pl.zdrov.database.modelsFX.RegistrationModel;
import pl.zdrov.database.modelsFX.SpecializationModel;
import pl.zdrov.utilies.DialogCatch;
import pl.zdrov.utilies.FxmlUtilies;
import pl.zdrov.utilies.converters.ConverterDoctor;
import pl.zdrov.utilies.exceptions.ApplicationException;

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
    private TableColumn<DoctorFx,String> nameTableView,surnameTableView,mailTableView, phoneTableView,specializationTableView;

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
        phoneTableView.setCellValueFactory(cellData-> cellData.getValue().phoneProperty());
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
        nameTextField.clear();
        surnameTextField.clear();
        specializationComboBox.setItems(SpecializationModel.returnAllSpecialization());
        tableView.setItems(doctorModel.getDoctorFxShowObservableList());

    }

    @FXML
    public void registerPatient() {
        RegistrationController registrationController;
        if(BackgroundController.getRegistrationController() ==null)
        {
            registrationController = FxmlUtilies.getControllerNewWindow(Path.REGISTRATION_PATH,"Okno rejestracji").getController();
            BackgroundController.setRegistrationController(registrationController);
        }else {
            registrationController = BackgroundController.getRegistrationController();
        }
        registrationController.setDoctor(ConverterDoctor.convertToDoctor(doctorModel.getDoctorFx()));



    }
    @FXML
    private void showDoctor() {

        ShowPersonController showPersonController = FxmlUtilies.getControllerNewWindow(Path.SHOW_PERSON_PATH,"").getController();
        showPersonController.setImage(ConverterDoctor.convertToDoctor(doctorModel.getDoctorFx()));

    }


    @FXML
    public void checkWorkHourWork(){

        AddWorkHoursController addWorkHoursController = FxmlUtilies.getControllerNewWindow(Path.ADD_WORK_HOURS_PATH,"Godziny pracy lekarza").getController();
        addWorkHoursController.setVisibleHbox();
        addWorkHoursController.showWorkHours(ConverterDoctor.convertToDoctor(doctorModel.getDoctorFx()));


    }
    @FXML
    public void editDoctor() {

        AddDoctorController addDoctorController = FxmlUtilies.getControllerMainWindow(Path.ADD_DOCTOR_PATH).getController();
        addDoctorController.setTextFields(ConverterDoctor.convertToDoctor(doctorModel.getDoctorFx()));

    }
    @FXML
    public void deleteDoctor() {
        try
        {
            if(RegistrationModel.isDoctorInRegistration("DOCTOR_ID",ConverterDoctor.convertToDoctor(doctorModel.getDoctorFx())))
                throw new ApplicationException("Nie można usunąć lekarza, ktory ma zaplanowane wizyty");
            doctorModel.deleteDoctorInDataBase();
        }catch (ApplicationException e)
        {
            DialogCatch.errorCommitDoctor(e.getMessage());
        }

    }

    private void setMainObservableList(String newValue) {
        if(newValue.isEmpty())
        {
            tableView.setItems(doctorModel.getDoctorFxShowObservableList());
        }
    }

}

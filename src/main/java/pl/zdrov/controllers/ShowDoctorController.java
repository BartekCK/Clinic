package pl.zdrov.controllers;

import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import pl.zdrov.database.models.Specialization;
import pl.zdrov.database.modelsFX.DoctorFx;
import pl.zdrov.database.modelsFX.DoctorModel;

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



        System.out.println("PROBAAAAA");



        tableView.setItems(doctorModel.getDoctorFxShowObservableList());
        idTableView.setCellValueFactory(cellData-> cellData.getValue().idProperty());
        nameTableView.setCellValueFactory(cellData-> cellData.getValue().nameProperty());
        surnameTableView.setCellValueFactory(cellData-> cellData.getValue().surNameProperty());
        mailTableView.setCellValueFactory(cellData-> cellData.getValue().mailProperty());
        peselTableView.setCellValueFactory(cellData-> cellData.getValue().peselProperty());
        phoneTableView.setCellValueFactory(cellData-> cellData.getValue().phoneProperty());
        pwzTableView.setCellValueFactory(cellData-> cellData.getValue().pwzProperty());


        //specializationTableView.setCellValueFactory(cellData-> (ObservableValue<String>) cellData.getValue().getSpecializationFx());
        //specializationTableView.setCellValueFactory(cellData->  cellData.getValue().getSpecializationFx().titleProperty());

    }

    @FXML
    public void searchDoctor(ActionEvent actionEvent) {
    }
}

package pl.zdrov.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import pl.zdrov.database.models.Doctor;
import pl.zdrov.database.models.WorkHours;
import pl.zdrov.database.modelsFX.WorkHoursFx;
import pl.zdrov.database.modelsFX.WorkHoursModel;
import pl.zdrov.utilies.DialogCatch;
import pl.zdrov.utilies.FxmlUtilies;


public class AddWorkHoursController {

    private WorkHoursModel workHoursModel;

    private WorkHours workHours;

    private WorkHoursFx workHoursFx;

    private Doctor doctor;

    @FXML
    public ComboBox<String> dayComboBox;

    @FXML
    public ComboBox<String> fromComboBox;

    @FXML
    public ComboBox<String> toComboBox;

    @FXML
    public TableView<WorkHoursFx> tableViewWorkHours;

    @FXML
    public TableColumn<WorkHoursFx,String> dayTableView, hoursFromTableView,hoursToTableView;

    private ObservableList<String> dayString = FXCollections.observableArrayList("Poniedziałek","Wtorek","Środa","Czwartek","Piątek");

    private ObservableList<String> hourString = FXCollections.observableArrayList("08:00","08:30","09:00","09:30","10:00","10:30","11:00","11:30","12:00",
            "12:30","13:00","13:30","14:00","14:30","15:00","15:30","16:00");

    @FXML public void initialize()
    {

        dayComboBox.setItems(dayString);
        toComboBox.setItems(hourString);
        fromComboBox.setItems(hourString);

        workHoursModel = new WorkHoursModel();
        doctor = FxmlUtilies.getDoctor();//SPRAWDZIC

        tableViewWorkHours.setItems(workHoursModel.getWorkHoursFxList());
        dayTableView.setCellValueFactory(cellData-> cellData.getValue().dayProperty());
        hoursFromTableView.setCellValueFactory(cellData-> cellData.getValue().timeFromProperty());
        hoursToTableView.setCellValueFactory(cellData-> cellData.getValue().timeToProperty());
    }

    @FXML
    public void saveToLocalData() {
        try {
            StringProperty d = new SimpleStringProperty(dayComboBox.getValue());
            StringProperty from = new SimpleStringProperty(fromComboBox.getValue());
            StringProperty to = new SimpleStringProperty(toComboBox.getValue());
            System.out.println(d.toString()+" "+from.toString()+" "+to.toString());
            workHoursFx = new WorkHoursFx(d,from,to);
            workHoursModel.addTimeList(workHoursFx);

        }catch (Exception e)
        {
            DialogCatch.errorCommitDoctor(e.getMessage());
        }
    }

    @FXML
    public void deleteFromLocalData() {

    }

    @FXML
    public void commitWorkHours() {
    }
}

package pl.zdrov.controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import pl.zdrov.database.models.WorkHours;
import pl.zdrov.database.modelsFX.WorkHoursFx;
import pl.zdrov.database.modelsFX.WorkHoursModel;
import pl.zdrov.utilies.DialogCatch;
import pl.zdrov.utilies.converters.ConverterWorkHours;
import pl.zdrov.utilies.exceptions.ApplicationException;


public class AddWorkHoursController extends BackgroundController{

    private WorkHoursModel workHoursModel;

    private ObservableList<String> dayString = FXCollections.observableArrayList("Poniedziałek","Wtorek","Środa","Czwartek","Piątek");

    private ObservableList<String> hourString = FXCollections.observableArrayList("08:00","08:30","09:00","09:30","10:00","10:30","11:00","11:30","12:00",
            "12:30","13:00","13:30","14:00","14:30","15:00","15:30","16:00");

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

    @FXML
    public MenuItem delteItem;


    @FXML public void initialize()
    {

        dayComboBox.setItems(dayString);
        toComboBox.setItems(hourString);
        fromComboBox.setItems(hourString);

        workHoursModel = new WorkHoursModel();

        tableViewWorkHours.setItems(workHoursModel.getWorkHoursFxList());
        dayTableView.setCellValueFactory(cellData-> cellData.getValue().dayProperty());
        hoursFromTableView.setCellValueFactory(cellData-> cellData.getValue().timeFromProperty());
        hoursToTableView.setCellValueFactory(cellData-> cellData.getValue().timeToProperty());

        tableViewWorkHours.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            workHoursModel.setWorkHours(newValue);
        }));
    }

    @FXML
    public void saveToLocalData() {
        try {
            WorkHours workHours = new WorkHours(dayComboBox.getValue(),fromComboBox.getValue(),toComboBox.getValue());
            WorkHoursFx workHoursFx = ConverterWorkHours.convertToWorkHoursFx(workHours);
            System.out.println(workHoursFx.getDay()+workHoursFx.getTimeFrom()+workHoursFx.getTimeTo());
            workHoursModel.addTimeList(workHoursFx);

        }catch (Exception e)
        {
            DialogCatch.errorCommitDoctor(e.getMessage());
        }
    }

    @FXML
    public void deleteFromLocalData() {
        workHoursModel.deleteItemTimeList();
    }

    @FXML
    public void commitWorkHours() {
        if(DialogCatch.infoCommitWorkHours())
        {
            try {
                workHoursModel.saveWorkHoursInDataBase();

            } catch (ApplicationException e) {
                DialogCatch.errorCommitDoctor(e.getMessage());
            }
        }
    }


}

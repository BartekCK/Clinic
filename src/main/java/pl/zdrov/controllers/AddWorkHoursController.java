package pl.zdrov.controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import pl.zdrov.database.models.Doctor;
import pl.zdrov.database.modelsFX.WorkHoursFx;
import pl.zdrov.database.modelsFX.WorkHoursModel;
import pl.zdrov.utilies.DialogCatch;
import pl.zdrov.utilies.exceptions.CorrectDataCommit;


public class AddWorkHoursController {


    private WorkHoursModel workHoursModel;

    private ObservableList<String> dayString = FXCollections.observableArrayList("Poniedziałek","Wtorek","Środa","Czwartek","Piątek");

    private ObservableList<String> hourString = FXCollections.observableArrayList("08:00","08:30","09:00","09:30","10:00","10:30","11:00","11:30","12:00",
            "12:30","13:00","13:30","14:00","14:30","15:00","15:30","16:00");

    @FXML
    private ComboBox<String> dayComboBox;

    @FXML
    private ComboBox<String> fromComboBox;

    @FXML
    private ComboBox<String> toComboBox;

    @FXML
    private TableView<WorkHoursFx> tableViewWorkHours;

    @FXML
    private TableColumn<WorkHoursFx,String> dayTableView, hoursFromTableView,hoursToTableView;

    @FXML
    private HBox hbox;



    @FXML public void initialize()
    {

        dayComboBox.setItems(dayString);
        toComboBox.setItems(hourString);
        fromComboBox.setItems(hourString);

        workHoursModel = new WorkHoursModel();
        workHoursModel.init(BackgroundController.getDoctor());

        tableViewWorkHours.setItems(workHoursModel.getWorkHoursFxList());
        dayTableView.setCellValueFactory(cellData-> cellData.getValue().dayProperty());
        hoursFromTableView.setCellValueFactory(cellData-> cellData.getValue().timeFromProperty());
        hoursToTableView.setCellValueFactory(cellData-> cellData.getValue().timeToProperty());

        tableViewWorkHours.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            workHoursModel.setWorkHours(newValue);
        }));
    }

    @FXML
    private void saveToLocalData() {
        try {
            CorrectDataCommit.checkWorkHours(dayComboBox,fromComboBox,toComboBox);
            WorkHoursFx workHoursFx = new WorkHoursFx(dayComboBox.getValue(),fromComboBox.getValue(),toComboBox.getValue());
            workHoursModel.addWorkHoursFxList(workHoursFx);

        }catch (Exception e)
        {
            DialogCatch.errorCommitDoctor(e.getMessage());
        }
    }

    @FXML
    private void deleteFromLocalData() {
        workHoursModel.deleteItemTimeList();
    }

    @FXML
    private void commitWorkHours() {
        if(DialogCatch.infoCommitWorkHours())
        {
            workHoursModel.saveWorkHoursInDataBase();
            BackgroundController.getMainController().cleanWindow();

        }
    }

    public void setVisibleHbox()
    {
        hbox.setVisible(false);
    }

    public void showWorkHours(Doctor doctor)
    {
        workHoursModel.init(doctor);

    }
    
}

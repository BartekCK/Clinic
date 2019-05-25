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

/**
 * Klasa kontroluje wykonywane akcje na pliku AddWorkHours.fxml
 *
 */
public class AddWorkHoursController {

    /**
     * Przechowuje referencje obiektu, który odpowiada za akcje związane z ustaleniem godziny pracy
     */
    private WorkHoursModel workHoursModel;

    /**
     * Przechowuje nazwy dni w języku polskim
     */
    private final ObservableList<String> dayString = FXCollections.observableArrayList("Poniedziałek","Wtorek","Środa","Czwartek","Piątek");
    /**
     * Przechowuje nazwy godzin.
     */
    private final ObservableList<String> hourString = FXCollections.observableArrayList("08:00","08:30","09:00","09:30","10:00","10:30","11:00","11:30","12:00",
            "12:30","13:00","13:30","14:00","14:30","15:00","15:30","16:00");

    /**
     * Odpowiada za wybór dnia pracy
     */
    @FXML
    private ComboBox<String> dayComboBox;

    /**
     * Odpowiada za wybór godziny od ktorej lekarz rozpoczyna prace
     */
    @FXML
    private ComboBox<String> fromComboBox;

    /**
     * Odpowiada za wybór godziny do której lekarz pracuje
     */
    @FXML
    private ComboBox<String> toComboBox;

    /**
     * Wyświela wybrane godziny pracy
     */
    @FXML
    private TableView<WorkHoursFx> tableViewWorkHours;

    /**
     * Wyświela wybrane godziny pracy
     */
    @FXML
    private TableColumn<WorkHoursFx,String> dayTableView, hoursFromTableView,hoursToTableView;

    /**
     * Jest to Scena, na której wykonywane są operacje
     */
    @FXML
    private HBox hbox;


    /**
     * Metoda wykonująca się tuż po konstruktorze
     */
    @FXML
    public void initialize()
    {

        dayComboBox.setItems(dayString);
        toComboBox.setItems(hourString);
        fromComboBox.setItems(hourString);

        workHoursModel = new WorkHoursModel();
        if(BackgroundController.getDoctor() != null)
            workHoursModel.init(BackgroundController.getDoctor());

        tableViewWorkHours.setItems(workHoursModel.getWorkHoursFxList());
        dayTableView.setCellValueFactory(cellData-> cellData.getValue().dayProperty());
        hoursFromTableView.setCellValueFactory(cellData-> cellData.getValue().timeFromProperty());
        hoursToTableView.setCellValueFactory(cellData-> cellData.getValue().timeToProperty());

        tableViewWorkHours.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            workHoursModel.setWorkHours(newValue);
        }));
    }

    /**
     * Metoda wykonująca się po kliknięciu przycisku
     *
     * Służy do dodania nowego wymiaru godzin pracy, bez zapisu
     */
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

    /**
     * Metoda wykonująca się po kliknięciu prawym przyciskiem myszy na obiekt w TableView
     *
     * Służy do usunięcia wybranego pola
     */
    @FXML
    private void deleteFromLocalData() {
        workHoursModel.deleteItemTimeList();
    }

    /**
     * Metoda wykonująca się po kliknięciu przycisku
     *
     * Służy do zapisu ustalonych godzin
     */
    @FXML
    private void commitWorkHours() {
        if(DialogCatch.infoCommitWorkHours())
        {
            workHoursModel.saveWorkHoursInDataBase();
            BackgroundController.getMainController().cleanWindow();

        }
    }

    /**
     * Ustawia widoczność górnej sceny.
     */
    public void setVisibleHbox()
    {
        hbox.setVisible(false);
    }

    /**
     * Wyświetla godziny pracy wybranego lekarza
     * @param doctor to lekarz którego godziny pracy chcemy zobaczyć/edytować
     */
    public void showWorkHours(Doctor doctor)
    {
        workHoursModel.init(doctor);

    }

}

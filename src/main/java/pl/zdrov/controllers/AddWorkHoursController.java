package pl.zdrov.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class AddWorkHoursController {
    @FXML
    public ComboBox<String> dayComboBox;

    @FXML
    public ComboBox<String> fromComboBox;

    @FXML
    public ComboBox<String> toComboBox;

    ObservableList<String> dayString = FXCollections.observableArrayList("Poniedziałek","Wtorek","Środa","Czwartek","Piątek");
    ObservableList<String> hourString = FXCollections.observableArrayList("08:00","08:30","09:00","09:30","10:00","10:30","11:00","11:30","12:00",
            "12:30","13:00","13:30","14:00","14:30","15:00","15:30","16:00");

    @FXML public void initialize()
    {
        dayComboBox.setItems(dayString);
        toComboBox.setItems(hourString);
        fromComboBox.setItems(hourString);
    }

    @FXML
    public void saveToDataBase() {
    }

    @FXML
    public void saveAndClose() {
    }
}

package pl.zdrov.utilies;

import javafx.scene.control.Alert;

public class DialogCatch {

    public static void errorCommitDoctor(String error)
    {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Błąd wprowadzenia danych");
        errorAlert.setContentText(error);
        errorAlert.showAndWait();
    }
}

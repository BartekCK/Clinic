package pl.zdrov.utilies;

import javafx.scene.control.Alert;

public class DialogCatch {

    public static void errorCommitDoctor(String error)
    {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("BŁĄD");
        errorAlert.setHeaderText("Uwaga sprawdź poprawność danych !");
        errorAlert.setContentText(error);
        errorAlert.showAndWait();
    }
}

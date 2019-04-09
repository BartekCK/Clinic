package pl.zdrov.utilies;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class DialogCatch {

    public static void errorCommitDoctor(String error)
    {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("BŁĄD");
        errorAlert.setHeaderText("Uwaga sprawdź poprawność danych !");
        errorAlert.setContentText(error);
        errorAlert.showAndWait();
    }

    public static boolean infoCommitWorkHours()
    {
        Alert askAlert = new Alert(Alert.AlertType.CONFIRMATION,"Działania są nieodwracalne",ButtonType.OK,ButtonType.CANCEL);
        askAlert.setTitle("Zapis");
        askAlert.setHeaderText("Czy na pewno dokonać zapisu?");
        askAlert.showAndWait();
        if(askAlert.getResult() == ButtonType.OK)
            return true;
        else
            return false;
    }
}

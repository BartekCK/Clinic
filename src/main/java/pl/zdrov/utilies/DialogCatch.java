package pl.zdrov.utilies;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * Klasa odpowiada za wyświetlanie małych okien informacyjnych dotyczących działania programu.
 */
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

    public static void projectInf()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Przychodnia");
        alert.setHeaderText("O nas!");
        alert.setContentText("Projekt Przychodnia został utworzony przez studentów II roku Informatyki, Bartłomieja Kotarskiego" +
                " i Marcina Kozłowskiego. Został oceniony na ocenę bardzo dobrą (5). W przypadku znalezienia błędów lub chęci dalszej pracy nad projektem" +
                " zapraszamy do kontaktu:\n\n•bkotarski1@gmail.com\n•marcinjankozlowski@interia.pl");

        alert.showAndWait();
    }
}

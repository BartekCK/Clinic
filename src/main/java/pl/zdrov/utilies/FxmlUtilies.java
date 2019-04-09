package pl.zdrov.utilies;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import pl.zdrov.database.models.Doctor;

public class FxmlUtilies {

    static private Doctor doctor;

    static public Parent setFxml(String fxmlPath)
    {
        try {
            Parent root = FXMLLoader.load(FxmlUtilies.class.getResource(fxmlPath));
            return root;
        }catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    public static Doctor getDoctor() {
        return doctor;
    }

    public static void setDoctor(Doctor doctor) {
        FxmlUtilies.doctor = doctor;
    }
}

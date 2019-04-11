package pl.zdrov.utilies;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import pl.zdrov.database.models.Doctor;

public class FxmlUtilies {



    static public Parent setFxmlParent(String fxmlPath)
    {
        try {
            return FXMLLoader.load(FxmlUtilies.class.getResource(fxmlPath));
        }catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }



}

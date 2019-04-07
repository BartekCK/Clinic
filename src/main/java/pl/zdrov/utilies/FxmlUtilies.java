package pl.zdrov.utilies;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class FxmlUtilies {

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
}

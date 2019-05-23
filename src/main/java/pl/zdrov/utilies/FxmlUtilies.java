package pl.zdrov.utilies;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import pl.zdrov.Main;
import pl.zdrov.Path;
import pl.zdrov.controllers.BackgroundController;
import pl.zdrov.database.models.Doctor;

import java.io.IOException;

public class FxmlUtilies {



    static public Parent setFxmlParent(String fxmlPath)
    {
        try {
            Parent root = FXMLLoader.load(FxmlUtilies.class.getResource(fxmlPath));
            root.getStylesheets().addAll(FxmlUtilies.class.getResource(Path.CSS_PATH).toExternalForm());
            return root;
        }catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    static public FXMLLoader getControllerNewWindow(String path,String title)
    {
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            Main.newWindow(fxmlLoader.load(FxmlUtilies.class.getResource(path).openStream()),title);
        } catch (IOException e) {
            DialogCatch.errorCommitDoctor(e.getMessage());
        }

        return fxmlLoader;
    }

    static public FXMLLoader getControllerMainWindow(String path)
    {
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            BackgroundController.getMainController().setCenterParent(fxmlLoader.load(FxmlUtilies.class.getResource(path).openStream()));
        } catch (IOException e) {
            DialogCatch.errorCommitDoctor(e.getMessage());
        }
        return fxmlLoader;
    }


}

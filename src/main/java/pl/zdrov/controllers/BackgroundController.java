package pl.zdrov.controllers;

import pl.zdrov.database.models.Doctor;
import pl.zdrov.utilies.FxmlUtilies;

public class BackgroundController {

    private static MainController mainController;
    static private Doctor doctor;

    public static MainController getMainController() {
        return mainController;
    }

    public void setMainController(MainController mainController) {
        BackgroundController.mainController = mainController;
    }
    public static Doctor getDoctor() {
        return doctor;
    }

    public static void setDoctor(Doctor doctor) {
        BackgroundController.doctor = doctor;
    }
}

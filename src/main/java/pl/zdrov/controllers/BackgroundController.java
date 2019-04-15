package pl.zdrov.controllers;


import pl.zdrov.database.models.Doctor;


public class BackgroundController {

    private static MainController mainController;
    private static Doctor doctor;

    public static MainController getMainController() {
        return mainController;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public static Doctor getDoctor() {
        return doctor;
    }

    public static void setDoctor(Doctor doctor) {
        BackgroundController.doctor = doctor;
    }
}

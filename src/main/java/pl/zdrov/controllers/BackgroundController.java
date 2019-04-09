package pl.zdrov.controllers;

public class BackgroundController {

    private static MainController mainController;

    public static MainController getMainController() {
        return mainController;
    }

    public void setMainController(MainController mainController) {
        BackgroundController.mainController = mainController;
    }
}

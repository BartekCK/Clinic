package pl.zdrov.controllers;


import javafx.fxml.FXML;

public class FooterController {


    @FXML
    public void showBackGround() {
        BackgroundController.getMainController().cleanWindow();
    }
}

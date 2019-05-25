package pl.zdrov.controllers;


import javafx.fxml.FXML;
/**
 * Klasa kontroluje wykonywane akcje na pliku Footer.fxml
 *
 */
public class FooterController {

    /**
     * Ustawia tło główne podczas kliknięcia w przycisk
     */
    @FXML
    public void showBackGround() {
        BackgroundController.getMainController().cleanWindow();
    }
}

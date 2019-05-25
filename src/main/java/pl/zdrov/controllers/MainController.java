package pl.zdrov.controllers;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import pl.zdrov.Path;
import pl.zdrov.utilies.DialogCatch;
import pl.zdrov.utilies.FxmlUtilies;

/**
 * Klasa kontroluje wykonywane akcje na pliku Main.fxml
 */
public class MainController {

    /**
     * Referencja do tła
     */
    @FXML
    private BackgroundController backController;

    /**
     * Referencja do stopki
     */
    @FXML
    private FooterController downController;


    /**
     * Referencja do głównej sceny, na której wykonywane są akcje przez inne kontrolki
     */
    @FXML
    private StackPane stackPane;

    /**
     * Metoda wykonująca się tuż po konstruktorze
     */
    @FXML
    public void initialize()
    {
        backController.setMainController(this);
    }

    /**
     * Ustawia nową scene na StackPane
     * @param fxmlPath ściezka do nowej sceny
     */
    public void setCenter(String fxmlPath)
    {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(FxmlUtilies.setFxmlParent(fxmlPath));
    }

    /**
     * Ustawia nową scene na StackPane
     * @param parent obiekt, kótry ma zasłonić stackPane
     */
    public void setCenterParent(Parent parent)
    {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(parent);
    }

    /**
     * Czyszczenie stackPane i ustawienie tła
     */
    public void cleanWindow()
    {
        stackPane.getChildren().clear();
        ImageView iv = new ImageView(new Image("/images/background.png"));
        stackPane.getChildren().add(iv);
    }

    /**
     * Akcja z górnego menu.
     */
    @FXML
    public void itemShowDoctors()
    {
        setCenter(Path.SHOW_DOCTOR_PATH);
    }
    /**
     * Akcja z górnego menu.
     */
    @FXML
    public void itemAddDoctor()
    {
        setCenter(Path.ADD_DOCTOR_PATH);
    }
    /**
     * Akcja z górnego menu.
     */
    @FXML
    public void itemAddPatient() {
        setCenter(Path.ADD_PATIENT_PATH);
    }
    /**
     * Akcja z górnego menu.
     */
    @FXML
    public void itemShowPatient() {
        setCenter(Path.SHOW_PATIENT_PATH);
    }
    /**
     * Akcja z górnego menu.
     */
    @FXML
    public void itemShowRegistration() {
        setCenter(Path.SHOW_REGISTRATION_PATH);
    }
    /**
     * Akcja z górnego menu.
     */
    @FXML
    public void projectInformation() {
        DialogCatch.projectInf();
    }
    /**
     * Akcja z górnego menu.
     */
    @FXML
    public void projectClose() {
        System.exit(0);
    }


}

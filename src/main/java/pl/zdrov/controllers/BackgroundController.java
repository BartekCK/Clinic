package pl.zdrov.controllers;

import pl.zdrov.database.models.Doctor;

/**
 * Klasa kontroluje wykonywane akcje na pliku Background.fxml
 */
public class BackgroundController {

    /**
     * Przechowuje referencje do głównej sceny
     */
    private static MainController mainController;

    /**
     * Przechowuje referencje sceny odpowiadającej za rejestracje
     */
    private static RegistrationController registrationController;

    /**
     * Przechowuje referencje do atywnego doktora
     */
    private static Doctor doctor;

    /**
     * Zwraca referencje do głównej sceny
     * @return MainController czyli referencje do sceny głównej
     */
    public static MainController getMainController() {
        return mainController;
    }

    /**
     * Ustala scene glówną
     * @param mainController jest to kontroller odpowiadający za pracę sceny głownej
     */
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    /**
     * Zwraca kontroller do rejestracji
     * @return registrationController czyli zwraca kontroller do rejestracji
     */
    public static RegistrationController getRegistrationController() {
        return registrationController;
    }

    /**
     * Ustawia aktywny panel rejestracji
     * @param registrationController jest to aktywny panel rejestracji
     */
    public static void setRegistrationController(RegistrationController registrationController) {
        BackgroundController.registrationController = registrationController;
    }

    /**
     * Zwraca aktywnego dotora
     * @return doctor czyli aktywny doktor, potrzebny do wykonywania akcji między różnymi kontrolkami
     */
    public static Doctor getDoctor() {
        return doctor;
    }

    /**
     * Ustawia dotkora
     * @param doctor referencja do obiektu potrzebnego do pracy
     */
    public static void setDoctor(Doctor doctor) {
        BackgroundController.doctor = doctor;
    }
}

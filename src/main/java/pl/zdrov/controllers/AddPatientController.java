package pl.zdrov.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import pl.zdrov.database.models.Patient;
import pl.zdrov.database.modelsFX.PatientModel;
import pl.zdrov.utilies.DialogCatch;
import pl.zdrov.utilies.exceptions.CorrectDataCommit;

/**
 * Klasa kontroluje wykonywane akcje na pliku AddPatient.fxml
 */
public class AddPatientController {

    /**
     * Przechowuje referencje do dodawanego pacjenta
     */
    private Patient patient;

    /**
     * Przechowuje referencje obiektu, który odpowiada za akcje wykonywane na obiekcie doctor
     */
    private PatientModel patientModel;

    /**
     * Sprawdza czy dodawany jest nowy obiekt, ponieważ może być to ecdycja istniejącego już obiektu
     */
    private boolean isNewPatient;

    /**
     * Referencja do obiektu w celu pobrania tekstu
     */
    @FXML
    private TextField nameTextField;

    /**
     * Referencja do obiektu w celu pobrania tekstu
     */
    @FXML
    private TextField surnameTextField;

    /**
     * Referencja do obiektu w celu pobrania tekstu
     */
    @FXML
    private TextField peselTextField;
    @FXML
    private TextField mailTextField;

    /**
     * Referencja do obiektu w celu pobrania tekstu
     */
    @FXML
    private TextField phoneTextField;

    /**
     * Referencja do obiektu w celu pobrania tekstu
     */
    @FXML
    private ComboBox<String> nfzComboBox;

    /**
     * Referencja do obiektu w celu pobrania tekstu
     */
    @FXML
    private TextField cityTextField;

    /**
     * Referencja do obiektu w celu pobrania tekstu
     */
    @FXML
    private TextField adressTextField;

    /**
     * Referencja do obiektu w celu pobrania tekstu
     */
    @FXML
    private TextField zipCode1TextField;

    /**
     * Referencja do obiektu w celu pobrania tekstu
     */
    @FXML
    private TextField zipCode2TextField;

    /**
     * Referencja do obiektu w celu pobrania tekstu
     */
    @FXML
    private TextField weightTextField;

    /**
     * Referencja do obiektu w celu pobrania tekstu
     */
    @FXML
    private TextField heightTextField;

    /**
     * Referencja do obiektu w celu pobrania tekstu
     */
    @FXML
    private Button saveButton;

    /**
     * Lista przochowywująca stałe wartości
     *
     * Przechowuje oddziałt NFZ, które w Polsce są stałą wartością i zależą od województw
     */
    private final ObservableList<String> nfzList = FXCollections.observableArrayList("01 - Dolnośląski Oddział Narodowego Funduszu Zdrowia we Wrocławiu",
            "02 - Kujawsko-Pomorski Oddział Narodowego Funduszu Zdrowia w Bydgoszczy",
            "03 - Lubelski Oddział Narodowego Funduszu Zdrowia w Lublinie",
            "04 - Lubuski Oddział Narodowego Funduszu Zdrowia w Zielonej Górze",
            "05 - Łódzki Oddział Narodowego Funduszu Zdrowia w Łodzi",
            "06 - Małopolski Oddział Narodowego Funduszu Zdrowia w Krakowie",
            "07 - Mazowiecki Oddział Narodowego Funduszu Zdrowia w Warszawie",
            "08 - Opolski Oddział Narodowego Funduszu Zdrowia w Opolu",
            "09 - Podkarpacki Oddział Narodowego Funduszu Zdrowia w Rzeszowie",
            "10 - Podlaski Oddział Narodowego Funduszu Zdrowia w Białymstoku",
            "11 - Pomorski Oddział Narodowego Funduszu Zdrowia w Gdańsku",
            "12 - Śląski Oddział Narodowego Funduszu Zdrowia w Katowicach",
            "13 - Świętokrzyski Oddział Narodowego Funduszu Zdrowia w Kielcach",
            "14 - Warmińsko-Mazurski Oddział Narodowego Funduszu Zdrowia w Olsztynie",
            "15 - Wielkopolski Oddział Narodowego Funduszu Zdrowia w Poznaniu",
            "16 - Zachodniopomorski Oddział Narodowego Funduszu Zdrowia w Szczecinie");

    /**
     * Metoda wykonująca się tuż po konstruktorze
     */
    @FXML
    private void initialize()
    {
        hoverButtonToCommit();
        nfzComboBox.setItems(nfzList);
        patientModel = new PatientModel();
        this.isNewPatient = true;
    }

    /**
     * Metoda wykonująca się po kliknięciu przycisku saveButton
     *
     * Służy do utworzenia nowego obiektu lub edycji instniejącego, typu Patient i dodania go do bazy danych
     */
    @FXML
    private void commitPatient() {

        try {
            if (isNewPatient)
            {
                patient = new Patient();
            }
            patient.setName(nameTextField.getText());
            patient.setSurName(surnameTextField.getText());
            patient.setPesel(peselTextField.getText());
            patient.setMail(mailTextField.getText());
            patient.setPhone(phoneTextField.getText());
            patient.setAddress(adressTextField.getText());
            patient.setCity(cityTextField.getText());
            patient.setZipCode(connectZipCode());
            patient.setWeight(Double.parseDouble(weightTextField.getText()));
            patient.setHeight(Double.parseDouble(heightTextField.getText()));
            patient.setBranchNfz(nfzComboBox.getValue());
            CorrectDataCommit.checkPatient(patient);
            if(DialogCatch.infoCommitWorkHours())
            {
                patientModel.savePatientInDataBase(patient);
                BackgroundController.getMainController().cleanWindow();
            }
        } catch (Exception e) {
            DialogCatch.errorCommitDoctor(e.getMessage());
        }


    }
    /**
     * Metoda opdowiada za bindowanie przycisku i wykorzystana jest w metodzie initialize
     */
    private void hoverButtonToCommit() {
        saveButton.disableProperty().bind(nameTextField.textProperty().isEmpty().or(surnameTextField.textProperty().isEmpty()).or(peselTextField.textProperty().isEmpty())
                .or(cityTextField.textProperty().isEmpty()).or(mailTextField.textProperty().isEmpty()).or(phoneTextField.textProperty().isEmpty())
                .or(adressTextField.textProperty().isEmpty()).or(zipCode1TextField.textProperty().isEmpty()).or(zipCode2TextField.textProperty().isEmpty()));
    }

    /**
     * Łączy ze sobą dwa ciągi znaków
     * @return zwraca uzysakne String z pól odpowiadających za kod pocztowy
     */
    private String connectZipCode()
    {
        return zipCode1TextField.getText()+"-"+zipCode2TextField.getText();
    }
    /**
     * Metoda podczas wykoywania akcji edycji odpowiada za uzupełenie TextField stosownym tekstem
     * @param patient to referencja to edytowanego obiektu pobranego z bazy danych
     */
    public void setTextFields(Patient patient) {

        nameTextField.setText(patient.getName());
        surnameTextField.setText(patient.getSurName());
        peselTextField.setText(patient.getPesel());
        mailTextField.setText(patient.getMail());
        phoneTextField.setText(patient.getPhone());
        nfzComboBox.setValue(patient.getBranchNfz());
        cityTextField.setText(patient.getCity());
        adressTextField.setText(patient.getAddress());
        zipCode1TextField.setText(patient.getZipCode().substring(0,2));
        zipCode2TextField.setText(patient.getZipCode().substring(3));
        weightTextField.setText(Double.toString(patient.getWeight()));
        heightTextField.setText(Double.toString(patient.getHeight()));
        this.patient = patient;
        this.isNewPatient = false;

    }
}

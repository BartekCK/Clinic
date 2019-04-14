package pl.zdrov.controllers;


import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import pl.zdrov.database.models.Patient;
import pl.zdrov.database.modelsFX.PatientModel;
import pl.zdrov.utilies.DialogCatch;
import pl.zdrov.utilies.exceptions.ApplicationException;
import pl.zdrov.utilies.exceptions.CorrectDataCommit;

public class AddPatientController {

    private Patient patient;
    private boolean isNewPatient;
    private PatientModel patientModel;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField surnameTextField;
    @FXML
    private TextField peselTextField;
    @FXML
    private TextField mailTextField;
    @FXML
    private TextField phoneTextField;
    @FXML
    private ComboBox<String> nfzComboBox;
    @FXML
    private TextField cityTextField;
    @FXML
    private TextField adressTextField;
    @FXML
    private TextField zipCode1TextField;
    @FXML
    private TextField zipCode2TextField;
    @FXML
    private TextField weightTextField;
    @FXML
    private TextField heightTextField;
    @FXML
    private Button saveButton;

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

    @FXML
    private void initialize()
    {
        hoverButtonToCommit();
        nfzComboBox.setItems(nfzList);
        patientModel = new PatientModel();
        this.isNewPatient = true;
    }

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
            //CorrectDataCommit.checkPatient(patient);
            if(DialogCatch.infoCommitWorkHours())
            {
                patientModel.savePatientInDataBase(patient);
                BackgroundController.getMainController().cleanWindow();
            }
        } catch (Exception e) {
            DialogCatch.errorCommitDoctor(e.getMessage());
        }


    }

    private void hoverButtonToCommit() {
        saveButton.disableProperty().bind(nameTextField.textProperty().isEmpty().or(surnameTextField.textProperty().isEmpty()).or(peselTextField.textProperty().isEmpty())
                .or(cityTextField.textProperty().isEmpty()).or(mailTextField.textProperty().isEmpty()).or(phoneTextField.textProperty().isEmpty())
                .or(adressTextField.textProperty().isEmpty()).or(zipCode1TextField.textProperty().isEmpty()).or(zipCode2TextField.textProperty().isEmpty()));
    }

    private String connectZipCode()
    {
        return zipCode1TextField.getText()+"-"+zipCode2TextField.getText();
    }

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

package pl.zdrov.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import pl.zdrov.Path;
import pl.zdrov.database.models.Doctor;
import pl.zdrov.database.models.Specialization;
import pl.zdrov.database.modelsFX.DoctorModel;
import pl.zdrov.database.modelsFX.SpecializationModel;
import pl.zdrov.utilies.DialogCatch;
import pl.zdrov.utilies.exceptions.CorrectDataCommit;


/**
 * Klasa kontroluje wykonywane akcje na pliku AddDoctor.fxml
 *
 */
public class AddDoctorController  {

    /**
     * Przechowuje referencje do dodawanego doktora
     */
    private Doctor doctor;

    /**
     * Przechowuje referencje obiektu, który odpowiada za akcje wykonywane na obiekcie doctor
     */
    private DoctorModel doctorModel;

    /**
     * Sprawdza czy dodawany jest nowy obiekt, ponieważ może być to ecdycja istniejącego już obiektu
     */
    private boolean isNewDoctor;

    /**
     * Referencja do przycisku zapisującego lekarza w bazie danych, w celu bindownaia.
     */
    @FXML
    private Button saveButton;

    /**
     * Referencja w celu pobrania tekstu
     */
    @FXML
    private TextField nameTextField;

    /**
     * Referencja w celu pobrania tekstu
     */
    @FXML
    private TextField surnameTextField;

    /**
     * Referencja w celu pobrania tekstu
     */
    @FXML
    private TextField peselTextField;

    /**
     * Referencja w celu pobrania tekstu
     */
    @FXML
    private TextField pwzTextField;

    /**
     * Referencja w celu pobrania tekstu
     */
    @FXML
    private TextField mailTextField;

    /**
     * Referencja w celu pobrania tekstu
     */
    @FXML
    private TextField phoneTextField;

    /**
     * Referencja w celu wyboru specializacji lekarza
     */
    @FXML
    private ComboBox<Specialization> specializationComboBox;

    /**
     * Metoda wykonująca się tuż po konstruktorze
     */
    @FXML
    private void initialize()
    {
        hoverButtonToCommit();
        this.doctorModel = new DoctorModel();
        this.specializationComboBox.setItems(SpecializationModel.returnAllSpecialization());
        this.isNewDoctor = true;

    }

    /**
     * Metoda wykonująca się po kliknięciu przycisku saveButton
     *
     * Służy do utworzenia nowego obiektu typu Doctor i dodania go do bazy danych
     */
    @FXML
    private void commitDoctor() {
        try {
            if (isNewDoctor)
            {
                doctor = new Doctor();
            }
            doctor.setName(nameTextField.getText());
            doctor.setSurName(surnameTextField.getText());
            doctor.setPesel(peselTextField.getText());
            doctor.setPwz(pwzTextField.getText());
            doctor.setMail(mailTextField.getText());
            doctor.setPhone(phoneTextField.getText());
            doctor.setSpecialization(specializationComboBox.getValue());

            CorrectDataCommit.checkDoctor(doctor);
            if(DialogCatch.infoCommitWorkHours())
            {
                doctorModel.saveDoctorInDataBase(doctor);
                BackgroundController.setDoctor(doctor);
                BackgroundController.getMainController().setCenter(Path.ADD_WORK_HOURS_PATH);
            }
        }catch (Exception e)
        {
            DialogCatch.errorCommitDoctor(e.getMessage());
        }
    }

    /**
     * Metoda opdowiada za bindowanie przycisku i wykorzystana jest w metodzie initialize
     */
    private void hoverButtonToCommit() {
        saveButton.disableProperty().bind(nameTextField.textProperty().isEmpty().or(surnameTextField.textProperty().isEmpty()).or(peselTextField.textProperty().isEmpty())
                .or(pwzTextField.textProperty().isEmpty()).or(mailTextField.textProperty().isEmpty()).or(phoneTextField.textProperty().isEmpty()));
    }

    /**
     * Metoda podczas wykoywania akcji edycji odpowiada za uzupełenie TextField stosownym tekstem
     * @param doctor to referencja to edytowanego obiektu pobranego z bazy danych
     */
    public void setTextFields(Doctor doctor)
    {
        nameTextField.setText(doctor.getName());
        surnameTextField.setText(doctor.getSurName());
        peselTextField.setText(doctor.getPesel());
        pwzTextField.setText(doctor.getPwz());
        mailTextField.setText(doctor.getMail());
        phoneTextField.setText(doctor.getPhone());
        specializationComboBox.setValue(doctor.getSpecialization());
        this.doctor = doctor;
        this.isNewDoctor = false;

    }


}

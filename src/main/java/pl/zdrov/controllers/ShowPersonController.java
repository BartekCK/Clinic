package pl.zdrov.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import pl.zdrov.database.models.Doctor;
import pl.zdrov.database.models.Patient;
import pl.zdrov.database.models.expands.BaseModel;

/**
 * Klasa kontroluje wykonywane akcje na pliku ShowPerson.fxml
 */
public class ShowPersonController {

    /**
     * Referencja w celu wyświetlenia tekstu
     */
    @FXML
    private TextField nameTextField, surnameTextField, peselTextField,phoneTextField,mailTextField,pwzTextField, specializationTextField,
            adressTextField,zipTextField, cityTextField,nfzTextField, weightTextField, heightTextField;
    /**
     * Ustala aktywne zdjęcie
     */
    @FXML
    private ImageView image;
    /**
     * Jest to Scena, na której wykonywane są operacje
     */
    @FXML
    private HBox doctorHBox,patientHBox1, patientHBox2, patientHBox3;


    /**
     * Ustawia zdjęcie w zależności czy to lekarz czy pacjent
     * @param baseModel to lekarz lub pacjent
     */
    public void setImage(BaseModel baseModel)
    {
        if (baseModel instanceof Patient)
        {
            image.setImage(new Image("images/patient.png"));
            doctorHBox.setDisable(true);

            nameTextField.setText(((Patient)baseModel).getName());
            surnameTextField.setText(((Patient)baseModel).getSurName());
            peselTextField.setText(((Patient)baseModel).getPesel());
            phoneTextField.setText(((Patient)baseModel).getPhone());
            mailTextField.setText(((Patient)baseModel).getMail());

            adressTextField.setText(((Patient)baseModel).getAddress());
            zipTextField.setText(((Patient)baseModel).getZipCode());
            cityTextField.setText(((Patient)baseModel).getCity());
            nfzTextField.setText(((Patient)baseModel).getBranchNfz());
            weightTextField.setText(String.valueOf(((Patient)baseModel).getWeight()));
            heightTextField.setText(String.valueOf(((Patient)baseModel).getHeight()));



        }else if(baseModel instanceof Doctor)
        {

            image.setImage(new Image("images/doctor.png"));
            patientHBox1.setDisable(true);
            patientHBox2.setDisable(true);
            patientHBox3.setDisable(true);

            nameTextField.setText(((Doctor)baseModel).getName());
            surnameTextField.setText(((Doctor)baseModel).getSurName());
            peselTextField.setText(((Doctor)baseModel).getPesel());
            phoneTextField.setText(((Doctor)baseModel).getPhone());
            mailTextField.setText(((Doctor)baseModel).getMail());

            pwzTextField.setText(((Doctor)baseModel).getPwz());
            specializationTextField.setText(((Doctor)baseModel).getSpecialization().toString());

        }

    }

}

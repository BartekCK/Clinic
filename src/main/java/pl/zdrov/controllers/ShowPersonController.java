package pl.zdrov.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import pl.zdrov.database.models.Doctor;
import pl.zdrov.database.models.Patient;
import pl.zdrov.database.models.exception.BaseModel;

public class ShowPersonController {
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField surnameTextField;
    @FXML
    private TextField peselTextField;
    @FXML
    private TextField phoneTextField;
    @FXML
    private TextField mailTextField;
    @FXML
    private TextField pwzTextField;
    @FXML
    private TextField specializationTextField;
    @FXML
    private TextField adressTextField;
    @FXML
    private TextField zipTextField;
    @FXML
    private TextField cityTextField;
    @FXML
    private TextField nfzTextField;
    @FXML
    private TextField weightTextField;
    @FXML
    private TextField heightTextField;
    @FXML
    private ImageView image;
    @FXML
    private HBox doctorHBox;
    @FXML
    private HBox patientHBox1;
    @FXML
    private HBox patientHBox2;
    @FXML
    private HBox patientHBox3;



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

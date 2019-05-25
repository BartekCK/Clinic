package pl.zdrov.utilies.exceptions;

import javafx.scene.control.ComboBox;
import pl.zdrov.database.models.Doctor;
import pl.zdrov.database.models.Patient;

/**
 * Klasa sprawdza czy dane zostały wprowadzone prawidłowo
 */
public class CorrectDataCommit
{

    public static void checkDoctor(Doctor doctor) throws ApplicationException {

        if(doctor.getPesel().length() == 11 && doctor.getPwz().length() == 7 && Character.isUpperCase(doctor.getName().charAt(0))
                && Character.isUpperCase(doctor.getSurName().charAt(0)))
        {
            System.out.println("Prawidlowo wprowadzone dane");
        }else
        {
            throw new ApplicationException("Wprowadzone dane są błędne");
        }

    }


    public static void checkWorkHours(ComboBox comboBox1,ComboBox comboBox2, ComboBox comboBox3) throws ApplicationException {
        if(comboBox1.getSelectionModel().isEmpty() || comboBox2.getSelectionModel().isEmpty() || comboBox3.getSelectionModel().isEmpty())
        {
            throw new ApplicationException("Wprowadzone dane są błędne");
        }

    }

    public static void checkPatient(Patient patient) throws ApplicationException {

        if(patient.getPesel().length() == 11 && Character.isUpperCase(patient.getName().charAt(0))
                && Character.isUpperCase(patient.getSurName().charAt(0)))
        {
            System.out.println("Prawidlowo wprowadzone dane");
        }else
        {
            throw new ApplicationException("Wprowadzone dane są błędne");
        }

    }



}

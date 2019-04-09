package pl.zdrov.utilies.exceptions;

import pl.zdrov.database.models.Doctor;

import java.io.IOException;

public class CorrectDataCommit extends Exception{

    private String errorMessage = "Błąd wprowadzenia danych";

    public static void checkDoctor(Doctor doctor) throws IOException {

        if(doctor.getPesel().length() == 11 && doctor.getPwz().length() == 7 && Character.isUpperCase(doctor.getName().charAt(0))
                && Character.isUpperCase(doctor.getSurName().charAt(0)))
        {
            System.out.println("Prawidlowo wprowadzone dane");
        }else
        {
            throw new IOException();
        }


    }

}

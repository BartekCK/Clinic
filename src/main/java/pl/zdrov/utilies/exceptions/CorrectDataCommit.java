package pl.zdrov.utilies.exceptions;

import pl.zdrov.database.models.Doctor;

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

}

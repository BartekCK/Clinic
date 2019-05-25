package pl.zdrov.utilies.converters;

import pl.zdrov.database.models.Registration;
import pl.zdrov.database.modelsFX.RegistrationFx;
import pl.zdrov.utilies.Utils;

/**
 * Klasa opowiada za konwetrowanie obiektów między Registration a RegistrationFx
 */
public class ConvertRegistration {

    public static Registration convertToRegistration(RegistrationFx registrationFx)
    {
        Registration registration = new Registration();
        registration.setId(registrationFx.getId());
        registration.setDoctor(ConverterDoctor.convertToDoctor(registrationFx.getDoctorFx()));
        registration.setPatient(ConverterPatient.convertToPatient(registrationFx.getPatientFx()));
        registration.setAddedDate(Utils.convertToDate(registrationFx.getAddedDate()));
        registration.setTimeFrom(registrationFx.getTimeFrom());
        registration.setTimeTo(registrationFx.getTimeTo());

        return registration;
    }


    public static RegistrationFx convertToRegistrationFx(Registration registration)
    {
        RegistrationFx registrationFx = new RegistrationFx();
        registrationFx.setId(registration.getId());
        registrationFx.setDoctorFx(ConverterDoctor.convertToDoctorFx(registration.getDoctor()));
        registrationFx.setPatientFx(ConverterPatient.convertToPatientFx(registration.getPatient()));
        registrationFx.setAddedDate(Utils.convertToLocalDate(registration.getAddedDate()));
        registrationFx.setTimeFrom(registration.getTimeFrom());
        registrationFx.setTimeTo(registration.getTimeTo());

        return registrationFx;
    }

}

package pl.zdrov.database.modelsFX;

import org.junit.Before;
import org.junit.Test;
import pl.zdrov.database.models.Doctor;
import pl.zdrov.utilies.exceptions.ApplicationException;

import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class RegistrationModelTest {

    RegistrationModel registrationModel;
    @Before
    public void set()
    {
        registrationModel = new RegistrationModel();
        registrationModel.init();
    }

    @Test
    public void checkFreeRegistration() {
        registrationModel.setDoctor(new Doctor());
        registrationModel.checkFreeRegistration(LocalDate.now());
    }

    @Test
    public void polishDays() {
        assertNull(registrationModel.polishDays("Brak tekstu"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void isDoctorInRegistration() {
        RegistrationModel.isDoctorInRegistration("a",new Doctor());
    }
}
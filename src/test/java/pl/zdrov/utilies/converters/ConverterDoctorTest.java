package pl.zdrov.utilies.converters;

import org.junit.Test;
import pl.zdrov.database.models.Doctor;

import static org.junit.Assert.*;

public class ConverterDoctorTest {

    @Test(expected = NullPointerException.class)
    public void convertToDoctor() {
        ConverterDoctor.convertToDoctor(null);
    }

    @Test(expected = NullPointerException.class)
    public void convertToDoctorFx() {
        ConverterDoctor.convertToDoctorFx(new Doctor());
    }
}
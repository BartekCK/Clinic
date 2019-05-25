package pl.zdrov.database.models;

import javafx.collections.ObservableList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.zdrov.database.DbConnector;
import pl.zdrov.database.modelsFX.SpecializationModel;

import static org.junit.Assert.*;

public class DoctorTest {

    private Doctor doctor;



    @Before
    public void setUp() {
        DbConnector.initDatabase();
        doctor = new Doctor();
    }

    @Test
    public void getPwz() {
        doctor.setPwz("1234567");
        assertEquals(7,doctor.getPwz().length());
    }

    @Test
    public void getSpecialization() {
        ObservableList<Specialization> temp = SpecializationModel.returnAllSpecialization();
        doctor.setSpecialization(temp.get(1));
        Assert.assertNotNull(doctor.getSpecialization());
    }
}
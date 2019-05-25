package pl.zdrov.database.modelsFX;

import javafx.collections.ObservableList;
import org.junit.Before;
import org.junit.Test;
import pl.zdrov.database.DbConnector;
import pl.zdrov.database.models.Doctor;
import pl.zdrov.utilies.exceptions.ApplicationException;

import static org.junit.Assert.*;

public class DoctorModelTest {

    DoctorModel doctorModel;
    @Before
    public void set()
    {
        DbConnector.initDatabase();
        doctorModel = new DoctorModel();
        doctorModel.init();
    }

    @Test(expected = ApplicationException.class)
    public void saveDoctorInDataBase() throws ApplicationException {

        doctorModel.saveDoctorInDataBase(null);
    }

    @Test
    public void nameSearch() {
        ObservableList<DoctorFx> temp = doctorModel.nameSearch("Co kolwiek");
        assertTrue(temp.isEmpty());
    }

    @Test
    public void surnameSearch() {
        ObservableList<DoctorFx> temp = doctorModel.nameSearch("Co kolwiek");
        assertTrue(temp.isEmpty());
    }

    @Test(expected = NullPointerException.class)
    public void deleteDoctorInDataBase() {
        doctorModel.deleteDoctorInDataBase();
    }

    @Test
    public void setDoctor() {
        doctorModel.setDoctor(null);
        assertNull(doctorModel.getDoctor());
    }
}
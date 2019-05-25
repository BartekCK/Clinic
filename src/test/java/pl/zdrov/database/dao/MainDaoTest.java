package pl.zdrov.database.dao;

import org.junit.Before;
import org.junit.Test;
import pl.zdrov.database.DbConnector;
import pl.zdrov.database.models.Doctor;
import pl.zdrov.database.models.Patient;
import pl.zdrov.utilies.exceptions.ApplicationException;

import static org.junit.Assert.*;

public class MainDaoTest {


    @Before
    public void setUp() {
        DbConnector.initDatabase();
    }
    @Test(expected = ApplicationException.class)
    public void creatOrUpdate() throws ApplicationException {
        DoctorDao doctorDao = new DoctorDao();
        doctorDao.creatOrUpdate(null);
    }

    @Test(expected = ApplicationException.class)
    public void creatOrUpdate2() throws ApplicationException {
        DoctorDao doctorDao = new DoctorDao();
        doctorDao.creatOrUpdate(new Doctor());
    }

    @Test
    public void delete() throws ApplicationException {
        Patient patient = new Patient();
        PatientDao patientDao = new PatientDao();
        patientDao.delete(new Patient());
    }

}
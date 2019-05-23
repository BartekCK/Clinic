package pl.zdrov.database.dao;

import org.junit.Test;
import pl.zdrov.database.models.Doctor;
import pl.zdrov.database.models.Patient;
import pl.zdrov.utilies.exceptions.ApplicationException;

import static org.junit.Assert.*;

public class MainDaoTest {

    @Test(expected = NullPointerException.class)
    public void creatOrUpdate() {
        DoctorDao doctorDao = new DoctorDao();
        try {
            doctorDao.creatOrUpdate(null);
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
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
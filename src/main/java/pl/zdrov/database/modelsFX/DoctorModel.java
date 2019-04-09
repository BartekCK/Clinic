package pl.zdrov.database.modelsFX;

import pl.zdrov.database.DbConnector;
import pl.zdrov.database.dao.DoctorDao;
import pl.zdrov.database.models.Doctor;
import pl.zdrov.utilies.DialogCatch;
import pl.zdrov.utilies.exceptions.ApplicationException;

import java.awt.*;

public class DoctorModel {

    public void saveDoctorInDataBase(Doctor doctor) throws ApplicationException {
        DoctorDao doctorDao = new DoctorDao();

        doctorDao.creatOrUpdate(doctor);
        DbConnector.closeConnectionSource();
    }
}

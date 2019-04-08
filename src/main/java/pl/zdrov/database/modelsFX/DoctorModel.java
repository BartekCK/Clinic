package pl.zdrov.database.modelsFX;

import pl.zdrov.database.DbConnector;
import pl.zdrov.database.dao.DoctorDao;
import pl.zdrov.database.models.Doctor;
import pl.zdrov.utilies.exceptions.ApplicationException;

public class DoctorModel {

    public void saveDoctorInDataBase(Doctor doctor)
    {
        DoctorDao doctorDao = new DoctorDao();
        try {
            doctorDao.creatOrUpdate(doctor);
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
        DbConnector.closeConnectionSource();
    }
}

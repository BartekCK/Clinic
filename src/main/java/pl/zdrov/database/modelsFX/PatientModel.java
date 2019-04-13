package pl.zdrov.database.modelsFX;

import pl.zdrov.database.DbConnector;
import pl.zdrov.database.dao.PatientDao;
import pl.zdrov.database.models.Patient;
import pl.zdrov.utilies.exceptions.ApplicationException;

public class PatientModel {

    public void savePatientInDataBase(Patient patient) throws ApplicationException {
        PatientDao patientDao = new PatientDao();

        patientDao.creatOrUpdate(patient);
        DbConnector.closeConnectionSource();
    }
}

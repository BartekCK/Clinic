package pl.zdrov.database.modelsFX;

import org.junit.Before;
import org.junit.Test;
import pl.zdrov.utilies.exceptions.ApplicationException;

import static org.junit.Assert.*;

public class PatientModelTest {

    PatientModel patientModel;
    @Before
    public void first()
    {
        patientModel = new PatientModel();
    }

    @Test(expected = ApplicationException.class)
    public void savePatientInDataBase() throws ApplicationException {
        patientModel.savePatientInDataBase(null);
    }


    @Test
    public void init() {
        patientModel.init();
    }
}
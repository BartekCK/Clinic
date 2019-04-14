package pl.zdrov.database.modelsFX;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.zdrov.database.DbConnector;
import pl.zdrov.database.dao.PatientDao;
import pl.zdrov.database.models.Patient;
import pl.zdrov.utilies.DialogCatch;
import pl.zdrov.utilies.converters.ConverterPatient;
import pl.zdrov.utilies.exceptions.ApplicationException;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class PatientModel {

    private ObservableList<PatientFx> patientFxShowObservableList = FXCollections.observableArrayList();
    private ObservableList<PatientFx> patientFxShowObservableListSearch = FXCollections.observableArrayList();
    private List<Patient> patientList = new LinkedList<>();
    private PatientFx patientFx;
    private Patient patient;

    public void savePatientInDataBase(Patient patient) throws ApplicationException {
        PatientDao patientDao = new PatientDao();

        patientDao.creatOrUpdate(patient);
        DbConnector.closeConnectionSource();
    }

    public void pullPatientFromDataBase()
    {
        try {
            PatientDao patientDao = new PatientDao();
            patientList.clear();
            patientList = patientDao.queryForAll(Patient.class);
        } catch (ApplicationException e) {
            DialogCatch.errorCommitDoctor(e.getMessage());
        }
        DbConnector.closeConnectionSource();
    }
    public void init()
    {
        pullPatientFromDataBase();
        patientFxShowObservableList.clear();
        patientFxShowObservableListSearch.clear();
        patientList.forEach(patient -> {
            PatientFx patientFx = ConverterPatient.convertToPatientFx(patient);
            patientFxShowObservableList.add(patientFx);
        });

    }


    public  ObservableList<PatientFx> nameSearch(String compare)
    {
        patientFxShowObservableListSearch.clear();
        patientFxShowObservableList.forEach(patientFx -> {
            if (compare.equals(patientFx.getName()))
            {
                patientFxShowObservableListSearch.add(patientFx);
            }
        });

        return patientFxShowObservableListSearch;
    }

    public  ObservableList<PatientFx> surnameSearch(String compare)
    {
        patientFxShowObservableListSearch.clear();
        patientFxShowObservableList.forEach(patientFx -> {
            if (compare.equals(patientFx.getSurName()))
            {
                patientFxShowObservableListSearch.add(patientFx);
            }
        });

        return patientFxShowObservableListSearch;
    }

    public  ObservableList<PatientFx> peselSearch(String compare)
    {
        patientFxShowObservableListSearch.clear();
        patientFxShowObservableList.forEach(patientFx -> {
            if (compare.equals(patientFx.getPesel()))
            {
                patientFxShowObservableListSearch.add(patientFx);
            }
        });

        return patientFxShowObservableListSearch;
    }



    public void deletePatientInDataBase()
    {

        try {
            PatientDao patientDao = new PatientDao();
            this.patient = ConverterPatient.convertToPatient(this.patientFx);
            patientDao.delete(this.patient);
            DbConnector.closeConnectionSource();
            init();
        } catch (ApplicationException e) {
            DialogCatch.errorCommitDoctor(e.getMessage());
        }
    }




    public ObservableList<PatientFx> getPatientFxShowObservableList() {
        return patientFxShowObservableList;
    }

    public void setPatientFxShowObservableList(ObservableList<PatientFx> patientFxShowObservableList) {
        this.patientFxShowObservableList = patientFxShowObservableList;
    }

    public ObservableList<PatientFx> getPatientFxShowObservableListSearch() {
        return patientFxShowObservableListSearch;
    }

    public void setPatientFxShowObservableListSearch(ObservableList<PatientFx> patientFxShowObservableListSearch) {
        this.patientFxShowObservableListSearch = patientFxShowObservableListSearch;
    }

    public PatientFx getPatientFx() {
        return patientFx;
    }

    public void setPatientFx(PatientFx patientFx) {
        this.patientFx = patientFx;
    }
}

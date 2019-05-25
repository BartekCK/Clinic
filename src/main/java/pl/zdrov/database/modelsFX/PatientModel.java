package pl.zdrov.database.modelsFX;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.zdrov.database.DbConnector;
import pl.zdrov.database.dao.PatientDao;
import pl.zdrov.database.models.Patient;
import pl.zdrov.utilies.DialogCatch;
import pl.zdrov.utilies.converters.ConverterPatient;
import pl.zdrov.utilies.exceptions.ApplicationException;
import java.util.LinkedList;
import java.util.List;

/**
 * Klasa zarządza akcjami na obiektach typu Doctor
 */
public class PatientModel {

    private ObservableList<PatientFx> patientFxShowObservableList = FXCollections.observableArrayList();
    private ObservableList<PatientFx> patientFxShowObservableListSearch = FXCollections.observableArrayList();
    private List<Patient> patientList = new LinkedList<>();
    private PatientFx patientFx;
    private Patient patient;

    /**
     * Zapis pacjenta do bazy danych
     * @param patient pacjent kotry ma zostac zapisany
     * @throws ApplicationException w przypadku wystąpienia błędu
     */
    public void savePatientInDataBase(Patient patient) throws ApplicationException {
        PatientDao patientDao = new PatientDao();

        patientDao.creatOrUpdate(patient);
        DbConnector.closeConnectionSource();
    }

    /**
     * Pobranie wszystkich pacjentów z bazy danych
     */
    private void pullPatientFromDataBase()
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

    /**
     * Inicjowanie pracy
     */
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

    /**
     * Wyszukiwanie pacjenta za pomocą imienia
     * @param compare ciąg znakow do wyszukania
     * @return lisę uzyskanych wyników
     */
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

    /**
     * Wyszukiwanie pacjenta za pomocą nazwiska
     * @param compare ciąg znakow do wyszukania
     * @return lisę uzyskanych wyników
     */
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

    /**
     * Wyszukiwanie pacjenta za pomocą peselu
     * @param compare ciąg znakow do wyszukania
     * @return lisę uzyskanych wyników
     */
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


    /**
     * Usuwa wybranego pacjenta z bazy danych
     */
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

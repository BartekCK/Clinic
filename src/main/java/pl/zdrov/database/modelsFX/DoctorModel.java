package pl.zdrov.database.modelsFX;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.zdrov.database.DbConnector;
import pl.zdrov.database.dao.DoctorDao;
import pl.zdrov.database.dao.WorkHoursDao;
import pl.zdrov.database.models.Doctor;
import pl.zdrov.database.models.Specialization;
import pl.zdrov.utilies.DialogCatch;
import pl.zdrov.utilies.converters.ConverterDoctor;
import pl.zdrov.utilies.exceptions.ApplicationException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Klasa zarządza akcjami na obiektach typu Doctor
 */
public class DoctorModel {

    private ObservableList<DoctorFx> doctorFxShowObservableList = FXCollections.observableArrayList();
    private ObservableList<DoctorFx> doctorFxShowObservableListSearch = FXCollections.observableArrayList();
    private List <Doctor> doctorList = new LinkedList<>();

    private DoctorFx doctorFx;
    private Doctor doctor;


    /**
     * Zapis lekarza do bazy danych
     * @param doctor lekarz który ma zostać zapisany
     * @throws ApplicationException w przypadku wystąpienia błędu
     */
    public void saveDoctorInDataBase(Doctor doctor) throws ApplicationException {
        DoctorDao doctorDao = new DoctorDao();

        doctorDao.creatOrUpdate(doctor);
        DbConnector.closeConnectionSource();
    }

    /**
     * Pobranie wszystkich lekarzy z bazy danych
     */
    private void pullDoctorFromDataBase()
    {
        try {
            DoctorDao doctorDao = new DoctorDao();
            doctorList.clear();
            doctorList = doctorDao.queryForAll(Doctor.class);
        } catch (ApplicationException e) {
            DialogCatch.errorCommitDoctor(e.getMessage());
        }
        DbConnector.closeConnectionSource();
    }

    /**
     * Metoda inicjuje klasę wartoścami początkowymi
     */
    public void init()
    {
        pullDoctorFromDataBase();
        doctorFxShowObservableList.clear();
        doctorFxShowObservableListSearch.clear();
        doctorList.forEach(doctor -> {
            DoctorFx doctorFx = ConverterDoctor.convertToDoctorFx(doctor);
            doctorFxShowObservableList.add(doctorFx);
        });

    }


    public ObservableList<DoctorFx> getDoctorFxShowObservableList() {
        return doctorFxShowObservableList;
    }

    /**
     * Wyszukiwanie lekarza za pomocą imienia
     * @param compare ciąg znakow do wyszukania
     * @return lisę uzyskanych wyników
     */
    public  ObservableList<DoctorFx> nameSearch(String compare)
    {
        doctorFxShowObservableListSearch.clear();
        doctorFxShowObservableList.forEach(doctorFx -> {
            if (compare.equals(doctorFx.getName()))
            {
                doctorFxShowObservableListSearch.add(doctorFx);
            }
        });

        return doctorFxShowObservableListSearch;
    }

    /**
     * Wyszukiwanie lekarza za pomocą nazwiska
     * @param compare ciąg znakow do wyszukania
     * @return lisę uzyskanych wyników
     */
    public  ObservableList<DoctorFx> surnameSearch(String compare)
    {
        doctorFxShowObservableListSearch.clear();
        doctorFxShowObservableList.forEach(doctorFx -> {
            if (compare.equals(doctorFx.getSurName()))
            {
                doctorFxShowObservableListSearch.add(doctorFx);
            }
        });

        return doctorFxShowObservableListSearch;
    }

    /**
     * Wyszukiwanie lekarza za pomocą nazwy specializacji
     * @param specialization rodzaj specializacji
     * @return lisę uzyskanych wyników
     */
    public  ObservableList<DoctorFx> specializationSearch(Specialization specialization)
    {
        doctorFxShowObservableListSearch.clear();
        doctorFxShowObservableList.forEach(doctorFx -> {
            if (specialization.getId() == doctorFx.getSpecializationFx().getId())
            {
                doctorFxShowObservableListSearch.add(doctorFx);
            }
        });

        return doctorFxShowObservableListSearch;
    }

    /**
     * Usuwa wybranego lekarza z bazy danych
     */
    public void deleteDoctorInDataBase()
    {

        try {
            DoctorDao doctorDao = new DoctorDao();
            WorkHoursDao workHoursDao = new WorkHoursDao();
            this.doctor = ConverterDoctor.convertToDoctor(this.doctorFx);
            workHoursDao.deleteByColumnName("DOCTOR_ID",this.doctor.getId());
            doctorDao.delete(doctor);
            DbConnector.closeConnectionSource();
            init();
        } catch (ApplicationException e) {
            DialogCatch.errorCommitDoctor(e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public DoctorFx getDoctorFx() {
        return doctorFx;
    }

    public void setDoctorFx(DoctorFx doctorFx) {
        this.doctorFx = doctorFx;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}

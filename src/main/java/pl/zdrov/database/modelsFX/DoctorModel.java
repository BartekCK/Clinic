package pl.zdrov.database.modelsFX;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.zdrov.database.DbConnector;
import pl.zdrov.database.dao.DoctorDao;
import pl.zdrov.database.models.Doctor;
import pl.zdrov.utilies.DialogCatch;
import pl.zdrov.utilies.converters.ConverterDoctor;
import pl.zdrov.utilies.exceptions.ApplicationException;


import java.util.LinkedList;
import java.util.List;

public class DoctorModel {

    private ObservableList<DoctorFx> doctorFxShowObservableList = FXCollections.observableArrayList();
    private List <Doctor> doctorList = new LinkedList<>();


    public void saveDoctorInDataBase(Doctor doctor) throws ApplicationException {
        DoctorDao doctorDao = new DoctorDao();

        doctorDao.creatOrUpdate(doctor);
        DbConnector.closeConnectionSource();
    }

    public void pullDoctorFromDataBase()
    {
        try {
            DoctorDao doctorDao = new DoctorDao();
            doctorList = doctorDao.queryForAll(Doctor.class);
        } catch (ApplicationException e) {
            DialogCatch.errorCommitDoctor(e.getMessage());
        }
        DbConnector.closeConnectionSource();
    }
    public void init()
    {
        pullDoctorFromDataBase();
        doctorFxShowObservableList.clear();
        doctorList.forEach(doctor -> {
            DoctorFx doctorFx = ConverterDoctor.convertToDoctorFx(doctor);
            System.out.println(doctorFx);
            doctorFxShowObservableList.add(doctorFx);
        });

    }


    public ObservableList<DoctorFx> getDoctorFxShowObservableList() {
        return doctorFxShowObservableList;
    }

}

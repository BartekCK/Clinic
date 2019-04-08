package pl.zdrov.database.modelsFX;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.zdrov.database.DbConnector;
import pl.zdrov.database.dao.SpecializationDao;
import pl.zdrov.database.models.Specialization;
import pl.zdrov.utilies.exceptions.ApplicationException;

import java.util.List;

public class SpecializationFx {
    private static Specialization specialization;
    private static final String [] spec = {"Alergologia (0731)","Angiologia (0732)","Diabetologia (0740)","Endokrynologia (0741)","Farmakologia kliniczna (0742)","Gastroenterologia (0743)","Hematologia (0745)",
            "Kardiologia dziecieca (0762)","Medycyna paliatywna (0750)","Medycyna sportowa (0751)","Nefrologia (0752)","Neurologia dziecieca (0763)","Reumatologia (0757)"
    };

    public static void setSpecialization() {

        try {
            SpecializationDao specializationDao = new SpecializationDao();
            for (String s : spec) {
                specialization = new Specialization(s);
                specializationDao.creatOrUpdate(specialization);
            }
            DbConnector.closeConnectionSource();
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }

    public static ObservableList returnAllSpecialization() {
        try {
            SpecializationDao specializationDao = new SpecializationDao();
            return FXCollections.observableList(specializationDao.queryForAll(Specialization.class));
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
        return null;
    }
}

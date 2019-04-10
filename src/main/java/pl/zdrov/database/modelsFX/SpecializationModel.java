package pl.zdrov.database.modelsFX;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.zdrov.database.DbConnector;
import pl.zdrov.database.dao.SpecializationDao;
import pl.zdrov.database.models.Specialization;
import pl.zdrov.utilies.exceptions.ApplicationException;

public class SpecializationModel {

    public static void setSpecialization() {
        try {
            SpecializationDao specializationDao = new SpecializationDao();

            for (String s : Specialization.getSpec()) {
                Specialization specialization = new Specialization(s);
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

package pl.zdrov.database.modelsFX;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.zdrov.database.DbConnector;
import pl.zdrov.database.dao.SpecializationDao;
import pl.zdrov.database.models.Specialization;
import pl.zdrov.utilies.exceptions.ApplicationException;
import java.sql.SQLException;
/**
 * Klasa zarządza akcjami na obiektach typu Specialization
 */
public class SpecializationModel {

    private static ObservableList<Specialization> temp = FXCollections.observableArrayList();

    /**
     * Zapisuje specializacje do bazy danych
     */
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

    /**
     * Zwraca wszystkie specializacje
     * @return wszystkie specializacje
     */
    public static ObservableList returnAllSpecialization() {
        try {
            SpecializationDao specializationDao = new SpecializationDao();
            temp.clear();
            temp = FXCollections.observableList(specializationDao.queryForAll(Specialization.class));
            DbConnector.closeConnectionSource();
            return temp;
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Sprawdza czy specializacje zostały zapisane do bazy danych
     * @return true jeżeli baza jest pusta
     */
    public static boolean isDataBaseSpecializationEmpty()
    {
        SpecializationDao specializationDao = new SpecializationDao();
        try {
            return specializationDao.isEmptySpecializationDataBase();
        } catch (ApplicationException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }


}

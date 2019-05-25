package pl.zdrov.database.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import pl.zdrov.database.models.Doctor;
import pl.zdrov.database.models.Registration;
import pl.zdrov.database.models.expands.BaseModel;
import pl.zdrov.utilies.exceptions.ApplicationException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;


/**
 * Klasa odpowiedzialna za operacje na bazie danych dla lekarzy
 */
public class RegistrationDao extends MainDao{
    public RegistrationDao() {
        super();
    }

    /**
     * Zwraca listę zarejestrowanych osób dla określonego lekarza
     * @param columName1 nazwa pierwszej kolumny
     * @param doctor wybrany doktor
     * @param columnName2 nazwa drugiej kolumnu
     * @param date data
     * @return listę obiektów określonych w wymaganiach
     * @throws ApplicationException błąd działania aplikacji
     * @throws SQLException błąd działania aplikacji
     */
    public List pullByColumnName(String columName1, Doctor doctor, String columnName2, Date date) throws ApplicationException, SQLException {

        Dao<Registration, Object> dao = getDao(Registration.class);
        PreparedQuery<Registration> temp = dao.queryBuilder().where().eq(columName1, doctor).and().eq(columnName2,date).prepare();
        return dao.query(temp);
    }

    /**
     * Sprawdza czy wybrany lekarz lub doktor ma lub miał zarejestrowane wizyty
     * @param columnName nazwa kolumny w wybranej tabeli
     * @param baseModel lekarz lub pacjent
     * @return true jeżeli obiekt występuje w rejestracji, false w przeciwnym wypadku
     * @throws ApplicationException błąd działania aplikacji
     * @throws SQLException błąd działania aplikacji
     */
    public  boolean isObjectInRegistrationCheck(String columnName, BaseModel baseModel) throws ApplicationException, SQLException {
        Dao<Registration, Object> dao = getDao(Registration.class);
        PreparedQuery<Registration> temp = dao.queryBuilder().where().eq(columnName, baseModel.getId()).prepare();
        return !dao.query(temp).isEmpty();
    }


}
package pl.zdrov.database.dao;

import com.j256.ormlite.dao.Dao;
import pl.zdrov.database.models.Specialization;
import pl.zdrov.utilies.exceptions.ApplicationException;
import java.sql.SQLException;
import java.util.List;

/**
 * Klasa odpowiedzialna za operacje na bazie danych dla specializacji lekarzy
 */
public class SpecializationDao extends MainDao {
    public SpecializationDao() {
        super();
    }

    /**
     * Sprawdza czy specializacje zostały załadowane do bazy danych czy nie
     * @return true jeżeli nie zostały załadowane, false w przeciwnym wypadku
     * @throws ApplicationException błąd działania aplikacji
     * @throws SQLException błąd działania aplikacji
     */
    public boolean isEmptySpecializationDataBase() throws ApplicationException, SQLException {
        Dao<Specialization, Object> dao = getDao(Specialization.class);
        List<Specialization> temp = dao.queryForAll();
        return temp.isEmpty();
    }
}

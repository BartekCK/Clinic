package pl.zdrov.database.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.PreparedQuery;
import pl.zdrov.database.models.WorkHours;
import pl.zdrov.utilies.exceptions.ApplicationException;
import java.sql.SQLException;
import java.util.List;

/**
 * Klasa odpowiedzialna za operacje na bazie danych dla godzin pracy
 */
public class WorkHoursDao extends MainDao{
    public WorkHoursDao() {
        super();
    }

    /**
     * Usuwa godziny pracy dla usuwanego lekarza
     * @param columName nazwa kolumny
     * @param id lekarza
     * @throws ApplicationException błąd działania aplikacji
     * @throws SQLException błąd działania aplikacji
     */
    public void deleteByColumnName(String columName, int id) throws ApplicationException, SQLException {
        Dao<WorkHours, Object> dao = getDao(WorkHours.class);
        DeleteBuilder<WorkHours, Object> deleteBuilder = dao.deleteBuilder();
        deleteBuilder.where().eq(columName, id);
        dao.delete(deleteBuilder.prepare());
    }

    /**
     *Pobiera godziny pracy dla wskaznego lekarza
     * @param columName nazwa kolumny
     * @param id lekarza
     * @return lista obiektów
     * @throws ApplicationException błąd działania aplikacji
     * @throws SQLException błąd działania aplikacji
     */
    public List pullByColumnName(String columName, int id) throws ApplicationException, SQLException {

        Dao<WorkHours, Object> dao = getDao(WorkHours.class);
        PreparedQuery<WorkHours> temp = dao.queryBuilder().where().eq(columName, id).prepare();
        return dao.query(temp);
    }
}
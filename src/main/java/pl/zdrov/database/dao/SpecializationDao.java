package pl.zdrov.database.dao;

import com.j256.ormlite.dao.Dao;
import pl.zdrov.database.models.Registration;
import pl.zdrov.database.models.Specialization;
import pl.zdrov.utilies.exceptions.ApplicationException;

import java.sql.SQLException;
import java.util.List;

public class SpecializationDao extends MainDao {
    public SpecializationDao() {
        super();
    }

    public boolean isEmptySpecializationDataBase() throws ApplicationException, SQLException {
        Dao<Specialization, Object> dao = getDao(Specialization.class);
        List<Specialization> temp = dao.queryForAll();
        return temp.isEmpty();
    }
}

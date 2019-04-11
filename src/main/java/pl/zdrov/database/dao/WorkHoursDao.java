package pl.zdrov.database.dao;


import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import pl.zdrov.database.models.WorkHours;
import pl.zdrov.utilies.exceptions.ApplicationException;

import java.sql.SQLException;
import java.util.List;

public class WorkHoursDao extends MainDao{
    public WorkHoursDao() {
        super();
    }

    public void deleteByColumnName(String columName, int id) throws ApplicationException, SQLException {
        Dao<WorkHours, Object> dao = getDao(WorkHours.class);
        DeleteBuilder<WorkHours, Object> deleteBuilder = dao.deleteBuilder();
        deleteBuilder.where().eq(columName, id);
        dao.delete(deleteBuilder.prepare());
    }

    public List pullByColumnName(String columName, int id) throws ApplicationException, SQLException {

        Dao<WorkHours, Object> dao = getDao(WorkHours.class);
        PreparedQuery<WorkHours> temp = dao.queryBuilder().where().eq(columName, id).prepare();
        return dao.query(temp);
    }
}
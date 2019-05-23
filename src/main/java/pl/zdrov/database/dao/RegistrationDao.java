package pl.zdrov.database.dao;


import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import pl.zdrov.database.models.Doctor;
import pl.zdrov.database.models.Registration;
import pl.zdrov.database.models.exception.BaseModel;
import pl.zdrov.utilies.exceptions.ApplicationException;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class RegistrationDao extends MainDao{
    public RegistrationDao() {
        super();
    }

    public List pullByColumnName(String columName1, Doctor doctor, String columnName2, Date date) throws ApplicationException, SQLException {

        Dao<Registration, Object> dao = getDao(Registration.class);
        PreparedQuery<Registration> temp = dao.queryBuilder().where().eq(columName1, doctor).and().eq(columnName2,date).prepare();
        return dao.query(temp);
    }

    public  boolean isObjectInRegistrationCheck(String columnName, BaseModel baseModel) throws ApplicationException, SQLException {
        Dao<Registration, Object> dao = getDao(Registration.class);
        PreparedQuery<Registration> temp = dao.queryBuilder().where().eq(columnName, baseModel.getId()).prepare();
        return !dao.query(temp).isEmpty();
    }


}
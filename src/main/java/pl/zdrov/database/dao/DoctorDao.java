package pl.zdrov.database.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.PreparedUpdate;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.stmt.Where;
import pl.zdrov.database.models.Doctor;
import pl.zdrov.utilies.exceptions.ApplicationException;

import java.sql.SQLException;

public class DoctorDao extends MainDao{
    public DoctorDao() {
        super();
    }


}

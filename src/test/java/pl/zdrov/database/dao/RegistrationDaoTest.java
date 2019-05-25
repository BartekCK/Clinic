package pl.zdrov.database.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import org.junit.Before;
import org.junit.Test;
import pl.zdrov.database.DbConnector;
import pl.zdrov.database.models.Doctor;
import pl.zdrov.database.models.Registration;
import pl.zdrov.utilies.exceptions.ApplicationException;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class RegistrationDaoTest {

    @Before
    public void setUp() {
        DbConnector.initDatabase();
    }

    @Test(expected = IllegalArgumentException.class)
    public void pullByColumnName() throws SQLException, ApplicationException {

        RegistrationDao registrationDao = new RegistrationDao();
        List<Registration> aa = registrationDao.pullByColumnName("Brak nazw", new Doctor(), "brak", new Date());


    }

    @Test
    public void isObjectInRegistrationCheck() throws ApplicationException, SQLException {
        RegistrationDao registrationDao = new RegistrationDao();
        Dao<Registration, Object> dao = registrationDao.getDao(Registration.class);
        PreparedQuery<Registration> temp = dao.queryBuilder().where().eq("DOCTOR_ID", 0).prepare();
        assertTrue(dao.query(temp).isEmpty()


        );
    }
}
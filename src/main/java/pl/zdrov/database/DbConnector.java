package pl.zdrov.database;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import pl.zdrov.database.models.Doctor;
import pl.zdrov.database.models.Patient;
import pl.zdrov.database.models.Registration;
import pl.zdrov.database.models.WorkHours;

import java.io.IOException;
import java.sql.SQLException;

public class DbConnector {

    private static final Logger LOGGER = LoggerFactory.getLogger(DbConnector.class);
    private static final String JDBC_DRIVER_HD = "jdbc:sqlite:clinic.db";
//    private static final String USER = "admin";
//    private static final String PASS = "admin";

    private static ConnectionSource connectionSource;

    public static void initDatabase(){
        createConnectionSource();
        dropTable();
        createTable();
        closeConnectionSource();
    }

    private static void createConnectionSource(){
        try {
            connectionSource = new JdbcConnectionSource(JDBC_DRIVER_HD);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    public static ConnectionSource getConnectionSource(){
        if(connectionSource == null){
            createConnectionSource();
        }
        return connectionSource;
    }

    public static void closeConnectionSource(){
        if(connectionSource!=null) try {
            connectionSource.close();
        } catch (IOException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    private static void createTable(){
        try {
            TableUtils.createTableIfNotExists(connectionSource, Doctor.class);
            TableUtils.createTableIfNotExists(connectionSource, Patient.class);
            TableUtils.createTableIfNotExists(connectionSource, WorkHours.class);
            TableUtils.createTableIfNotExists(connectionSource, Registration.class);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    private  static  void  dropTable(){
        try {
            TableUtils.dropTable(connectionSource, Doctor.class,true);
            TableUtils.dropTable(connectionSource, Patient.class,true);
            TableUtils.dropTable(connectionSource, WorkHours.class,true);
            TableUtils.dropTable(connectionSource, Registration.class,true);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
    }
}

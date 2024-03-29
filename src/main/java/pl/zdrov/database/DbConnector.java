package pl.zdrov.database;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import pl.zdrov.database.models.*;
import pl.zdrov.database.modelsFX.SpecializationModel;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Połączenie z bazą danych
 */
public class DbConnector {

    private static final Logger LOGGER = LoggerFactory.getLogger(DbConnector.class);
    private static final String JDBC_DRIVER_HD = "jdbc:sqlite:clinic.db";
//    private static final String USER = "admin";
//    private static final String PASS = "admin";

    private static ConnectionSource connectionSource;

    /**
     * Zbior najważniejszych metod w jednej
     */
    public static void initDatabase(){
        createConnectionSource();
        //dropTable();
        createTable();
        loadData();
        closeConnectionSource();
    }

    /**
     * Tworzenie nowego połączenia
     */
    private static void createConnectionSource(){
        try {
            connectionSource = new JdbcConnectionSource(JDBC_DRIVER_HD);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    /**
     * Geter od połączenia
     * @return aktywne połączenie
     */
    public static ConnectionSource getConnectionSource(){
        if(connectionSource == null){
            createConnectionSource();
        }
        return connectionSource;
    }

    /**
     * Zamyka aktywne połączenie z bazą
     */
    public static void closeConnectionSource(){
        if(connectionSource!=null) try {
            connectionSource.close();
        } catch (IOException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    /**
     * Tworzy tabele jeżeli nie istnieją
     */
    private static void createTable(){
        try {
            TableUtils.createTableIfNotExists(connectionSource, Doctor.class);
            TableUtils.createTableIfNotExists(connectionSource, Patient.class);
            TableUtils.createTableIfNotExists(connectionSource, WorkHours.class);
            TableUtils.createTableIfNotExists(connectionSource, Registration.class);
            TableUtils.createTableIfNotExists(connectionSource, Specialization.class);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    /**
     * Usuwa tabele
     */
    private static void dropTable(){
        try {
            TableUtils.dropTable(connectionSource, Doctor.class,true);
            TableUtils.dropTable(connectionSource, Patient.class,true);
            TableUtils.dropTable(connectionSource, WorkHours.class,true);
            TableUtils.dropTable(connectionSource, Registration.class,true);
            TableUtils.dropTable(connectionSource, Specialization.class,true);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    /**
     * Ładuje program danymi początkowymi
     */
    private static void loadData()
    {
        if(SpecializationModel.isDataBaseSpecializationEmpty())
            SpecializationModel.setSpecialization();
    }
}

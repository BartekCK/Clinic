package pl.zdrov.database.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import pl.zdrov.database.DbConnector;
import pl.zdrov.database.models.expands.BaseModel;
import pl.zdrov.utilies.exceptions.ApplicationException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Klasa odpowiedzialna za operacje na bazie danych
 */
public abstract class MainDao {
    /**
     * Wyświetlanie stowosnych komunikatów dotyczących zachowania bazy
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MainDao.class);

    /**
     * Przechowywanie referencji do aktywnego połączenia z bazą
     */
    protected final ConnectionSource connectionSource;

    /**
     * Tworzenie połączenia z bazą
     */
    public MainDao() {
        this.connectionSource = DbConnector.getConnectionSource();
    }

    /**
     * Tworzy nowy wpis do wskazanej tabeli
     * @param baseModel odpowiada za klasę, która ma zostać zapisana
     * @param <T> BaseModel
     * @param <I> object
     * @throws ApplicationException błąd działania aplikacji
     */
    public <T extends BaseModel, I> void creatOrUpdate(BaseModel baseModel) throws ApplicationException {
        if(baseModel == null)
            throw new ApplicationException("Obiekt jest nullem");
        Dao<T, I> dao = getDao((Class<T>) baseModel.getClass());
        try {
            dao.createOrUpdate((T) baseModel);
        } catch (SQLException e) {
            LOGGER.warn(e.getCause().getMessage());
            throw new ApplicationException("Błąd wpisu/aktualizacji");
        } finally {
            this.closeDbConnection();
        }
    }

    public <T extends BaseModel, I> void refresh(BaseModel baseModel) throws ApplicationException {
        try {
            Dao<T, I> dao = getDao((Class<T>) baseModel.getClass());
            dao.refresh((T) baseModel);
        } catch (SQLException e) {
            LOGGER.warn(e.getCause().getMessage());
            throw new ApplicationException("Błąd odświeżenia bazy danych");
        } finally {
            this.closeDbConnection();
        }
    }

    /**
     * Służy do usuwania wpisu z bazy danych
     * @param baseModel klasa która implementuje ten interfejs
     * @param <T> baseModel
     * @param <I> Object
     * @throws ApplicationException błąd działania aplikacji
     */
    public <T extends BaseModel, I> void delete(BaseModel baseModel) throws ApplicationException {
        try {
            Dao<T, I> dao = getDao((Class<T>) baseModel.getClass());
            dao.delete((T) baseModel);
        } catch (SQLException e) {
            LOGGER.warn(e.getCause().getMessage());
            throw new ApplicationException("Błąd usunięcia");
        } finally {
            this.closeDbConnection();
        }
    }

    public <T extends BaseModel, I> void deleteById(Class<T> cls, Integer id) throws ApplicationException {
        try {
            Dao<T, I> dao = getDao(cls);
            dao.deleteById((I) id);
        } catch (SQLException e) {
            LOGGER.warn(e.getCause().getMessage());
            throw new ApplicationException("Błąd usunięcia");
        } finally {
            this.closeDbConnection();
        }
    }

    public <T extends BaseModel, I> T findById(Class<T> cls, Integer id) throws ApplicationException {
        try {
            Dao<T, I> dao = getDao(cls);
            return dao.queryForId((I) id);
        } catch (SQLException e) {
            LOGGER.warn(e.getCause().getMessage());
            throw new ApplicationException("Brak wyników");
        } finally {
            this.closeDbConnection();
        }
    }

    /**
     * Służy do pobrania wszystkich wyników z tabeli
     * @param cls typ klasy
     * @param <T> BaseModel
     * @param <I> Object
     * @return listę zawierającą wszystkie obiekty
     * @throws ApplicationException błąd działania aplikacji
     */
    public <T extends BaseModel, I> List<T> queryForAll(Class<T> cls) throws ApplicationException {
        try {
            Dao<T, I> dao = getDao(cls);
            return dao.queryForAll();
        } catch (SQLException e) {
            LOGGER.warn(e.getCause().getMessage());
            throw new ApplicationException("Brak wyników");
        } finally {
            this.closeDbConnection();
        }
    }

    /**
     * Zwraca dao potrzebne do operacji na bazie danych
     * @param cls typ klasy
     * @param <T> BaseModel
     * @param <I> Object
     * @return dao
     * @throws ApplicationException błąd działania aplikacji
     */
    public <T extends BaseModel, I> Dao<T, I> getDao(Class<T> cls) throws ApplicationException {
        try {
            return DaoManager.createDao(connectionSource, cls);
        } catch (SQLException e) {
            LOGGER.warn(e.getCause().getMessage());
            throw new ApplicationException("Błąd zamknięcia");
        } finally {
            this.closeDbConnection();
        }
    }

    public <T extends BaseModel, I> QueryBuilder<T, I> getQueryBuilder(Class<T> cls) throws ApplicationException {
        Dao<T, I> dao = getDao(cls);
        return dao.queryBuilder();
    }

    /**
     * Kończy połączenie z bazą danych
     * @throws ApplicationException błąd działania aplikacji
     */
    private void closeDbConnection() throws ApplicationException {
        try {
            this.connectionSource.close();
        } catch (IOException e) {
            throw new ApplicationException("Błąd zamknięcia");
        }
    }
}

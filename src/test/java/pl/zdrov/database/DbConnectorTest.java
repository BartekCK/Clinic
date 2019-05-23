package pl.zdrov.database;

import org.junit.Test;

import static org.junit.Assert.*;

public class DbConnectorTest {

    @Test
    public void initDatabase() {
        DbConnector.getConnectionSource();
        DbConnector.closeConnectionSource();
    }

}
package pl.zdrov.utilies;

import org.junit.Test;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.Assert.*;

public class UtilsTest {

    @Test
    public void convertToDate() {
        assertNotNull(Utils.convertToDate(LocalDate.now()));
    }

    @Test
    public void convertToLocalDate() {

        Date temp = Utils.convertToDate(LocalDate.now());
        assertEquals(Utils.convertToLocalDate(temp),LocalDate.now());

    }
}
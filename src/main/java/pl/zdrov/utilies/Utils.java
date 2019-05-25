package pl.zdrov.utilies;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Klasa odpowiada za konwertowanie typów dat
 */
public class Utils {

    public static Date convertToDate(LocalDate localDate){
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate convertToLocalDate(Date date){

        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}

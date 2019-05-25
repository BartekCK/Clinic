package pl.zdrov.database.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import pl.zdrov.database.models.expands.BaseModel;
/**
 * Klasa przedstawia ogólną postać godzin pracy
 */
@DatabaseTable(tableName = "WORK_HOURS")
public class WorkHours implements BaseModel {
    /**
     * Identyfikator generowany automatycznie
     */
    @DatabaseField(generatedId = true)
    private int id;
    /**
     * Klucz obcy do lekarza
     */
    @DatabaseField(columnName = "DOCTOR_ID", foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true, canBeNull = false)
    private Doctor doctor;
    /**
     * Dzien pracy
     */
    @DatabaseField(columnName = "DAY", canBeNull = false)
    private String day;
    /**
     * Godzina pracy od
     */
    @DatabaseField(columnName = "TIME_TO",canBeNull = false)
    private String timeFrom;
    /**
     * Godzina pracy do
     */
    @DatabaseField(columnName = "TIME_FROM", canBeNull = false)
    private String timeTo;

    public WorkHours() {
    }

    public WorkHours(String day, String timeFrom, String timeTo) {

        this.day = day;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(String timeFrom) {
        this.timeFrom = timeFrom;
    }

    public String getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(String timeTo) {
        this.timeTo = timeTo;
    }

    @Override
    public String toString() {
        return "WorkHours{" +
                "id=" + id +
                ", doctor=" + doctor +
                ", day='" + day + '\'' +
                ", timeFrom='" + timeFrom + '\'' +
                ", timeTo='" + timeTo + '\'' +
                '}';
    }
}

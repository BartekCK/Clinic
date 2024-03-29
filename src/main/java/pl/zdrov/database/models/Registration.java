package pl.zdrov.database.models;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import pl.zdrov.database.models.expands.BaseModel;

import java.util.Date;

/**
 * Klasa przedstawia ogólną postać rejestracji
 */
@DatabaseTable(tableName = "REGISTRATION")
public class Registration implements BaseModel {

    /**
     * Identyfikator generowany automatycznie
     */
    @DatabaseField(generatedId = true)
    private int id;
    /**
     * Przechowywanie lekarza
     */
    @DatabaseField(columnName = "DOCTOR_ID", foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true, canBeNull = false)
    private Doctor doctor;
    /**
     * Przechowywanie pacjenta
     */
    @DatabaseField(columnName = "PATIENT_ID", foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true, canBeNull = false)
    private Patient patient;
    /**
     * Data wizyty
     */
    @DatabaseField(columnName = "VISIT_DATE",dataType = DataType.DATE_STRING,format = "yyyy-MM-dd")
    private Date addedDate;
    /**
     * Godzina wizyty od
     */
    @DatabaseField(columnName = "TIME_TO",canBeNull = false)
    private String timeFrom;

    /**
     * Godzina wizyty do
     */
    @DatabaseField(columnName = "TIME_FROM", canBeNull = false)
    private String timeTo;




    public Registration() {
    }

    public Registration(Doctor doctor, Patient patient, Date addedDate, String timeFrom, String timeTo) {
        this.doctor = doctor;
        this.patient = patient;
        this.addedDate = addedDate;
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

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
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
        return "Registration{" +
                "id=" + id +
                ", doctor=" + doctor +
                ", patient=" + patient +
                ", addedDate=" + addedDate +
                ", timeFrom='" + timeFrom + '\'' +
                ", timeTo='" + timeTo + '\'' +
                '}';
    }
}

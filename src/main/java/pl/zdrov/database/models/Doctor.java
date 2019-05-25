package pl.zdrov.database.models;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import pl.zdrov.database.models.expands.BaseModel;
import pl.zdrov.database.models.expands.Person;

/**
 * Klasa przedstawia ogólną postać lekarza
 */
@DatabaseTable(tableName = "DOCTORS")
public class Doctor extends Person implements BaseModel {


    /**
     * Numer pwz
     */
    @DatabaseField(columnName = "PWZ",canBeNull = false, width = 7,unique = true)
    private String pwz;

    /**
     * Specializacja lekarza
     */
    @DatabaseField(columnName = "SPECIALIZATION", foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true, canBeNull = false)
    private Specialization specialization;
    /**
     * Godziny pracy
     */
    @ForeignCollectionField(eager = true)
    private ForeignCollection<WorkHours> workHours;

    /**
     * Dostęp do rejestracji
     */
    @ForeignCollectionField
    private ForeignCollection<Registration> registrations;

    public Doctor() {
    }

    public Doctor(String name, String surName, String pesel, String mail, String phone, String pwz, Specialization specialization) {
        this.name = name;
        this.surName = surName;
        this.pesel = pesel;
        this.mail = mail;
        this.phone = phone;
        this.pwz = pwz;
        this.specialization = specialization;
    }

    public String getPwz() {
        return pwz;
    }

    public void setPwz(String pwz) {
        this.pwz = pwz;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public ForeignCollection<WorkHours> getWorkHours() {
        return workHours;
    }

    public void setWorkHours(ForeignCollection<WorkHours> workHours) {
        this.workHours = workHours;
    }

    public ForeignCollection<Registration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(ForeignCollection<Registration> registrations) {
        this.registrations = registrations;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                ", pesel='" + pesel + '\'' +
                ", mail='" + mail + '\'' +
                ", phone='" + phone + '\'' +
                ", pwz='" + pwz + '\'' +
                ", specialization=" + specialization +
                '}';
    }
}

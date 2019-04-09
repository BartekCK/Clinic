package pl.zdrov.database.models;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import pl.zdrov.database.models.exception.BaseModel;

@DatabaseTable(tableName = "DOCTORS")
public class Doctor implements BaseModel {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "NAME", canBeNull = false)
    private String name;

    @DatabaseField(columnName = "SURNAME", canBeNull = false)
    private String surName;

    @DatabaseField(columnName = "PESEL",canBeNull = false, width = 11,unique = true)
    private String pesel;

    @DatabaseField(columnName = "MAIL")
    private String mail;

    @DatabaseField(columnName = "PHONE", width = 9)
    private String phone;

    @DatabaseField(columnName = "PWZ",canBeNull = false, width = 7,unique = true)
    private String pwz;

    @DatabaseField(columnName = "SPECIALIZATION", foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true, canBeNull = false)
    private Specialization specialization;

    @ForeignCollectionField(eager = true)
    private ForeignCollection<WorkHours> workHours;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
}

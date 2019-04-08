package pl.zdrov.database.models;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import pl.zdrov.database.models.exception.BaseModel;

@DatabaseTable(tableName = "DOCTORS")
public class Doctor implements BaseModel {

    @DatabaseField(generatedId = true)
    protected int id;

    @DatabaseField(columnName = "NAME", canBeNull = false)
    protected String name;

    @DatabaseField(columnName = "SURNAME", canBeNull = false)
    protected String surName;

    @DatabaseField(columnName = "PESEL",canBeNull = false, width = 11,unique = true)
    protected String pesel;

    @DatabaseField(columnName = "MAIL")
    protected String mail;

    @DatabaseField(columnName = "PHONE", width = 9)
    protected String phone;

    @DatabaseField(columnName = "PWZ",canBeNull = false, width = 7,unique = true)
    private int pwz;

    @DatabaseField(columnName = "SPECIALIZATION", foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true, canBeNull = false)
    private Specialization specialization;

    @ForeignCollectionField(eager = true)
    private ForeignCollection<WorkHours> workHours;

    @ForeignCollectionField
    private ForeignCollection<Registration> registrations;

    public Doctor() {
    }

    public Doctor(String name, String surName, String pesel, String mail, String phone, int pwz, Specialization specialization) {
        this.name = name;
        this.surName = surName;
        this.pesel = pesel;
        this.mail = mail;
        this.phone = phone;
        this.pwz = pwz;
        this.specialization = specialization;
    }
}

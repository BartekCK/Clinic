package pl.zdrov.database.models;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "DOCTORS")
public class Doctor {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "NAME", canBeNull = false)
    private String name;

    @DatabaseField(columnName = "SURNAME", canBeNull = false)
    private String surName;

    @DatabaseField(columnName = "PESEL",canBeNull = false, width = 11,unique = true)
    private String pesel;

    @DatabaseField(columnName = "PWZ",canBeNull = false, width = 7,unique = true)
    private int pwz;

    @DatabaseField(columnName = "SPECIALIZATION", canBeNull = false)
    private String specialization;

    @DatabaseField(columnName = "MAIL")
    private String mail;

    @DatabaseField(columnName = "PHONE", width = 9)
    private String phone;

    @ForeignCollectionField(eager = true)
    private ForeignCollection<WorkHours> workHours;

}

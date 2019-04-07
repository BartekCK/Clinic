package pl.zdrov.database.models;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import pl.zdrov.database.models.exception.BaseModel;

@DatabaseTable(tableName = "PATIENTS")
public class Patient implements BaseModel {

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

    @DatabaseField(columnName = "ADRESS", canBeNull = false)
    private String address;

    @DatabaseField(columnName = "CITY", canBeNull = false)
    private String city;

    @DatabaseField(columnName = "ZIPCODE", canBeNull = false)
    private String zipCode;

    @DatabaseField(columnName = "WEIGHT")
    private double weight;

    @DatabaseField(columnName = "HEIGHT")
    private double height;

    @DatabaseField(columnName = "NFZ")
    private int branchNfz;

    @ForeignCollectionField(eager = true)
    private ForeignCollection<Registration> registrations;
}

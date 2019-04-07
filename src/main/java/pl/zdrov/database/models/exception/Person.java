package pl.zdrov.database.models.exception;

import com.j256.ormlite.field.DatabaseField;

public abstract class Person {

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


}

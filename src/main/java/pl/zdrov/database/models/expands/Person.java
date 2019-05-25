package pl.zdrov.database.models.expands;

import com.j256.ormlite.field.DatabaseField;

public abstract class Person {
    /**
     * Identyfikator generowany automatycznie
     */
    @DatabaseField(generatedId = true)
    protected int id;
    /**
     * Imię
     */
    @DatabaseField(columnName = "NAME", canBeNull = false)
    protected String name;
    /**
     * Nazwisko
     */
    @DatabaseField(columnName = "SURNAME", canBeNull = false)
    protected String surName;
    /**
     * Pesel
     */
    @DatabaseField(columnName = "PESEL",canBeNull = false, width = 11,unique = true)
    protected String pesel;
    /**
     * Mail
     */
    @DatabaseField(columnName = "MAIL")
    protected String mail;

    /**
     * Telefon
     */
    @DatabaseField(columnName = "PHONE", width = 9)
    protected String phone;

    public Person() {
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
}

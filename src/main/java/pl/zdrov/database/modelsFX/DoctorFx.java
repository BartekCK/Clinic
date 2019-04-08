package pl.zdrov.database.modelsFX;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DoctorFx {

    private IntegerProperty id = new SimpleIntegerProperty();

    private StringProperty name = new SimpleStringProperty();

    private StringProperty surName= new SimpleStringProperty();

    private StringProperty pesel= new SimpleStringProperty();

    private StringProperty mail= new SimpleStringProperty();

    private StringProperty phone= new SimpleStringProperty();

    private IntegerProperty pwz= new SimpleIntegerProperty();

    private StringProperty specialization=new SimpleStringProperty();


    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSurName() {
        return surName.get();
    }

    public StringProperty surNameProperty() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName.set(surName);
    }

    public String getPesel() {
        return pesel.get();
    }

    public StringProperty peselProperty() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel.set(pesel);
    }

    public String getMail() {
        return mail.get();
    }

    public StringProperty mailProperty() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail.set(mail);
    }

    public String getPhone() {
        return phone.get();
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public int getPwz() {
        return pwz.get();
    }

    public IntegerProperty pwzProperty() {
        return pwz;
    }

    public void setPwz(int pwz) {
        this.pwz.set(pwz);
    }

    public String getSpecialization() {
        return specialization.get();
    }

    public StringProperty specializationProperty() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization.set(specialization);
    }
}

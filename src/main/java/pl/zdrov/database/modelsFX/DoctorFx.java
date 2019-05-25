package pl.zdrov.database.modelsFX;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Klasa dla lekarza ale pomaga ona wyświetlać dane w kontrolkach określnych w plikach fxml
 */
public class DoctorFx {

    private IntegerProperty id = new SimpleIntegerProperty();

    private StringProperty name = new SimpleStringProperty();

    private StringProperty surName = new SimpleStringProperty();

    private StringProperty pesel = new SimpleStringProperty();

    private StringProperty mail = new SimpleStringProperty();

    private StringProperty phone = new SimpleStringProperty();

    private StringProperty pwz = new SimpleStringProperty();

    private SpecializationFx specializationFx;

    public SpecializationFx getSpecializationFx() {
        return specializationFx;
    }

    public void setSpecializationFx(SpecializationFx specializationFx) {
        this.specializationFx = specializationFx;
    }

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

    public String getPwz() {
        return pwz.get();
    }

    public StringProperty pwzProperty() {
        return pwz;
    }

    public void setPwz(String pwz) {
        this.pwz.set(pwz);
    }

    public DoctorFx() {
    }

    @Override
    public String toString() {
        return "DoctorFx{" +
                "id=" + id +
                ", name=" + name +
                ", surName=" + surName +
                ", pesel=" + pesel +
                ", mail=" + mail +
                ", phone=" + phone +
                ", pwz=" + pwz +
                '}';
    }
}



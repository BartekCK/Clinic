package pl.zdrov.database.modelsFX;

import javafx.beans.property.*;

public class PatientFx {


    protected IntegerProperty id = new SimpleIntegerProperty();


    protected StringProperty name = new SimpleStringProperty();


    protected StringProperty surName= new SimpleStringProperty();


    protected StringProperty pesel= new SimpleStringProperty();


    protected StringProperty mail= new SimpleStringProperty();


    protected StringProperty phone= new SimpleStringProperty();


    private StringProperty address= new SimpleStringProperty();


    private StringProperty city= new SimpleStringProperty();


    private StringProperty zipCode= new SimpleStringProperty();


    private DoubleProperty weight = new SimpleDoubleProperty();


    private DoubleProperty height = new SimpleDoubleProperty();


    private StringProperty branchNfz= new SimpleStringProperty();

    public PatientFx() {
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

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getCity() {
        return city.get();
    }

    public StringProperty cityProperty() {
        return city;
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public String getZipCode() {
        return zipCode.get();
    }

    public StringProperty zipCodeProperty() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode.set(zipCode);
    }

    public double getWeight() {
        return weight.get();
    }

    public DoubleProperty weightProperty() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight.set(weight);
    }

    public double getHeight() {
        return height.get();
    }

    public DoubleProperty heightProperty() {
        return height;
    }

    public void setHeight(double height) {
        this.height.set(height);
    }

    public String getBranchNfz() {
        return branchNfz.get();
    }

    public StringProperty branchNfzProperty() {
        return branchNfz;
    }

    public void setBranchNfz(String branchNfz) {
        this.branchNfz.set(branchNfz);
    }

    @Override
    public String toString() {
        return "PatientFx{" +
                "id=" + id +
                ", name=" + name +
                ", surName=" + surName +
                ", pesel=" + pesel +
                ", mail=" + mail +
                ", phone=" + phone +
                ", address=" + address +
                ", city=" + city +
                ", zipCode=" + zipCode +
                ", weight=" + weight +
                ", height=" + height +
                ", branchNfz=" + branchNfz +
                '}';
    }
}

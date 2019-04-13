package pl.zdrov.database.models;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import pl.zdrov.database.models.exception.BaseModel;

@DatabaseTable(tableName = "PATIENTS")
public class Patient implements BaseModel {

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
    private String branchNfz;

    @ForeignCollectionField(eager = true)
    private ForeignCollection<Registration> registrations;

    public Patient() {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getBranchNfz() {
        return branchNfz;
    }

    public void setBranchNfz(String branchNfz) {
        this.branchNfz = branchNfz;
    }

    public ForeignCollection<Registration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(ForeignCollection<Registration> registrations) {
        this.registrations = registrations;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                ", pesel='" + pesel + '\'' +
                ", mail='" + mail + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", weight=" + weight +
                ", height=" + height +
                ", branchNfz='" + branchNfz + '\'' +
                '}';
    }
}

package pl.zdrov.database.models;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import pl.zdrov.database.models.expands.BaseModel;
import pl.zdrov.database.models.expands.Person;

/**
 * Klasa przedstawia ogólną postać pacjenta
 */
@DatabaseTable(tableName = "PATIENTS")
public class Patient extends Person implements BaseModel {


    /**
     * Adres
     */
    @DatabaseField(columnName = "ADRESS", canBeNull = false)
    private String address;

    /**
     * Nazwa miasta
     */
    @DatabaseField(columnName = "CITY", canBeNull = false)
    private String city;

    /**
     * Kod pocztowy
     */
    @DatabaseField(columnName = "ZIPCODE", canBeNull = false)
    private String zipCode;

    /**
     * Waga
     */
    @DatabaseField(columnName = "WEIGHT")
    private double weight;

    /**
     * Wysokość
     */
    @DatabaseField(columnName = "HEIGHT")
    private double height;

    /**
     * Rodzaj oddziału NFZ
     */
    @DatabaseField(columnName = "NFZ")
    private String branchNfz;

    /**
     * Dostęp do rejestracji
     */
    @ForeignCollectionField(eager = true)
    private ForeignCollection<Registration> registrations;

    public Patient() {
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

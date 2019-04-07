package pl.zdrov.database.models;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import pl.zdrov.database.models.exception.Person;

@DatabaseTable(tableName = "PATIENTS")
public class Patient extends Person {

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

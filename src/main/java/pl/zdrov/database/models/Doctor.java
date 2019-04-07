package pl.zdrov.database.models;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import pl.zdrov.database.models.exception.Person;

@DatabaseTable(tableName = "DOCTORS")
public class Doctor extends Person {

    @DatabaseField(columnName = "PWZ",canBeNull = false, width = 7,unique = true)
    private int pwz;

    @DatabaseField(columnName = "SPECIALIZATION", canBeNull = false)
    private String specialization;

    @ForeignCollectionField(eager = true)
    private ForeignCollection<WorkHours> workHours;

    @ForeignCollectionField
    private ForeignCollection<Registration> registrations;


}

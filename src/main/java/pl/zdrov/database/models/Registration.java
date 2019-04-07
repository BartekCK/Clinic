package pl.zdrov.database.models;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName = "REGISTRATION")
public class Registration {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "DOCTOR_ID", foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true, canBeNull = false)
    private Doctor doctor;

    @DatabaseField(columnName = "PATIENT_ID", foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true, canBeNull = false)
    private Patient patient;

    @DatabaseField(columnName = "VISIT_DATE",dataType = DataType.DATE_STRING,format = "dd-mm-yyy-HH-MM")
    private Date addedDate;


}

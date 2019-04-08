package pl.zdrov.database.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import pl.zdrov.database.models.exception.BaseModel;

import java.time.LocalTime;

@DatabaseTable(tableName = "WORK_HOURS")
public class WorkHours implements BaseModel {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "DOCTOR_ID", foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true, canBeNull = false)
    private Doctor doctor;

    @DatabaseField(columnName = "DAY")
    private String day;

    @DatabaseField(columnName = "TIME")
    private String time;
}

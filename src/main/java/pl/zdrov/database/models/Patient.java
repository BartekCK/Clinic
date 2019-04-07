package pl.zdrov.database.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "PATIENTS")
public class Patient {

    @DatabaseField(generatedId = true)
    private int id;
}

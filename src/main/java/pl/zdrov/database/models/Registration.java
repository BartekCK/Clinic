package pl.zdrov.database.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "REGISTRATION")
public class Registration {

    @DatabaseField(generatedId = true)
    private int id;
}

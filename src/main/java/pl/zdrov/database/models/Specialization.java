package pl.zdrov.database.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import pl.zdrov.database.models.exception.BaseModel;

@DatabaseTable(tableName = "SPECIALIZATION")
public class Specialization implements BaseModel {

    @DatabaseField(generatedId = true)
    protected int id;

    @DatabaseField(columnName = "TITLE", canBeNull = false)
    protected String title;

    public Specialization() {
    }

    public Specialization(String title) {
        this.title = title;
    }
}

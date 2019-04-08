package pl.zdrov.database.models;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import pl.zdrov.database.models.exception.BaseModel;

@DatabaseTable(tableName = "SPECIALIZATION")
public class Specialization implements BaseModel {

    @DatabaseField(generatedId = true)
    protected int id;

    @DatabaseField(columnName = "TITLE", canBeNull = false)
    protected String title;

    @ForeignCollectionField(eager = true)
    private ForeignCollection<Doctor> doctors;

    public Specialization() {
    }

    public Specialization(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return id+") "+title;
    }
}

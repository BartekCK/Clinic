package pl.zdrov.database.models;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import pl.zdrov.database.models.exception.BaseModel;

@DatabaseTable(tableName = "SPECIALIZATION")
public class Specialization implements BaseModel {

    private static final String [] spec = {"Alergologia (0731)","Angiologia (0732)","Diabetologia (0740)","Endokrynologia (0741)","Farmakologia kliniczna (0742)","Gastroenterologia (0743)","Hematologia (0745)",
            "Kardiologia dziecieca (0762)","Medycyna paliatywna (0750)","Medycyna sportowa (0751)","Nefrologia (0752)","Neurologia dziecieca (0763)","Reumatologia (0757)"
    };

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

    public static String[] getSpec() {
        return spec;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

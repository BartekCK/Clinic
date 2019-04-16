package pl.zdrov.database.modelsFX;


import javafx.beans.property.*;

import java.time.LocalDate;


public class RegistrationFx {

    private IntegerProperty id = new SimpleIntegerProperty();

    private DoctorFx doctorFx;

    private PatientFx patientFx;

    private ObjectProperty<LocalDate> addedDate = new SimpleObjectProperty<>();

    private StringProperty timeFrom= new SimpleStringProperty();

    private StringProperty timeTo= new SimpleStringProperty();

    public RegistrationFx() {
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public DoctorFx getDoctorFx() {
        return doctorFx;
    }

    public void setDoctorFx(DoctorFx doctorFx) {
        this.doctorFx = doctorFx;
    }

    public PatientFx getPatientFx() {
        return patientFx;
    }

    public void setPatientFx(PatientFx patientFx) {
        this.patientFx = patientFx;
    }

    public LocalDate getAddedDate() {
        return addedDate.get();
    }

    public ObjectProperty<LocalDate> addedDateProperty() {
        return addedDate;
    }

    public void setAddedDate(LocalDate addedDate) {
        this.addedDate.set(addedDate);
    }

    public String getTimeFrom() {
        return timeFrom.get();
    }

    public StringProperty timeFromProperty() {
        return timeFrom;
    }

    public void setTimeFrom(String timeFrom) {
        this.timeFrom.set(timeFrom);
    }

    public String getTimeTo() {
        return timeTo.get();
    }

    public StringProperty timeToProperty() {
        return timeTo;
    }

    public void setTimeTo(String timeTo) {
        this.timeTo.set(timeTo);
    }
}

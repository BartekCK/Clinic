package pl.zdrov.database.modelsFX;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class WorkHoursFx {

    private IntegerProperty id = new SimpleIntegerProperty();

    private DoctorFx doctorFx;

    private StringProperty day = new SimpleStringProperty();

    private StringProperty timeFrom = new SimpleStringProperty();

    private StringProperty timeTo = new SimpleStringProperty();




    public WorkHoursFx() {
    }

    public WorkHoursFx(String day, String timeFrom, String timeTo) {
        this.day.set(day);
        this.timeFrom.set(timeFrom);
        this.timeTo.set(timeTo);
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

    public String getDay() {
        return day.get();
    }

    public StringProperty dayProperty() {
        return day;
    }

    public void setDay(String day) {
        this.day.set(day);
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

    @Override
    public String toString() {
        return "WorkHoursFx{" +
                "id=" + id +
                ", doctorFx=" + doctorFx +
                ", day=" + day +
                ", timeFrom=" + timeFrom +
                ", timeTo=" + timeTo +
                '}';
    }



}
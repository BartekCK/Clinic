package pl.zdrov.database.modelsFX;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class WorkHoursFx {

    private StringProperty day = new SimpleStringProperty();

    private StringProperty timeFrom = new SimpleStringProperty();

    private StringProperty timeTo = new SimpleStringProperty();

    private DoctorFx doctorFx;


    public WorkHoursFx() {
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

    public DoctorFx getDoctorFx() {
        return doctorFx;
    }

    public void setDoctorFx(DoctorFx doctorFx) {
        this.doctorFx = doctorFx;
    }
}
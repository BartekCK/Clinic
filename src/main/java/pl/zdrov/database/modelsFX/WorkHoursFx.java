package pl.zdrov.database.modelsFX;

import javafx.beans.property.StringProperty;


public class WorkHoursFx {

    private StringProperty day;

    private StringProperty timeFrom ;

    private StringProperty timeTo;

    public WorkHoursFx(StringProperty day, StringProperty timeFrom, StringProperty timeTo) {
        this.day = day;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
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
}
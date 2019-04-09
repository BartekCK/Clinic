package pl.zdrov.utilies.converters;

import pl.zdrov.database.models.WorkHours;
import pl.zdrov.database.modelsFX.WorkHoursFx;

public class ConverterWorkHours {

    public static WorkHours convertToWorkHours(WorkHoursFx workHoursFx){
        WorkHours workHours = new WorkHours();
        workHours.setDay(workHoursFx.getDay());
        workHours.setTimeFrom(workHoursFx.getTimeFrom());
        workHours.setTimeTo(workHoursFx.getTimeTo());
        return workHours;
    }

    public static WorkHoursFx convertToWorkHoursFx(WorkHours WorkHours){
        WorkHoursFx workHoursFx = new WorkHoursFx();
        workHoursFx.setDay(WorkHours.getDay());
        workHoursFx.setTimeFrom(WorkHours.getTimeFrom());
        workHoursFx.setTimeTo(WorkHours.getTimeTo());
        return workHoursFx;
    }



}

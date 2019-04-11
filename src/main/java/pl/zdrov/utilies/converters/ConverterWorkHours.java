package pl.zdrov.utilies.converters;

import pl.zdrov.database.models.WorkHours;
import pl.zdrov.database.modelsFX.WorkHoursFx;

public class ConverterWorkHours {

    public static WorkHours convertToWorkHours(WorkHoursFx workHoursFx){
        WorkHours workHours = new WorkHours();
        workHours.setId(workHoursFx.getId());
        workHours.setDay(workHoursFx.getDay());
        workHours.setTimeFrom(workHoursFx.getTimeFrom());
        workHours.setTimeTo(workHoursFx.getTimeTo());
        workHours.setDoctor(ConverterDoctor.convertToDoctor(workHoursFx.getDoctorFx()));
        return workHours;
    }

    public static WorkHoursFx convertToWorkHoursFx(WorkHours workHours){
        WorkHoursFx workHoursFx = new WorkHoursFx();
        workHoursFx.setId(workHours.getId());
        workHoursFx.setDay(workHours.getDay());
        workHoursFx.setTimeFrom(workHours.getTimeFrom());
        workHoursFx.setTimeTo(workHours.getTimeTo());
        workHoursFx.setDoctorFx(ConverterDoctor.convertToDoctorFx(workHours.getDoctor()));
        return workHoursFx;
    }



}

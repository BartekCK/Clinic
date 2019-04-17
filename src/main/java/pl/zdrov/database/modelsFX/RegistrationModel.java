package pl.zdrov.database.modelsFX;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.zdrov.database.dao.RegistrationDao;
import pl.zdrov.database.models.Doctor;
import pl.zdrov.database.models.Patient;
import pl.zdrov.database.models.Registration;
import pl.zdrov.utilies.DialogCatch;
import pl.zdrov.utilies.Utils;
import pl.zdrov.utilies.exceptions.ApplicationException;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RegistrationModel {

    private Doctor doctor;
    private Patient patient;
    private WorkHoursModel workHoursModel;
    private String polishDay;

    private ObservableList<WorkHoursFx> choosenDayWorkHoursFx = FXCollections.observableArrayList();
    private ObservableList<WorkHoursFx> divisionHoursOfDayWorkHoursFx = FXCollections.observableArrayList();
    private List<Registration> listOccupiedHoursSelectedDay = new ArrayList<>();

    public RegistrationModel() {
        workHoursModel = new WorkHoursModel();
    }

    private void selectPolishDay(LocalDate selectedDate) throws ApplicationException {
        choosenDayWorkHoursFx.clear();
        divisionHoursOfDayWorkHoursFx.clear();
        this.polishDay = polishDays(selectedDate.getDayOfWeek().toString());
        if(this.polishDay ==null)
            throw new ApplicationException("Przychodnia nie pracuje w weekendy");
        workHoursModel.getWorkHoursFxList().forEach(workHoursFx -> {
            if(polishDay.equals(workHoursFx.getDay()))
            {
                choosenDayWorkHoursFx.add(workHoursFx);
            }
        });
    }

    public void makeSectionWorkHoursSelectedDay(LocalDate selectedDate) throws ApplicationException {
        selectPolishDay(selectedDate);
        choosenDayWorkHoursFx.forEach(workHoursFx -> {
            LocalTime fromLocalTime = LocalTime.parse(workHoursFx.getTimeFrom());
            LocalTime toLocalTime = LocalTime.parse(workHoursFx.getTimeTo());
            while(!fromLocalTime.equals(toLocalTime))
            {
                WorkHoursFx workHoursFx1 = new WorkHoursFx();
                workHoursFx1.setDay(polishDay);
                workHoursFx1.setTimeFrom(fromLocalTime.toString());
                fromLocalTime = fromLocalTime.plusMinutes(30);
                workHoursFx1.setTimeTo(fromLocalTime.toString());
                divisionHoursOfDayWorkHoursFx.add(workHoursFx1);
            }
        });
    }

    public void pullRegistrationFromDataBase(LocalDate selectedDate)
    {
        this.listOccupiedHoursSelectedDay.clear();
        RegistrationDao registrationDao = new RegistrationDao();
        try {
            this.listOccupiedHoursSelectedDay = registrationDao.pullByColumnName("DOCTOR_ID", this.doctor, "VISIT_DATE", Utils.convertToDate(selectedDate));

        } catch (ApplicationException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public ObservableList <WorkHoursFx> checkFreeRegistration(LocalDate selectedDate)
    {

        try {
            makeSectionWorkHoursSelectedDay(selectedDate);
            pullRegistrationFromDataBase(selectedDate);

            if(this.listOccupiedHoursSelectedDay.isEmpty())
            {
                return this.divisionHoursOfDayWorkHoursFx;
            }
            ObservableList<WorkHoursFx> temp = FXCollections.observableArrayList();
            divisionHoursOfDayWorkHoursFx.forEach(workHoursFx -> {
                listOccupiedHoursSelectedDay.forEach(registration -> {
                    if (workHoursFx.getTimeTo().equals(registration.getTimeTo())){
                        temp.add(workHoursFx);
                    }
                });
            });

            divisionHoursOfDayWorkHoursFx.removeAll(temp);
            return divisionHoursOfDayWorkHoursFx;
        } catch (ApplicationException e) {
            DialogCatch.errorCommitDoctor(e.getMessage());
        }
        return null;


    }




    public String polishDays(String day)
    {
        switch (day)
        {
            case "MONDAY":
                return "Poniedziałek";
            case "TUESDAY":
                return "Wtorek";
            case "WEDNESDAY":
                return "Środa";
            case "THURSDAY":
                return "Czwartek";
            case "FRIDAY":
                return "Piątek";
        }
        return null;
    }





    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
        workHoursModel.init(this.doctor);
    }

    public WorkHoursModel getWorkHoursModel() {
        return workHoursModel;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}

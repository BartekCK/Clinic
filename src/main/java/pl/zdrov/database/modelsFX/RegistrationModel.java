package pl.zdrov.database.modelsFX;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.zdrov.database.DbConnector;
import pl.zdrov.database.dao.RegistrationDao;
import pl.zdrov.database.models.Doctor;
import pl.zdrov.database.models.Patient;
import pl.zdrov.database.models.Registration;
import pl.zdrov.database.models.Specialization;
import pl.zdrov.utilies.DialogCatch;
import pl.zdrov.utilies.Utils;
import pl.zdrov.utilies.converters.ConvertRegistration;
import pl.zdrov.utilies.exceptions.ApplicationException;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RegistrationModel {

    private Doctor doctor;
    private Patient patient;
    private WorkHoursModel workHoursModel;
    private String polishDay;

    private Registration registration;
    private RegistrationFx registrationFx;

    private ObservableList<WorkHoursFx> choosenDayWorkHoursFx = FXCollections.observableArrayList();
    private ObservableList<WorkHoursFx> divisionHoursOfDayWorkHoursFx = FXCollections.observableArrayList();
    private List<Registration> listOccupiedHoursSelectedDay = new ArrayList<>();

    private ObservableList<RegistrationFx> registrationFxShowObservableList = FXCollections.observableArrayList();
    private ObservableList<RegistrationFx> registrationFxShowObservableListSearch = FXCollections.observableArrayList();
    private List<Registration> allRegistrationList = new ArrayList<>();

    public RegistrationModel() {
        workHoursModel = new WorkHoursModel();
    }

    private void pullRegistrationFromDataBase()
    {
        try {
            RegistrationDao registrationDao = new RegistrationDao();
            allRegistrationList.clear();
            allRegistrationList = registrationDao.queryForAll(Registration.class);
        } catch (ApplicationException e) {
            DialogCatch.errorCommitDoctor(e.getMessage());
        }
        DbConnector.closeConnectionSource();
    }

    public void init()
    {
        pullRegistrationFromDataBase();
        registrationFxShowObservableList.clear();
        registrationFxShowObservableListSearch.clear();
        allRegistrationList.forEach(registration -> {
            RegistrationFx registrationFx = ConvertRegistration.convertToRegistrationFx(registration);
            registrationFxShowObservableList.add(registrationFx);
        });

    }

    public void deleteRegistrationInDataBase()
    {

        try {
            RegistrationDao registrationDao = new RegistrationDao();
            this.registration = ConvertRegistration.convertToRegistration(this.registrationFx);
            registrationDao.delete(registration);
            DbConnector.closeConnectionSource();
            init();
        } catch (ApplicationException e) {
            DialogCatch.errorCommitDoctor(e.getMessage());
        }
    }


    public void saveRegistrationInDataBase(Registration registration) throws ApplicationException {
        RegistrationDao registrationDao = new RegistrationDao();
        registrationDao.creatOrUpdate(registration);
        DbConnector.closeConnectionSource();
    }

    private void selectPolishDay(LocalDate selectedDate)   {
        choosenDayWorkHoursFx.clear();
        divisionHoursOfDayWorkHoursFx.clear();
        this.polishDay = polishDays(selectedDate.getDayOfWeek().toString());



        workHoursModel.getWorkHoursFxList().forEach(workHoursFx -> {
            if(polishDay.equals(workHoursFx.getDay()))
            {
                choosenDayWorkHoursFx.add(workHoursFx);
            }
        });
    }

    private void makeSectionWorkHoursSelectedDay(LocalDate selectedDate)  {
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

    private void pullRegistrationFromDataBase(LocalDate selectedDate)
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
        DbConnector.closeConnectionSource();

    }


    public ObservableList <WorkHoursFx> checkFreeRegistration(LocalDate selectedDate)
    {
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



    public  ObservableList<RegistrationFx> nameSearch(String compare,int check)
    {
        registrationFxShowObservableListSearch.clear();
        if(check ==0)
        {
            registrationFxShowObservableList.forEach(registrationFx -> {
                if (compare.equals(registrationFx.getDoctorFx().getName()))
                {
                    registrationFxShowObservableListSearch.add(registrationFx);
                }
            });
        }
        else
        {
            registrationFxShowObservableList.forEach(registrationFx -> {
                if (compare.equals(registrationFx.getPatientFx().getName()))
                {
                    registrationFxShowObservableListSearch.add(registrationFx);
                }
            });
        }


        return registrationFxShowObservableListSearch;
    }

    public  ObservableList<RegistrationFx> surnameSearch(String compare,int check)
    {
        registrationFxShowObservableListSearch.clear();
        if(check ==0)
        {
            registrationFxShowObservableList.forEach(registrationFx -> {
                if (compare.equals(registrationFx.getDoctorFx().getSurName()))
                {
                    registrationFxShowObservableListSearch.add(registrationFx);
                }
            });
        }
        else
        {
            registrationFxShowObservableList.forEach(registrationFx -> {
                if (compare.equals(registrationFx.getPatientFx().getSurName()))
                {
                    registrationFxShowObservableListSearch.add(registrationFx);
                }
            });
        }


        return registrationFxShowObservableListSearch;
    }



    public  ObservableList<RegistrationFx> comboboxSearch(String compare,int check)
    {
        registrationFxShowObservableListSearch.clear();
        if(check ==0)
        {
            registrationFxShowObservableList.forEach(registrationFx -> {
                if (compare.equals(registrationFx.getDoctorFx().getSpecializationFx().getTitle()))
                {
                    registrationFxShowObservableListSearch.add(registrationFx);
                }
            });
        }
        else
        {
            registrationFxShowObservableList.forEach(registrationFx -> {
                if (compare.equals(registrationFx.getTimeFrom()))
                {
                    registrationFxShowObservableListSearch.add(registrationFx);
                }
            });
        }


        return registrationFxShowObservableListSearch;
    }


    public  ObservableList<RegistrationFx> dateSearch(String compare)
    {
        registrationFxShowObservableListSearch.clear();

        registrationFxShowObservableList.forEach(registrationFx -> {
            if (compare.equals(registrationFx.getAddedDate().toString()))
            {
                registrationFxShowObservableListSearch.add(registrationFx);
            }
        });



        return registrationFxShowObservableListSearch;
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

    public ObservableList<RegistrationFx> getRegistrationFxShowObservableList() {
        return registrationFxShowObservableList;
    }

    public void setRegistrationFx(RegistrationFx registrationFx) {
        this.registrationFx = registrationFx;
    }

    public void clearRegistrationFxShowObservableListSearch()
    {
        this.registrationFxShowObservableListSearch.clear();
    }




}

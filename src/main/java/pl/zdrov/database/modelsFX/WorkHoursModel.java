package pl.zdrov.database.modelsFX;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.zdrov.controllers.BackgroundController;
import pl.zdrov.database.DbConnector;
import pl.zdrov.database.dao.WorkHoursDao;
import pl.zdrov.database.models.Doctor;
import pl.zdrov.database.models.WorkHours;
import pl.zdrov.utilies.DialogCatch;
import pl.zdrov.utilies.converters.ConverterWorkHours;
import pl.zdrov.utilies.exceptions.ApplicationException;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class WorkHoursModel {

    private ObservableList<WorkHoursFx> workHoursFxList = FXCollections.observableArrayList();
    private List<WorkHours> workHoursList = new LinkedList<>();

    private WorkHoursFx workHoursFx;

    private Doctor doctor;

    public WorkHoursModel() {

        this.doctor = BackgroundController.getDoctor();
    }

    public void saveWorkHoursInDataBase() {

        WorkHoursDao workHoursDao = new WorkHoursDao();
        workHoursFxList.forEach(e->{
            WorkHours workHours = new WorkHours();
            workHours.setDay(e.getDay());
            workHours.setTimeFrom(e.getTimeFrom());
            workHours.setTimeTo(e.getTimeTo());
            workHours.setDoctor(doctor);
            try {
                workHoursDao.creatOrUpdate(workHours);
            } catch (ApplicationException er) {
                DialogCatch.errorCommitDoctor(er.getMessage());
            }
        });
        DbConnector.closeConnectionSource();
    }

    public ObservableList<WorkHoursFx> getWorkHoursFxList() {
        return workHoursFxList;
    }

    public void addWorkHoursFxList(WorkHoursFx workHoursFx) {
        this.workHoursFxList.add(workHoursFx);
    }

    public void setWorkHours(WorkHoursFx workHoursFx) {
        this.workHoursFx = workHoursFx;
    }

    public void deleteItemTimeList()
    {
        workHoursFxList.remove(workHoursFx);
    }

    public void pullWorkHoursFromDataBase(Doctor doctor)
    {
        this.workHoursList.clear();
        this.workHoursFxList.clear();
        WorkHoursDao workHoursDao = new WorkHoursDao();
        try {
            this.workHoursList = workHoursDao.pullByColumnName("DOCTOR_ID",doctor.getId());
        } catch (ApplicationException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DbConnector.closeConnectionSource();
    }
    public void init(Doctor doctor)
    {
        pullWorkHoursFromDataBase(doctor);
        this.workHoursList.forEach(workHours -> {
            WorkHoursFx workHoursFx = ConverterWorkHours.convertToWorkHoursFx(workHours);
            this.workHoursFxList.add(workHoursFx);
        });
    }
}

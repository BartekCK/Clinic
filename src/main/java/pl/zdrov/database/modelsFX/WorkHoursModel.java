package pl.zdrov.database.modelsFX;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.zdrov.database.DbConnector;
import pl.zdrov.database.dao.WorkHoursDao;
import pl.zdrov.database.models.Doctor;
import pl.zdrov.database.models.WorkHours;
import pl.zdrov.utilies.FxmlUtilies;
import pl.zdrov.utilies.converters.ConverterWorkHours;
import pl.zdrov.utilies.exceptions.ApplicationException;

public class WorkHoursModel {

    private ObservableList<WorkHoursFx> workHoursFxList = FXCollections.observableArrayList();

    private WorkHoursFx workHoursFx;

    private Doctor doctor;

    public WorkHoursModel() {
        doctor = FxmlUtilies.getDoctor();//SPRAWDZIC
    }

    public void saveWorkHoursInDataBase() throws ApplicationException {

        WorkHoursDao workHoursDao = new WorkHoursDao();
        workHoursFxList.forEach(e->{
            WorkHours workHours = ConverterWorkHours.convertToWorkHours(e);
            workHours.setDoctor(doctor);
            try {
                workHoursDao.creatOrUpdate(workHours);
            } catch (ApplicationException ex) {
                ex.printStackTrace();
            }

        });
        DbConnector.closeConnectionSource();
    }

    public ObservableList<WorkHoursFx> getWorkHoursFxList() {
        return workHoursFxList;
    }

    public void addTimeList(WorkHoursFx workHoursFx) {
        workHoursFxList.add(workHoursFx);
    }

    public void setWorkHours(WorkHoursFx workHoursFx) {
        this.workHoursFx = workHoursFx;
    }

    public void deleteItemTimeList()
    {
        workHoursFxList.remove(workHoursFx);
    }

    public static void deleteWorkHoursInDataBase(WorkHours workHours)
    {

    }
}

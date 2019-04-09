package pl.zdrov.database.modelsFX;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.zdrov.database.DbConnector;
import pl.zdrov.database.dao.WorkHoursDao;
import pl.zdrov.database.models.WorkHours;
import pl.zdrov.utilies.exceptions.ApplicationException;

public class WorkHoursModel {

    private ObservableList<WorkHoursFx> workHoursFxList = FXCollections.observableArrayList();

    private WorkHoursFx workHoursFx;

    public WorkHoursModel() {

    }

    public void saveDoctorInDataBase(WorkHours workHours) throws ApplicationException {
        WorkHoursDao workHoursDao = new WorkHoursDao();

        workHoursDao.creatOrUpdate(workHours);
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
}

package pl.zdrov.database.modelsFX;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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


    /**
     * Zapisuje godziny pracy lekarza do bazy danych
     */
    public void saveWorkHoursInDataBase() {

        WorkHoursDao workHoursDao = new WorkHoursDao();
        try {
            workHoursDao.deleteByColumnName("DOCTOR_ID",this.doctor.getId());
        } catch (ApplicationException e) {
            DialogCatch.errorCommitDoctor(e.getMessage());
        } catch (SQLException e) {
            DialogCatch.errorCommitDoctor(e.getMessage());
        }

        workHoursFxList.forEach(e->{
            WorkHours workHours = new WorkHours();
            workHours.setDay(e.getDay());
            workHours.setTimeFrom(e.getTimeFrom());
            workHours.setTimeTo(e.getTimeTo());
            workHours.setDoctor(this.doctor);
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

    /**
     * Pobiera godziny pracy
     * @param doctor wskazany lekarz dla pobrania godzin pracy
     */
    private void pullWorkHoursFromDataBase(Doctor doctor)
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

    /**
     * Inicjajcja pracy z obiektami tej klasy
     * @param doctor lekarz do ktorego godziny pracy przynależą
     */
    public void init(Doctor doctor)
    {
        this.doctor = doctor;
        pullWorkHoursFromDataBase(doctor);
        this.workHoursList.forEach(workHours -> {
            WorkHoursFx workHoursFx = ConverterWorkHours.convertToWorkHoursFx(workHours);
            this.workHoursFxList.add(workHoursFx);
        });
    }
}

package pl.zdrov.database.modelsFX;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.zdrov.database.models.Doctor;
import pl.zdrov.database.models.Patient;
import pl.zdrov.database.models.Registration;
import pl.zdrov.database.models.WorkHours;

public class RegistrationModel {

    private Doctor doctor;
    private Patient patient;
    private WorkHoursModel workHoursModel;

    public RegistrationModel() {
        workHoursModel = new WorkHoursModel();
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

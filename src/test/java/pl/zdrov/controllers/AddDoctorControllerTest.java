package pl.zdrov.controllers;

import org.junit.Test;
import pl.zdrov.database.models.Doctor;
import pl.zdrov.database.models.Specialization;
import pl.zdrov.database.modelsFX.SpecializationModel;

import static org.junit.Assert.*;

public class AddDoctorControllerTest {

    @Test
    public void setTextFields() {
        Doctor doctor = new Doctor();
        doctor.setName("Bartek");
        doctor.setSurName("Mądry");
        doctor.setPesel("11111111111");
        doctor.setPwz("1111111");
        doctor.setMail("małpka@");
        doctor.setPhone("222222222");
        doctor.setSpecialization((Specialization) SpecializationModel.returnAllSpecialization().get(1));
        assertNotNull(doctor);

    }
}
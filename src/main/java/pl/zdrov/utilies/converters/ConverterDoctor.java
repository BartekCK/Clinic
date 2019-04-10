package pl.zdrov.utilies.converters;


import pl.zdrov.database.models.Doctor;
import pl.zdrov.database.modelsFX.DoctorFx;

public class ConverterDoctor {

    public static Doctor convertToDoctor(DoctorFx doctorFx)
    {

        Doctor doctor = new Doctor();
        return doctor;
    }

    public static DoctorFx convertToDoctorFx(Doctor doctor)
    {
        DoctorFx doctorFx = new DoctorFx();
        doctorFx.setId(doctor.getId());
        doctorFx.setName(doctor.getName());
        doctorFx.setSurName(doctor.getSurName());
        doctorFx.setPesel(doctor.getPesel());
        doctorFx.setMail(doctor.getMail());
        doctorFx.setPhone(doctor.getPhone());
        doctorFx.setPwz(doctor.getPwz());
        //BRAKUJE SPECIALIZACJI

        return doctorFx;
    }
}

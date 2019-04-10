package pl.zdrov.utilies.converters;


import pl.zdrov.database.models.Doctor;
import pl.zdrov.database.modelsFX.DoctorFx;

public class ConverterDoctor {

    public static Doctor convertToDoctor(DoctorFx doctorFx)
    {
        Doctor doctor = new Doctor();
        doctor.setId(doctorFx.getId());
        doctor.setName(doctorFx.getName());
        doctor.setSurName(doctorFx.getSurName());
        doctor.setPesel(doctorFx.getPesel());
        doctor.setMail(doctorFx.getMail());
        doctor.setPhone(doctorFx.getPhone());
        doctor.setPwz(doctorFx.getPwz());
        doctor.setSpecialization(ConvertSpecialization.convertToSpecialization(doctorFx.getSpecializationFx()));
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
        doctorFx.setSpecializationFx(ConvertSpecialization.convertToSpecializationFx(doctor.getSpecialization()));

        return doctorFx;
    }
}

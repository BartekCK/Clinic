package pl.zdrov.utilies.converters;

import pl.zdrov.database.models.Patient;
import pl.zdrov.database.modelsFX.PatientFx;

public class ConverterPatient {
    public static Patient convertToPatient(PatientFx patientFx)
    {
        Patient patient = new Patient();
        patient.setId(patientFx.getId());
        patient.setName(patientFx.getName());
        patient.setSurName(patientFx.getSurName());
        patient.setPesel(patientFx.getPesel());
        patient.setMail(patientFx.getMail());
        patient.setPhone(patientFx.getPhone());
        patient.setAddress(patientFx.getAddress());
        patient.setCity(patientFx.getCity());
        patient.setZipCode(patientFx.getZipCode());
        patient.setWeight(patientFx.getWeight());
        patient.setHeight(patientFx.getHeight());
        patient.setBranchNfz(patientFx.getBranchNfz());


        return patient;
    }

    public static PatientFx convertToPatientFx(Patient patient)
    {
        PatientFx patientFx = new PatientFx();

        patientFx.setId(patient.getId());
        patientFx.setName(patient.getName());
        patientFx.setSurName(patient.getSurName());
        patientFx.setPesel(patient.getPesel());
        patientFx.setMail(patient.getMail());
        patientFx.setPhone(patient.getPhone());
        patientFx.setAddress(patient.getAddress());
        patientFx.setCity(patient.getCity());
        patientFx.setZipCode(patient.getZipCode());
        patientFx.setWeight(patient.getWeight());
        patientFx.setHeight(patient.getHeight());
        patientFx.setBranchNfz(patient.getBranchNfz());


        return patientFx;
    }
}

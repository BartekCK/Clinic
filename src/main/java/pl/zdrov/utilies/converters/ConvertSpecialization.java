package pl.zdrov.utilies.converters;

import pl.zdrov.database.models.Specialization;
import pl.zdrov.database.modelsFX.SpecializationFx;
/**
 * Klasa opowiada za konwetrowanie obiektów między Specialization a SpecializationFx
 */
public class ConvertSpecialization {


    public static Specialization convertToSpecialization(SpecializationFx specializationFx)
    {
        Specialization specialization = new Specialization();
        specialization.setId(specializationFx.getId());
        specialization.setTitle(specializationFx.getTitle());
        return specialization;
    }

    public static SpecializationFx convertToSpecializationFx(Specialization specialization)
    {
        SpecializationFx specializationFx = new SpecializationFx();
        specializationFx.setId(specialization.getId());
        specializationFx.setTitle(specialization.getTitle());
        return specializationFx;
    }
}

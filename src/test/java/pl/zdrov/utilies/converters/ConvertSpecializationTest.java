package pl.zdrov.utilies.converters;

import org.junit.Assert;
import org.junit.Test;
import pl.zdrov.database.models.Specialization;

import static org.junit.Assert.*;

public class ConvertSpecializationTest {

    @Test
    public void convertToSpecializationFx() {
        assertNotNull(ConvertSpecialization.convertToSpecializationFx(new Specialization()));
    }
}
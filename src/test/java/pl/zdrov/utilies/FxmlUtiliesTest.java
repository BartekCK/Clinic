package pl.zdrov.utilies;

import org.junit.Test;

import static org.junit.Assert.*;

public class FxmlUtiliesTest {

    @Test
    public void setFxmlParent() {
        assertNull(FxmlUtilies.setFxmlParent(null));
    }

    @Test(expected = NullPointerException.class)
    public void getControllerNewWindow() {
        FxmlUtilies.getControllerNewWindow("brak","asd");
    }

}
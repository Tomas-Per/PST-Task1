package lt.pst.uzd.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VeiksmaiTest {

    @Test
    void testConstructor() {
        Veiksmai veiksmas = new Veiksmai(1,"insert", 1, "2021-11-05");
        assertAll(
                () -> assertEquals(1, veiksmas.getId() ),
                () -> assertEquals("insert", veiksmas.getVeiksmas()),
                () -> assertEquals(1, veiksmas.getVartotojoId()),
                () -> assertEquals("2021-11-05", veiksmas.getData())
        );
    }

    @Test
    void testEqualsObject() {
        Veiksmai veiksmas1 = new Veiksmai(1,"insert", 1, "2021-11-05");
        Veiksmai veiksmas2 = new Veiksmai(1,"insert", 1, "2021-11-05");
        assertEquals(veiksmas1, veiksmas2);
    }

    @Test
    void testSetId() {
        Veiksmai veiksmas = new Veiksmai();
        veiksmas.setId(2);
        int id = veiksmas.getId();
        assertEquals(id,2);
    }

    @Test
    void testSetVeiksmas() {
        Veiksmai veiksmas = new Veiksmai();
        veiksmas.setVeiksmas("delete");
        String veiksmasPav = veiksmas.getVeiksmas();
        assertEquals(veiksmasPav,"delete");
    }

    @Test
    void testSetVartotojoId() {
        Veiksmai veiksmas = new Veiksmai();
        veiksmas.setVartotojoId(2);
        int vartotojoId = veiksmas.getVartotojoId();
        assertEquals(vartotojoId,2);
    }

    @Test
    void testSetData() {
        Veiksmai veiksmas = new Veiksmai();
        veiksmas.setData("2021-11-11");
        String data = veiksmas.getData();
        assertEquals(data,"2021-11-11");
    }
}

package lt.pst.uzd.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VartotojasTest {

    @Test
    void testConstructor() {
        Vartotojas vartotojas = new Vartotojas(1,"Tomas","+37000000000");
        assertAll(
                () -> assertEquals(1, vartotojas.getId() ),
                () -> assertEquals("Tomas", vartotojas.getVardas()),
                () -> assertEquals("+37000000000", vartotojas.getTelNr())
        );
    }

    @Test
    void testEqualsObject() {
        Vartotojas vartotojas1 = new Vartotojas(1,"Tomas","+37000000000");
        Vartotojas vartotojas2 = new Vartotojas(1,"Tomas","+37000000000");
        assertEquals(vartotojas1, vartotojas2);
    }

    @Test
    void testSetId() {
        Vartotojas vartotojas = new Vartotojas();
        vartotojas.setId(2);
        int id = vartotojas.getId();
        assertEquals(id,2);
    }

    @Test
    void testSetVardas() {
        Vartotojas vartotojas = new Vartotojas();
        vartotojas.setVardas("Tom");
        String vardas = vartotojas.getVardas();
        assertEquals(vardas,"Tom");
    }

    @Test
    void testSetTelNr() {
        Vartotojas vartotojas = new Vartotojas();
        vartotojas.setTelNr("+37111111111");
        String telNr = vartotojas.getTelNr();
        assertEquals(telNr,"+37111111111");
    }
}

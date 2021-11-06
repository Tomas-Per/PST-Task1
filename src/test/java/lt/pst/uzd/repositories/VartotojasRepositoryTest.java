package lt.pst.uzd.repositories;

import lt.pst.uzd.models.Vartotojas;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;


@TestPropertySource(properties = {"spring.datasource.initialization-mode=never"})
@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class VartotojasRepositoryTest {

    @Autowired
    private VartotojasRepository vartotojasRepository;

    @BeforeAll
    private void setUp() {
        Vartotojas v1 = new Vartotojas(1, "Tomas", "+37011111");
        Vartotojas v2 = new Vartotojas(2, "Thomas", "+37022222");
        Vartotojas v3 = new Vartotojas(3, "Tom", "+37033333");
        vartotojasRepository.save(v1);
        vartotojasRepository.save(v2);
        vartotojasRepository.save(v3);
    }

    @Test
    public void testFindById() {
        Vartotojas vartotojas1 = new Vartotojas(1,"Tomas","+37011111");
        Vartotojas vartotojas2 = vartotojasRepository.findById(1);

        assertEquals(vartotojas1, vartotojas2);
    }

    @Test
    public void testSave() {
        Vartotojas vartotojas1 = new Vartotojas(4,"Jonas","+37000000000");
        vartotojasRepository.save(vartotojas1);
        Vartotojas vartotojas2 = vartotojasRepository.findByTelNrAndVardas("+37000000000", "Jonas");

        assertTrue(vartotojas1.equals(vartotojas2));
    }

    @Test
    public void testFindAll() {

        Iterable<Vartotojas> vartotojai = vartotojasRepository.findAll();
        assertNotNull(vartotojai);
        ArrayList<Vartotojas> result = new ArrayList<>();
        vartotojai.forEach(result::add);
        assertEquals(3, result.size());
    }

    @Test
    public void testFindByTelNrAndVardas() {
        Vartotojas vartotojas1 = new Vartotojas(1,"Tomas","+37011111");

        Vartotojas vartotojas2 = vartotojasRepository.findByTelNrAndVardas("+37011111", "Tomas");
        assertEquals(vartotojas1, vartotojas2);
    }

    @Test
    public void testFindByVardas() {
        Vartotojas vartotojas1 = new Vartotojas(1,"Tomas","+37011111");
        Vartotojas vartotojas2 = vartotojasRepository.findByVardas("Tomas");
        assertNotNull(vartotojas2);
        assertEquals(vartotojas1, vartotojas2);
    }

    @Test
    public void testDeleteById() {

        Vartotojas vartotojas2 = vartotojasRepository.findByTelNrAndVardas("+37011111", "Tomas");
        vartotojasRepository.deleteById(vartotojas2.getId());

        Iterable<Vartotojas> vartotojai = vartotojasRepository.findAll();
        ArrayList<Vartotojas> result = new ArrayList<>();
        vartotojai.forEach(result::add);
        assertEquals(2, result.size());
    }

    @Test
    public void testDelete() {

        Vartotojas vartotojas = vartotojasRepository.findByTelNrAndVardas("+37011111", "Tomas");
        vartotojasRepository.delete(vartotojas);

        Iterable<Vartotojas> vartotojai = vartotojasRepository.findAll();
        ArrayList<Vartotojas> result = new ArrayList<>();
        vartotojai.forEach(result::add);
        assertEquals(2, result.size());
    }


}

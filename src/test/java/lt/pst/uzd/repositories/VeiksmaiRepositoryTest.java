package lt.pst.uzd.repositories;


import lt.pst.uzd.models.Veiksmai;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestPropertySource(properties = {"spring.datasource.initialization-mode=never"})
@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class VeiksmaiRepositoryTest {

    @Autowired
    private VeiksmaiRepository veiksmaiRepository;

    @BeforeAll
    private void setUp() {
        Veiksmai v1 = new Veiksmai(1, "insert", 1,"2021-11-05");
        Veiksmai v2 = new Veiksmai(2, "delete", 2,"2021-11-05");
        Veiksmai v3 = new Veiksmai(3, "update", 3,"2021-11-05");
        veiksmaiRepository.save(v1);
        veiksmaiRepository.save(v2);
        veiksmaiRepository.save(v3);
    }

    @Test
    public void testFindById() {
        Veiksmai veiksmas1 = new Veiksmai(1, "insert", 1,"2021-11-05");
        Veiksmai veiksmas2 = veiksmaiRepository.findById(1);

        assertEquals(veiksmas1, veiksmas2);
    }

    @Test
    public void testSave() {
        Veiksmai veiksmas1 = new Veiksmai(4, "insert", 4,"2021-11-06");
        veiksmaiRepository.save(veiksmas1);
        Veiksmai veiksmas2 = veiksmaiRepository.findById(4);

        assertTrue(veiksmas1.equals(veiksmas2));
    }

    @Test
    public void testFindAll() {
        Iterable<Veiksmai> veiksmai = veiksmaiRepository.findAll();
        assertNotNull(veiksmai);
        ArrayList<Veiksmai> result = new ArrayList<>();
        veiksmai.forEach(result::add);
        assertEquals(3, result.size());
    }

    @Test
    public void testDeleteAllByVartotojoId() {

        veiksmaiRepository.deleteAllByVartotojoId(1);

        Iterable<Veiksmai> veiksmai = veiksmaiRepository.findAll();
        ArrayList<Veiksmai> result = new ArrayList<>();
        veiksmai.forEach(result::add);
        assertEquals(2, result.size());
    }

    @Test
    public void testDeleteById() {
        veiksmaiRepository.deleteAllByVartotojoId(1);

        Iterable<Veiksmai> veiksmai = veiksmaiRepository.findAll();
        ArrayList<Veiksmai> result = new ArrayList<>();
        veiksmai.forEach(result::add);
        assertEquals(2, result.size());
    }
}

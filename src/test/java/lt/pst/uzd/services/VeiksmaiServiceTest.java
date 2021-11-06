package lt.pst.uzd.services;


import lt.pst.uzd.models.Veiksmai;
import lt.pst.uzd.repositories.VartotojasRepository;
import lt.pst.uzd.repositories.VeiksmaiRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VeiksmaiServiceTest {

    @Mock
    VartotojasRepository vartotojasRepository;
    @Mock
    VeiksmaiRepository veiksmaiRepository;

    @InjectMocks
    VeiksmaiService veiksmaiService;

    @Test
    public void testFindById() {
        Veiksmai veiksmas = new Veiksmai(0, "", 0, "");
        when(veiksmaiRepository.findById(Mockito.anyInt())).thenReturn(veiksmas);

        Veiksmai found = veiksmaiService.findById(1);
        verify(veiksmaiRepository).findById(Mockito.anyInt());
        assertNotNull(found);
    }

    @Test
    public void testSave() {
        Veiksmai veiksmas = new Veiksmai(0, "", 0, "");
        when(veiksmaiService.save(Mockito.any(Veiksmai.class))).thenReturn(veiksmas);

        Veiksmai veik = veiksmaiService.save(veiksmas);
        verify(veiksmaiRepository).save(Mockito.any(Veiksmai.class));
        assertNotNull(veik);
    }

    @Test
    public void testFindAll() {
        Veiksmai veiksmas = new Veiksmai(0, "", 0, "");
        List<Veiksmai> veiksmai = new ArrayList<>();
        veiksmai.add(veiksmas);

        when(veiksmaiRepository.findAll()).thenReturn(veiksmai);
        Iterable<Veiksmai> resultIterable =  veiksmaiService.findAll();
        ArrayList<Veiksmai> result = new ArrayList<>();
        resultIterable.forEach(result::add);
        verify(veiksmaiRepository).findAll();
        assertEquals(1, result.size());
    }

    @Test
    public void testDeleteById() {
        veiksmaiService.deleteById(1);
        verify(veiksmaiRepository).deleteById(Mockito.anyInt());
    }

    @Test
    public void testDeleteAllByVartotojoId() {
        Veiksmai veiksmas = new Veiksmai(0, "", 0, "");
        veiksmaiService.deleteAllByVartotojoId(0);
        verify(veiksmaiRepository).deleteAllByVartotojoId(Mockito.anyInt());
        Iterable<Veiksmai> resultIterable =  veiksmaiService.findAll();
        ArrayList<Veiksmai> result = new ArrayList<>();
        resultIterable.forEach(result::add);
        assertEquals(0, result.size());
    }

    @Test
    public void testReplaceVeiksmas() {
        Veiksmai veiksmas = new Veiksmai(0, "", 0, "");
        Veiksmai veiksmas1 = veiksmaiService.replaceVeiksmas(veiksmas, Mockito.anyInt());
        verify(veiksmaiRepository).save(Mockito.any(Veiksmai.class));
    }


}

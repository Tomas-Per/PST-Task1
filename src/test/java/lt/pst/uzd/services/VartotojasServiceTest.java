package lt.pst.uzd.services;


import lt.pst.uzd.models.Vartotojas;
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
public class VartotojasServiceTest {

    @Mock
    VartotojasRepository vartotojasRepository;
    @Mock
    VeiksmaiRepository veiksmaiRepository;

    @InjectMocks
    VartotojasService vartotojasService;

    @Test
    public void testFindById() {
        Vartotojas vartotojas = new Vartotojas(0, "", "");
        when(vartotojasRepository.findById(Mockito.anyInt())).thenReturn(vartotojas);

        Vartotojas found = vartotojasService.findById(1);
        verify(vartotojasRepository).findById(Mockito.anyInt());
        assertNotNull(found);
    }

    @Test
    public void testSave() {
        Vartotojas vartotojas = new Vartotojas(0, "", "");
        when(vartotojasRepository.save(Mockito.any(Vartotojas.class))).thenReturn(vartotojas);

        Vartotojas var = vartotojasService.save(vartotojas);
        verify(vartotojasRepository).save(Mockito.any(Vartotojas.class));
        assertNotNull(var);
    }

    @Test
    public void testFindAll() {
        Vartotojas vartotojas = new Vartotojas(0, "", "");
        List<Vartotojas> vartotojai = new ArrayList<>();
        vartotojai.add(vartotojas);

        when(vartotojasRepository.findAll()).thenReturn(vartotojai);
        Iterable<Vartotojas> resultIterable =  vartotojasService.findAll();
        ArrayList<Vartotojas> result = new ArrayList<>();
        resultIterable.forEach(result::add);
        verify(vartotojasRepository).findAll();
        assertEquals(1, result.size());
    }

    @Test
    public void testDeleteById() {
        vartotojasService.deleteById(1);
        verify(vartotojasRepository).deleteById(Mockito.anyInt());
    }

    @Test
    public void testDelete() {
        Vartotojas vartotojas = new Vartotojas(0, "", "");
        when(vartotojasRepository.findByTelNrAndVardas(Mockito.anyString(), Mockito.anyString())).thenReturn(vartotojas);
        vartotojasService.delete(vartotojas);
        verify(vartotojasRepository).delete(Mockito.any(Vartotojas.class));
    }

    @Test
    public void testReplaceVartotojas() {
        Vartotojas vartotojas = new Vartotojas(0, "", "");
        Vartotojas vartotojas1 = vartotojasService.replaceVartotojas(vartotojas, Mockito.anyInt());
        verify(vartotojasRepository).save(Mockito.any(Vartotojas.class));
    }

    @Test
    public void testFindByVardas() {
        Vartotojas vartotojas = new Vartotojas(0,"","");

        when(vartotojasRepository.findByVardas("Tomas")).thenReturn(vartotojas);

        Vartotojas vartotojas1 = vartotojasService.findByVardas("Tomas");
        verify(vartotojasRepository).findByVardas("Tomas");
        assertNotNull(vartotojas);
    }
}

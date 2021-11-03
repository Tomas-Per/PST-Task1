package lt.pst.uzd.services;

import lt.pst.uzd.models.Vartotojas;
import lt.pst.uzd.repositories.VartotojasRepository;
import lt.pst.uzd.repositories.VeiksmaiRepository;
import org.springframework.stereotype.Service;

@Service
public class VartotojasService {

    public VartotojasService(VartotojasRepository vartotojasRepository, VeiksmaiRepository veiksmaiRepository) {
        this.vartotojasRepository = vartotojasRepository;
        this.veiksmaiRepository = veiksmaiRepository;
    }

    private final VartotojasRepository vartotojasRepository;
    private final VeiksmaiRepository veiksmaiRepository;

    public Vartotojas findById(int id) {
        return vartotojasRepository.findById(id);
    }

    public Iterable<Vartotojas> findAll() {
        return vartotojasRepository.findAll();
    }

    public Vartotojas save(Vartotojas vartotojas) {
       return vartotojasRepository.save(vartotojas);
    }

    public void deleteById(int id) {

        vartotojasRepository.deleteById(id);
        veiksmaiRepository.deleteAllByVartotojoId(id);
    }

    public void delete(Vartotojas vartotojas) {

        Vartotojas vartotojas1 = vartotojasRepository.findByTelNrAndVardas(vartotojas.getTelNr(), vartotojas.getVardas());
        vartotojasRepository.delete(vartotojas1);
        veiksmaiRepository.deleteAllByVartotojoId(vartotojas1.getId());
    }

    public Vartotojas replaceVartotojas(Vartotojas vartotojas, int id) {

        Vartotojas selected = vartotojasRepository.findById(id);
        if(selected == null) {
            Vartotojas newAdd = vartotojas;
            newAdd.setId(id);
            return vartotojasRepository.save(newAdd);
        } else {
            selected.setVardas(vartotojas.getVardas());
            selected.setTelNr(vartotojas.getTelNr());
            return vartotojasRepository.save(selected);
        }
    }

    public Vartotojas findByVardas(String vardas) {
        return vartotojasRepository.findByVardas(vardas);
    }

}

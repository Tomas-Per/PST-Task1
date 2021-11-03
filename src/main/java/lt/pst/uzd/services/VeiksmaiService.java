package lt.pst.uzd.services;

import lt.pst.uzd.models.Vartotojas;
import lt.pst.uzd.models.Veiksmai;
import lt.pst.uzd.repositories.VartotojasRepository;
import lt.pst.uzd.repositories.VeiksmaiRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class VeiksmaiService  {

    public VeiksmaiService(VeiksmaiRepository veiksmaiRepository, VartotojasRepository vartotojasRepository) {
        this.veiksmaiRepository = veiksmaiRepository;
        this.vartotojasRepository = vartotojasRepository;
    }

    private final VeiksmaiRepository veiksmaiRepository;
    private final VartotojasRepository vartotojasRepository;

    public Veiksmai findById(int id) {
        return veiksmaiRepository.findById(id);
    }

    public Veiksmai save(Veiksmai veiksmai){
        return veiksmaiRepository.save(veiksmai);
    }

    public Iterable<Veiksmai> findAll() {
        return veiksmaiRepository.findAll();
    }

    public void deleteById(int id) {
        veiksmaiRepository.deleteById(id);
    }

    public void deleteAllByVartotojoId(int id) {
        veiksmaiRepository.deleteAllByVartotojoId(id);
    }

    public Veiksmai replaceVeiksmas(Veiksmai veiksmas, int id) {

        Veiksmai selected = veiksmaiRepository.findById(id);
        if(selected == null) {
            veiksmas.setId(id);
            return veiksmaiRepository.save(veiksmas);
        } else {
            selected.setVeiksmas(veiksmas.getVeiksmas());
            selected.setVartotojoId(veiksmas.getVartotojoId());
            selected.setData(veiksmas.getData());
            return veiksmaiRepository.save(selected);
        }
    }

    public ArrayList<String> getNames() {
        Iterable<Veiksmai> veiksmai = veiksmaiRepository.findAll();
        ArrayList<String> names = new ArrayList<>();
        for(Veiksmai v: veiksmai) {
            int vartotojoId = v.getVartotojoId();
            Vartotojas vartotojas = vartotojasRepository.findById(vartotojoId);
            names.add(vartotojas.getVardas());
        }
        return names;
    }
}

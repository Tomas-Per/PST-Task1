package lt.pst.uzd.repositories;

import lt.pst.uzd.models.Vartotojas;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VartotojasRepository extends CrudRepository<Vartotojas, Integer> {

    Vartotojas findById(int id);

    Vartotojas save(Vartotojas vartotojas);

    Iterable<Vartotojas> findAll();

    void deleteById(int id);

    void delete(Vartotojas vartotojas);

    Vartotojas findByTelNrAndVardas(String telNr, String vardas);

    Vartotojas findByVardas(String vardas);

}

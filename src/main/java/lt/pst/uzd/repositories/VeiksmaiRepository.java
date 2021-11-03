package lt.pst.uzd.repositories;

import lt.pst.uzd.models.Veiksmai;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiksmaiRepository extends CrudRepository<Veiksmai, Integer> {

    Veiksmai findById(int id);

    Veiksmai save(Veiksmai veiksmai);

    Iterable<Veiksmai> findAll();

    void deleteById(int id);

    void deleteAllByVartotojoId(int id);


}

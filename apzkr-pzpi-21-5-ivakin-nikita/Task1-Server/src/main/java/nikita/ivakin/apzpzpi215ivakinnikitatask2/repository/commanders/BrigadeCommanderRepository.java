package nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.commanders;

import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.commanders.BrigadeCommander;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrigadeCommanderRepository extends JpaRepository<BrigadeCommander, Long> {

    /*Optional<BrigadeCommander> findBrigadeCommanderByBrigadeCommanderId(Integer id);
*/
    Optional<BrigadeCommander> findBrigadeCommanderByEmail(String email);

}

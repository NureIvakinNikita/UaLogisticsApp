package nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.commanders;

import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.commanders.BattalionCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.commanders.BrigadeCommander;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BattalionCommanderRepository extends JpaRepository<BattalionCommander, Long> {

    Optional<BattalionCommander> findBattalionCommanderById(Integer id);

    Optional<BattalionCommander> findBattalionCommanderByEmail(String email);

    List<BattalionCommander> findBattalionCommanderByBrigadeCommander(BrigadeCommander brigadeCommander);
}

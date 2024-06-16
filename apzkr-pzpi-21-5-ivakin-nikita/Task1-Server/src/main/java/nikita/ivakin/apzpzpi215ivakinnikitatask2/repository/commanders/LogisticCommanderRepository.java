package nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.commanders;

import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.commanders.LogisticCommander;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LogisticCommanderRepository extends JpaRepository<LogisticCommander, Long> {

    Optional<LogisticCommander> findLogisticCommanderByEmail(String email);

    Optional<LogisticCommander> findLogisticCommanderById(Integer id);

}

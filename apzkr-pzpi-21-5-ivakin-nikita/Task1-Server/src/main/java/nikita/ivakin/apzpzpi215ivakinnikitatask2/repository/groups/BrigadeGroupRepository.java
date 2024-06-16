package nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.groups;

import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.commanders.BrigadeCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.militaryGroups.BrigadeGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrigadeGroupRepository extends JpaRepository<BrigadeGroup, Long> {

    Optional<BrigadeGroup> findBrigadeGroupByBrigadeCommanderId(BrigadeCommander brigadeCommanderId);

    Optional<BrigadeGroup> findBrigadeGroupById(Integer id);
}

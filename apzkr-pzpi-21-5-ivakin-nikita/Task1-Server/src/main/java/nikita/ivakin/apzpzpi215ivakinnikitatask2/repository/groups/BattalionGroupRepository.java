package nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.groups;

import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.commanders.BattalionCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.militaryGroups.BattalionGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.militaryGroups.BrigadeGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface BattalionGroupRepository extends JpaRepository<BattalionGroup, Long> {

    Optional<BattalionGroup> findBattalionGroupById(Integer id);

    Optional<BattalionGroup> findBattalionGroupByBattalionCommanderId(BattalionCommander battalionCommanderId);

    ArrayList<BattalionGroup> findAllByBrigadeGroup(BrigadeGroup brigadeGroup);

    ArrayList<BattalionGroup> findAll();

}

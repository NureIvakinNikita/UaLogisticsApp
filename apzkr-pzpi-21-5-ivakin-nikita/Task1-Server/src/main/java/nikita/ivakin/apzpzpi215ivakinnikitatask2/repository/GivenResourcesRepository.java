package nikita.ivakin.apzpzpi215ivakinnikitatask2.repository;

import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.resources.GivenResources;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.enums.ResourcesType;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GivenResourcesRepository extends JpaRepository<GivenResources, Integer> {

    Optional<GivenResources> findGivenResourcesByCommanderIdAndMilitaryGroupIdAndRoleOfCommanderAndBrigadeCommanderIdAndAllocationOfResources(
            Integer commanderId, Integer militaryGroupId, Role roleOfCommander, Integer brigadeCommanderId, ResourcesType allocationOfResources
    );

    Optional<GivenResources> findGivenResourcesByMilitaryGroupIdAndRoleOfCommanderAndBrigadeCommanderId(Integer militaryGroupId, Role roleOfCommander, Integer brigadeCommanderId);
}

package nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.requests;

import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.requests.ResourcesRequest;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ResourcesRequestRepository extends JpaRepository<ResourcesRequest, Integer> {

    Optional<ResourcesRequest> findResourcesRequestByCommanderIdAndMilitaryGroupIdAndExactTimeAndRoleOfCommander(Integer commanderId, Integer militaryGroupId,
                                                                                                                 LocalDateTime exactTime, Role roleOfCommander);

}

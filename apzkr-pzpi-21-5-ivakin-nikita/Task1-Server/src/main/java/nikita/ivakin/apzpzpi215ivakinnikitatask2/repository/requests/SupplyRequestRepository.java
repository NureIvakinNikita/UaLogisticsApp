package nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.requests;

import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.requests.SupplyRequest;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupplyRequestRepository extends JpaRepository<SupplyRequest, Integer> {

    List<SupplyRequest> findSupplyRequestsByMilitaryGroupIdAndRoleOfCommander(Integer militaryGroupId, Role roleOfCommander);

    List<SupplyRequest> findSupplyRequestsBySeniorMilitaryGroupIdAndRoleOfCommander(Integer seniorMilitaryGroupId , Role roleOfCommander);

    List<SupplyRequest> findSupplyRequestsByBrigadeCommanderId(Integer brigadeCommanderId);

    Optional<SupplyRequest> findSupplyRequestByRequestId(Integer requestId);

}

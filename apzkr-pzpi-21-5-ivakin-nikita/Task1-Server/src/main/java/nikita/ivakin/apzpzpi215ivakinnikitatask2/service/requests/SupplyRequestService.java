package nikita.ivakin.apzpzpi215ivakinnikitatask2.service.requests;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.requests.SupplyRequest;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.enums.Role;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.exceptions.SupplyRequestNotFoundException;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.requests.SupplyRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class SupplyRequestService {


    private final SupplyRequestRepository supplyRequestRepository;


    public List<SupplyRequest> getSupplyRequestsForBrigadeByBrigadeId(Integer id, Role role) {
        List<SupplyRequest> supplyRequests = supplyRequestRepository.findSupplyRequestsByMilitaryGroupIdAndRoleOfCommander(id, role);
        if (supplyRequests == null || supplyRequests.size() == 0) {
            log.info("There aren't supply requests for brigade group with id " + id);
        }
        return supplyRequests;
    }

    public List<SupplyRequest> getSupplyRequestsForBattalionByBattalionId(Integer id, Role role) {
        List<SupplyRequest> supplyRequests = supplyRequestRepository.findSupplyRequestsByMilitaryGroupIdAndRoleOfCommander(id, role);
        if (supplyRequests == null || supplyRequests.size() == 0) {
            log.info("There aren't supply requests for battalion group with id " + id);
        }
        return supplyRequests;
    }

    public List<SupplyRequest> getSupplyRequestsForPlatByPlatId(Integer id, Role role) {
        List<SupplyRequest> supplyRequests = supplyRequestRepository.findSupplyRequestsByMilitaryGroupIdAndRoleOfCommander(id, role);
        if (supplyRequests == null || supplyRequests.size() == 0) {
            log.info("There aren't supply requests for plat group with id " + id);
        }
        return supplyRequests;
    }

    public List<SupplyRequest> getSupplyRequestsForCompanyByCompanyId(Integer id, Role role) {
        List<SupplyRequest> supplyRequests = supplyRequestRepository.findSupplyRequestsByMilitaryGroupIdAndRoleOfCommander(id, role);
        if (supplyRequests == null || supplyRequests.size() == 0) {
            log.info("There aren't supply requests for company group with id " + id);
        }
        return supplyRequests;
    }



    public List<SupplyRequest> getSupplyRequestsForPlatsByCompanyId(Integer id, Role role) {
        List<SupplyRequest> supplyRequests = supplyRequestRepository.findSupplyRequestsBySeniorMilitaryGroupIdAndRoleOfCommander(id, role);
        if (supplyRequests == null || supplyRequests.size() == 0) {
            log.info("There aren't supply requests for plat groups with company id " + id);
        }
        return supplyRequests;
    }

    public List<SupplyRequest> getSupplyRequestsForCompaniesByBattalionId(Integer id, Role role) {
        List<SupplyRequest> supplyRequests = supplyRequestRepository.findSupplyRequestsBySeniorMilitaryGroupIdAndRoleOfCommander(id, role);
        if (supplyRequests == null || supplyRequests.size() == 0) {
            log.info("There aren't supply requests for companies group with battalion id " + id);
        }
        return supplyRequests;
    }

    public List<SupplyRequest> getSupplyRequestsForBattalionByBrigadeId(Integer id, Role role) {
        List<SupplyRequest> supplyRequests = supplyRequestRepository.findSupplyRequestsBySeniorMilitaryGroupIdAndRoleOfCommander(id, role);
        if (supplyRequests == null || supplyRequests.size() == 0) {
            log.info("There aren't supply requests for battalion groups with brigade id " + id);
        }
        return supplyRequests;
    }

    public List<SupplyRequest> getAllSupplyRequestsByBrigadeCommanderId(Integer id) {
        List<SupplyRequest> supplyRequests = supplyRequestRepository.findSupplyRequestsByBrigadeCommanderId(id);
        if (supplyRequests == null || supplyRequests.size() == 0) {
            log.info("There aren't supply requests in brigade yet");
        }
        return supplyRequests;
    }

    public SupplyRequest getSupplyRequestById(Integer id) {
        Optional<SupplyRequest> tempSupplyRequest = supplyRequestRepository.findSupplyRequestByRequestId(id);
        if (tempSupplyRequest.isPresent()) {
            return tempSupplyRequest.get();
        } else {
            throw new SupplyRequestNotFoundException("There aren't any supply requests with id " + id);
        }
    }



    @Transactional
    public void save(SupplyRequest supplyRequest) {
        supplyRequestRepository.save(supplyRequest);
    }



}


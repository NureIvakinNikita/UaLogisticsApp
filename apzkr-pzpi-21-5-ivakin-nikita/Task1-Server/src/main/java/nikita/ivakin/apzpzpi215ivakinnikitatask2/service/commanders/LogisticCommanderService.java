package nikita.ivakin.apzpzpi215ivakinnikitatask2.service.commanders;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto.SupplyCarDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.SupplyCar;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.militaryGroups.LogisticCompany;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.requests.SupplyRequest;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.commanders.LogisticCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.enums.Role;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.enums.Status;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.exceptions.SupplyCarCreationException;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.commanders.LogisticCommanderRepository;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.SupplyCarService;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.groups.LogisticCompanyService;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.requests.SupplyRequestService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Slf4j
@AllArgsConstructor
public class LogisticCommanderService {

    private final LogisticCommanderRepository logisticCommanderRepository;
    private final SupplyRequestService supplyRequestService;
    private final LogisticCompanyService logisticCompanyService;
    private final SupplyCarService supplyCarService;

    public LogisticCommander getAuthenticatedLogisticCommander() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String brigadeCommanderEmail = authentication.getName();
        return findLogisticCommanderByEmail(brigadeCommanderEmail);
    }

    public List<SupplyRequest> getAllRequests() {
        LogisticCommander logisticCommander = getAuthenticatedLogisticCommander();
        List<SupplyRequest> supplyRequests = supplyRequestService.getSupplyRequestsForBrigadeByBrigadeId(logisticCommander.getBrigadeGroup().getId(), Role.BRIGADE_COMMANDER);
        //supplyRequests.addAll(supplyRequestService.getAllSupplyRequestsByBrigadeCommanderId(logisticCommander.getBrigadeCommander().getId()));
        return supplyRequests;
    }

    public LogisticCommander findLogisticCommanderByEmail(String email) {
        Optional<LogisticCommander> tempLogCom = logisticCommanderRepository.findLogisticCommanderByEmail(email);
        if (tempLogCom.isPresent()) {
            return tempLogCom.get();
        } else {
            log.info("Error logistic commander with email" + email  + " doesn't exist.");
        }
        return null;
    }

    public LogisticCommander findLogisticCommanderById(Integer logisticCommanderId) {
        Optional<LogisticCommander> tempLogCommander = logisticCommanderRepository.findLogisticCommanderById(logisticCommanderId);
        if (tempLogCommander.isPresent()) {
            return tempLogCommander.get();
        } else {
            log.info("Error logistic commander with id" + logisticCommanderId + " doesn't exist.");
        }
        return null;
    }
    //Added exceptions
    public boolean takeExecutionOfRequest(Integer id) {
        LogisticCommander logisticCommander = getAuthenticatedLogisticCommander();
        SupplyRequest supplyRequest = supplyRequestService.getSupplyRequestById(id);
        supplyRequest.setStatus(Status.EXECUTING);
        supplyRequest.setExecutiveCommanderId(logisticCommander.getId());
        supplyRequest.setExecutiveGroupId(logisticCommander.getLogisticCompany().getId());
        supplyRequest.setRoleOfExecutiveCommander(Role.LOGISTIC_COMMANDER);
        supplyRequest.setDateOfExecuting(LocalDate.now());
        supplyRequestService.save(supplyRequest);
        return true;
    }

    public boolean confirmDeliveryOfRequest(Integer id) {
        LogisticCommander logisticCommander = getAuthenticatedLogisticCommander();
        SupplyRequest supplyRequest = supplyRequestService.getSupplyRequestById(id);
        supplyRequest.setDeliveryComplitionDate(LocalDate.now());
        supplyRequest.setStatus(Status.GIVEN_TO_BRIGADE);
        supplyRequestService.save(supplyRequest);
        return true;
    }

    @Transactional
    public void save(LogisticCommander logisticCommander){
        logisticCommanderRepository.save(logisticCommander);
    }

    //add a check for executive commander
    public boolean createSupplyCar(SupplyCarDTO supplyCarDTO) {
        LogisticCommander logisticCommander = getAuthenticatedLogisticCommander();
        LogisticCompany logisticCompany = logisticCompanyService.findLogisticCompanyByLogisticCommander(logisticCommander);
        SupplyRequest supplyRequest = supplyRequestService.getSupplyRequestById(supplyCarDTO.getSupplyRequestId());
        try {
            Random rand = new Random();
            SupplyCar supplyCar = new SupplyCar();
            supplyCar.setTier(rand.nextInt(30 - 10 + 1) + 5);

            supplyCar.setSupplyRequest(supplyRequest);
            supplyCar.setLogisticCompany(logisticCompany);
            supplyRequest.setStatus(Status.CAR_ASSIGNED_EXECUTING);
            supplyCarService.save(supplyCar);
            supplyRequestService.save(supplyRequest);
        } catch (Exception e) {
            throw new SupplyCarCreationException("Error in creating supply car.");
        }
        return true;
    }
}

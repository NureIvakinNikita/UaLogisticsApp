package nikita.ivakin.apzpzpi215ivakinnikitatask2.service.commanders;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto.ResourcesRequestDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto.commanders.BattalionCommanderDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto.commanders.BrigadeCommanderDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto.groups.BattalionGroupDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto.groups.BrigadeGroupDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto.groups.LogisticCompanyDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.resources.GivenResources;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.requests.ResourcesRequest;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.resources.ResourcesUpdateResponse;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.requests.SupplyRequest;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.commanders.BattalionCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.commanders.BrigadeCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.commanders.LogisticCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.militaryGroups.BattalionGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.militaryGroups.BrigadeGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.militaryGroups.LogisticCompany;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.enums.ResourcesType;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.enums.Role;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.enums.Status;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.exceptions.*;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.commanders.BrigadeCommanderRepository;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.GivenResourcesService;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.groups.BrigadeGroupService;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.groups.LogisticCompanyService;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.requests.ResourcesRequestService;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.requests.SupplyRequestService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class BrigadeCommanderService {

    private final BrigadeCommanderRepository brigadeCommanderRepository;
    private final BrigadeGroupService brigadeGroupService;
    private final LogisticCompanyService logisticCompanyService;
    private final BattalionCommanderService battalionCommanderService;
    private final ResourcesRequestService resourcesRequestService;
    private final SupplyRequestService supplyRequestService;
    private final GivenResourcesService givenResourcesService;
    private final LogisticCommanderService logisticCommanderService;



    public BrigadeCommander getAuthenticatedBrigadeCommander() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String brigadeCommanderEmail = authentication.getName();
            return findBrigadeCommanderByEmail(brigadeCommanderEmail);
        } catch (Exception e) {
            throw new CommanderAuthenticationException("Error in getting authenticated brigade commander.");
        }

    }



    private boolean fillGivenResources(BrigadeGroupDTO brigadeGroupDTO, BrigadeCommander brigadeCommander){
        GivenResources givenResources = GivenResources.builder()
                .commanderId(brigadeCommander.getId())
                .militaryGroupId(brigadeCommander.getBrigadeGroupId().getId())
                .brigadeCommanderId(brigadeCommander.getId())
                .roleOfCommander(brigadeCommander.getRole())
                .issueDate(LocalDate.now())
                .allocationOfResources(ResourcesType.FOR_PERFORMING_A_MISSION)
                .ammo40mmGpCount(brigadeGroupDTO.getAmmo40mmGpCount())
                .ammo40mmRpgCount(brigadeGroupDTO.getAmmo40mmRpgCount())
                .ammo145KpvtCount(brigadeGroupDTO.getAmmo145KpvtCount())
                .ammo545x39AkRpkCount(brigadeGroupDTO.getAmmo545x39AkRpkCount())
                .ammo556x45ArCount(brigadeGroupDTO.getAmmo556x45ArCount())
                .ammo762PktCount(brigadeGroupDTO.getAmmo762PktCount())
                .ammo762x39AkCount(brigadeGroupDTO.getAmmo762x39AkCount())
                .offensiveGrenadesCount(brigadeGroupDTO.getOffensiveGrenadesCount())
                .defensiveGrenadesCount(brigadeGroupDTO.getDefensiveGrenadesCount())
                .riflesCount(brigadeGroupDTO.getRiflesCount())
                .machineGunsCount(brigadeGroupDTO.getMachineGunsCount())
                .bodyArmorCount(brigadeGroupDTO.getBodyArmorCount())
                .helmetsCount(brigadeGroupDTO.getHelmetsCount())
                .apcCount(brigadeGroupDTO.getApcCount())
                .tankCount(brigadeGroupDTO.getTankCount())
                .foodCount(brigadeGroupDTO.getFoodCount())
                .dryRationsCount(brigadeGroupDTO.getDryRationsCount())
                .build();
        try {
            givenResourcesService.save(givenResources);
        } catch (Exception e) {
            throw new GivenResourcesCreationException("Something went wrong while creating given resources in creation of brigade.");
        }
        return true;
    }

    //TO DO: work with return statement
    public boolean createBrigade(BrigadeGroupDTO brigadeGroupDTO) {
        BrigadeCommander brigadeCommander = getAuthenticatedBrigadeCommander();
        brigadeGroupService.createBrigadeGroup(brigadeGroupDTO, brigadeCommander);
        BrigadeGroup brigadeGroup = brigadeGroupService.findBrigadeGroupByBrigadeCommander(brigadeCommander);
        brigadeCommander.setBrigadeGroupId(brigadeGroup);

        fillGivenResources(brigadeGroupDTO, brigadeCommander);

        save(brigadeCommander);
        return true;
    }

    public boolean createLogisticCompany(LogisticCompanyDTO logisticCompanyDTO) {
        BrigadeCommander brigadeCommander = getAuthenticatedBrigadeCommander();
        return logisticCompanyService.createLogisticCompany(logisticCompanyDTO, brigadeCommander);
    }

    public boolean createBattalion(BattalionGroupDTO battalionGroupDTO) {
        BrigadeCommander brigadeCommander = getAuthenticatedBrigadeCommander();
        return battalionCommanderService.getBattalionGroupService().createBattalionGroup(battalionGroupDTO, brigadeCommander);
    }

    public boolean assignLogisticCompanyCommander(Integer logisticCommanderId, Integer logisticCompanyId){
        BrigadeCommander brigadeCommander = getAuthenticatedBrigadeCommander();
        LogisticCommander logisticCommander = logisticCommanderService.findLogisticCommanderById(logisticCommanderId);
        LogisticCompany logisticCompany = logisticCompanyService.findLogisticCompanyById(logisticCompanyId);
        givenResourcesService.assignCommander(logisticCommanderId, logisticCompanyId, brigadeCommander.getId(), Role.LOGISTIC_COMMANDER);
        logisticCompany.setLogisticCommanderId(logisticCommander);
        logisticCommander.setLogisticCompany(logisticCompany);
        logisticCommander.setBrigadeCommander(brigadeCommander);
        logisticCommander.setBrigadeGroup(brigadeCommander.getBrigadeGroupId());
        try {
            logisticCommanderService.save(logisticCommander);
            logisticCompanyService.save(logisticCompany);
        } catch (Exception e) {
            throw new CommanderAssigningException("Something went wrong in assigning LogisticCommander with id" + logisticCommanderId);
        }
        return true;
    }

    public boolean assignBattalionCommander(Integer battalionCommanderId, Integer battalionGroupId) {
        BrigadeCommander brigadeCommander = getAuthenticatedBrigadeCommander();
        BattalionCommander battalionCommander = battalionCommanderService.findBattalionCommanderById(battalionCommanderId);
        if (battalionCommander.getBattalionGroup() != null) {
            throw new CommanderAssigningException("BattalionCommander with such id already assigned.");
        }
        BattalionGroup battalionGroup = battalionCommanderService.getBattalionGroupService().findBattalionGroupById(battalionGroupId);
        givenResourcesService.assignCommander(battalionCommanderId, battalionGroupId, brigadeCommander.getId(), Role.BATTALION_COMMANDER);
        battalionGroup.setBattalionCommanderId(battalionCommander);
        battalionCommander.setBattalionGroup(battalionGroup);
        battalionCommander.setBrigadeCommander(brigadeCommander);
        battalionCommander.setBrigadeGroup(brigadeCommander.getBrigadeGroupId());
        try {
            battalionCommanderService.save(battalionCommander);
            battalionCommanderService.getBattalionGroupService().save(battalionGroup);
        } catch (Exception e) {
            throw new CommanderAssigningException("Something went wrong in assigning BattalionCommander with id" + battalionCommanderId);
        }
        return true;
    }
    @Transactional
    public void save(BrigadeCommander brigadeCommander) {
        brigadeCommanderRepository.save(brigadeCommander);
    }


    public BrigadeCommander findBrigadeCommanderByEmail(String email) {
        Optional<BrigadeCommander> tempBrigCom = brigadeCommanderRepository.findBrigadeCommanderByEmail(email);
        if (tempBrigCom.isPresent()) {
            return tempBrigCom.get();
        } else {
            throw new CommanderNotFoundException("Error brigade commander with email" + email  + " doesn't exist.");
        }
    }

    public boolean askForResources(ResourcesRequestDTO resourcesRequestDTO) {
        BrigadeCommander brigadeCommander = getAuthenticatedBrigadeCommander();
        ResourcesRequest resourcesRequest = ResourcesRequest.builder()
                .commanderId(brigadeCommander.getId())
                .militaryGroupId(brigadeCommander.getBrigadeGroupId().getId())
                .roleOfCommander(brigadeCommander.getRole())
                .ammo40mmGpCount(resourcesRequestDTO.getAmmo40mmGpCount())
                .ammo40mmRpgCount(resourcesRequestDTO.getAmmo40mmRpgCount())
                .ammo145KpvtCount(resourcesRequestDTO.getAmmo145KpvtCount())
                .ammo545x39AkRpkCount(resourcesRequestDTO.getAmmo545x39AkRpkCount())
                .ammo556x45ArCount(resourcesRequestDTO.getAmmo556x45ArCount())
                .ammo762PktCount(resourcesRequestDTO.getAmmo762PktCount())
                .ammo762x39AkCount(resourcesRequestDTO.getAmmo762x39AkCount())
                .offensiveGrenadesCount(resourcesRequestDTO.getOffensiveGrenadesCount())
                .defensiveGrenadesCount(resourcesRequestDTO.getDefensiveGrenadesCount())
                .riflesCount(resourcesRequestDTO.getRiflesCount())
                .bodyArmorCount(resourcesRequestDTO.getBodyArmorCount())
                .helmetsCount(resourcesRequestDTO.getHelmetsCount())
                .apcCount(resourcesRequestDTO.getApcCount())
                .tankCount(resourcesRequestDTO.getTankCount())
                .exactTime(LocalDateTime.now())
                .build();
        SupplyRequest supplyRequest = SupplyRequest.builder()
                .seniorMilitaryGroupId(null)
                .commanderId(brigadeCommander.getId())
                .militaryGroupId(brigadeCommander.getBrigadeGroupId().getId())
                .roleOfCommander(brigadeCommander.getRole())
                .dateOfRequest(LocalDate.now())
                .status(Status.NOT_PROCESSED)
                .build();
        try {
            resourcesRequestService.save(resourcesRequest);
        } catch (Exception e) {
            throw new ResourcesRequestCreationException("Something went wrong while creating resources request.", e);
        }
        resourcesRequest = resourcesRequestService.findResourcesRequestByCommanderIdAndMilitaryGroupIdAndExactTime(brigadeCommander.getId(), brigadeCommander.getBrigadeGroupId().getId(), resourcesRequest.getExactTime(), Role.BRIGADE_COMMANDER);
        supplyRequest.setResourcesRequestId(resourcesRequest);
        try {
            supplyRequestService.save(supplyRequest);
        } catch (Exception e) {
            throw new SupplyRequestCreationException("Something went wrong while creating supply request.", e);
        }
        return true;
    }

    public List<SupplyRequest> getBrigadeRequests() {
        BrigadeCommander brigadeCommander = getAuthenticatedBrigadeCommander();
        return supplyRequestService.getSupplyRequestsForBrigadeByBrigadeId(brigadeCommander.getBrigadeGroupId().getId(), brigadeCommander.getRole());
    }

    public List<SupplyRequest> getBattalionRequests() {
        BrigadeCommander brigadeCommander = getAuthenticatedBrigadeCommander();
        return supplyRequestService.getSupplyRequestsForBattalionByBrigadeId(brigadeCommander.getBrigadeGroupId().getId(), Role.BATTALION_COMMANDER);
    }

    public boolean validateResources(BrigadeGroupDTO brigadeGroupDTO) {
        BrigadeCommander brigadeCommander = getAuthenticatedBrigadeCommander();
        GivenResources givenResources = givenResourcesService.getGivenResources(
                brigadeCommander.getId(), brigadeCommander.getBrigadeGroupId().getId(), brigadeCommander.getRole(), brigadeCommander.getId(), ResourcesType.FOR_PERFORMING_A_MISSION
        );
        return brigadeGroupDTO.getAmmo762PktCount() >= givenResources.getAmmo762PktCount() / 4 && brigadeGroupDTO.getAmmo556x45ArCount() >= givenResources.getAmmo556x45ArCount() / 4
                && brigadeGroupDTO.getAmmo545x39AkRpkCount() >= givenResources.getAmmo545x39AkRpkCount() / 4 && brigadeGroupDTO.getAmmo762x39AkCount() >= givenResources.getAmmo762x39AkCount() / 4
                && brigadeGroupDTO.getAmmo145KpvtCount() >= givenResources.getAmmo145KpvtCount() / 4 && brigadeGroupDTO.getAmmo40mmGpCount() >= givenResources.getAmmo40mmGpCount() / 4
                && brigadeGroupDTO.getAmmo40mmRpgCount() >= givenResources.getAmmo40mmRpgCount() / 4 && brigadeGroupDTO.getBodyArmorCount() >= givenResources.getBodyArmorCount()
                && brigadeGroupDTO.getHelmetsCount() >= givenResources.getHelmetsCount() && brigadeGroupDTO.getApcCount() >= givenResources.getApcCount();
    }

    public ResourcesUpdateResponse updateBrigadeResources(BrigadeGroupDTO brigadeGroupDTO) {
        boolean validationResult;
        boolean updateResult;
        try {
            validationResult = !validateResources(brigadeGroupDTO);
            updateResult = brigadeGroupService.updateBrigadeResources(brigadeGroupDTO);
        } catch (Exception e) {
            throw new MilitaryGroupUpdateException("Error updating resources in brigade.");
        }

        return new ResourcesUpdateResponse(updateResult, validationResult);
    }

    public ResourcesUpdateResponse sendResourcesToBattalion(SupplyRequest supplyRequest) {
        BrigadeCommander brigadeCommander = getAuthenticatedBrigadeCommander();
        if (supplyRequest.getBrigadeCommanderId().equals(brigadeCommander.getId()) && supplyRequest.getSeniorMilitaryGroupId().equals(brigadeCommander.getBrigadeGroupId().getId())
                && supplyRequest.getRoleOfCommander().equals(Role.BATTALION_COMMANDER)){
            BattalionGroup battalionGroup = battalionCommanderService.getBattalionGroupService().findBattalionGroupById(supplyRequest.getMilitaryGroupId());
            ResourcesUpdateResponse resourcesUpdateResponse = allocateResources(supplyRequest.getResourcesRequestId(), brigadeCommander.getBrigadeGroupId(), brigadeCommander, battalionGroup);
            supplyRequest.setStatus(Status.EXECUTING);
            supplyRequest.setDateOfExecuting(LocalDate.now());
            supplyRequest.setExecutiveCommanderId(brigadeCommander.getId());
            supplyRequest.setExecutiveGroupId(brigadeCommander.getBrigadeGroupId().getId());
            supplyRequest.setRoleOfExecutiveCommander(Role.BRIGADE_COMMANDER);
            supplyRequestService.save(supplyRequest);
            return resourcesUpdateResponse;
        }
        return new ResourcesUpdateResponse(false, false);
    }

    public ResourcesUpdateResponse allocateResources(ResourcesRequest resourcesRequest, BrigadeGroup brigadeGroup, BrigadeCommander brigadeCommander, BattalionGroup battalionGroup) {
        boolean allocated = false;
        try {
            allocated = givenResourcesService.allocateResources(resourcesRequest, brigadeGroup, battalionGroup,
                    battalionGroup.getBattalionCommanderId().getId(), battalionGroup.getBattalionCommanderId().getRole(), battalionGroup.getBattalionCommanderId().getBrigadeCommander().getId(), 4);

        } catch (Exception e) {
            throw new GivenResourcesCreationException("Something went wrong in allocation resources for battalion group");
        }

        return new ResourcesUpdateResponse(allocated, !validateResources(brigadeGroupService.mapBrigadeGroupToDTO(brigadeGroup, mapBrigadeCommanderToDTO(brigadeCommander))));
    }

    public List<BattalionGroupDTO> getBrigadeBattalionGroups() {
        BrigadeCommander brigadeCommander = getAuthenticatedBrigadeCommander();
        List<BattalionGroupDTO> battalionGroupDTOS = battalionCommanderService.getBattalionGroupService().findBattalionGroupsByBrigadeGroupId(brigadeCommander.getBrigadeGroupId());
        List<BattalionCommanderDTO> battalionCommanderDTOS = getBrigadeBattalionCommanders();
        for (BattalionGroupDTO battalionGroupDTO : battalionGroupDTOS) {
            for (BattalionCommanderDTO battalionCommanderDTO : battalionCommanderDTOS) {
                if (battalionCommanderDTO.getBattalionGroupId().equals(battalionGroupDTO.getId())) {
                    battalionGroupDTO.setCommander(battalionCommanderDTO);
                }
            }
        }
        return battalionGroupDTOS;

    }

    private List<BattalionCommanderDTO> getBrigadeBattalionCommanders() {
        BrigadeCommander brigadeCommander = getAuthenticatedBrigadeCommander();
        List<BattalionCommanderDTO> battalionCommanderDTOS = battalionCommanderService.findBattalionCommanderByBrigadeCommander(brigadeCommander);
        return battalionCommanderDTOS;
    }

    public BrigadeGroupDTO getBrigadeGroup() {
        BrigadeCommander brigadeCommander = getAuthenticatedBrigadeCommander();
        BrigadeGroup brigadeGroup = brigadeGroupService.findBrigadeGroupByBrigadeCommander(brigadeCommander);
        return brigadeGroupService.mapBrigadeGroupToDTO(brigadeGroup, mapBrigadeCommanderToDTO(brigadeCommander));
    }

    public boolean confirmGettingOfResources(Integer supplyRequestId) {
        try {
            SupplyRequest supplyRequest = supplyRequestService.getSupplyRequestById(supplyRequestId);
            supplyRequest.setStatus(Status.FINISHED);
            supplyRequest.setExecutionСomplitionDate(LocalDate.now());
            supplyRequestService.save(supplyRequest);
            return true;
        } catch (Exception e) {
            throw new SupplyRequestUpdateException("Error in updating supply request in brigade commander service.");
        }
    }


    private BrigadeCommanderDTO mapBrigadeCommanderToDTO(BrigadeCommander brigadeCommander) {
        return BrigadeCommanderDTO.builder()
                .id(brigadeCommander.getId())
                .firstName(brigadeCommander.getFirstName())
                .lastName(brigadeCommander.getLastName())
                .secondName(brigadeCommander.getSecondName())
                .email(brigadeCommander.getEmail())
                .rank(brigadeCommander.getRank())
                .role(brigadeCommander.getRole())
                .passportNumber(brigadeCommander.getPassportNumber())
                .brigadeGroupId(brigadeCommander.getBrigadeGroupId().getId())
                .build();
    }
}


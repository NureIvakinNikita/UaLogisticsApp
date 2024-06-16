package nikita.ivakin.apzpzpi215ivakinnikitatask2.service.commanders;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto.ResourcesRequestDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto.commanders.BattalionCommanderDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto.commanders.CompanyCommanderDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto.groups.BattalionGroupDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto.groups.CompanyGroupDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.resources.GivenResources;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.requests.ResourcesRequest;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.resources.ResourcesUpdateResponse;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.requests.SupplyRequest;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.commanders.BattalionCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.commanders.BrigadeCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.commanders.CompanyCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.militaryGroups.BattalionGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.militaryGroups.CompanyGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.enums.ResourcesType;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.enums.Role;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.enums.Status;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.exceptions.*;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.commanders.BattalionCommanderRepository;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.GivenResourcesService;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.groups.BattalionGroupService;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.groups.CompanyGroupService;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.requests.ResourcesRequestService;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.requests.SupplyRequestService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class BattalionCommanderService {

    private final BattalionCommanderRepository battalionCommanderRepository;
    private final BattalionGroupService battalionGroupService;
    private final CompanyCommanderService companyCommanderService;
    private final CompanyGroupService companyGroupService;
    private final ResourcesRequestService resourcesRequestService;
    private final SupplyRequestService supplyRequestService;
    private final GivenResourcesService givenResourcesService;



    public BattalionCommander getAuthenticatedBattalionCommander() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String battalionCommanderEmail = authentication.getName();
            return findBattalionCommanderByEmail(battalionCommanderEmail);
        } catch (Exception e) {
            throw new CommanderAuthenticationException("Error in getting authenticated battalion commander.");
        }
    }

    public boolean createCompany(CompanyGroupDTO companyGroupDTO) {
        BattalionCommander battalionCommander = getAuthenticatedBattalionCommander();
        if (battalionCommander.getBattalionGroup() == null) {
            throw new CommanderDoesNotAssignedException("Commander isn't assigned to any battalion, creating a company isn't possible.");
        }
        return companyGroupService.createCompanyGroup(companyGroupDTO, battalionCommander);
    }

    public boolean assignCompanyCommander(Integer companyCommanderId, Integer companyGroupId){
        BattalionCommander battalionCommander = getAuthenticatedBattalionCommander();
        if (battalionCommander.getBattalionGroup() == null) {
            throw new CommanderDoesNotAssignedException("Commander isn't assigned to any battalion, assigning a company commander isn't possible.");
        }
        CompanyCommander companyCommander = companyCommanderService.findCompanyCommanderById(companyCommanderId);
        CompanyGroup companyGroup = companyGroupService.findCompanyGroupById(companyGroupId);
        givenResourcesService.assignCommander(companyCommanderId, companyGroupId, battalionCommander.getBrigadeCommander().getId(), Role.COMPANY_COMMANDER);
        companyGroup.setCompanyCommanderId(companyCommander);
        companyCommander.setCompanyGroup(companyGroup);
        companyCommander.setBattalionCommander(battalionCommander);
        companyCommander.setBattalionGroup(battalionCommander.getBattalionGroup());
        companyCommander.setBrigadeCommanderId(battalionCommander.getBrigadeCommander().getId());

        try {
            companyCommanderService.save(companyCommander);
            companyGroupService.save(companyGroup);
        } catch (Exception e) {
            log.info("Something went wrong in assigning CompanyCommander.");
            return false;
        }

        return true;
    }



    public BattalionCommander findBattalionCommanderByEmail(String email) {
        Optional<BattalionCommander> tempBatCom = battalionCommanderRepository.findBattalionCommanderByEmail(email);
        if (tempBatCom.isPresent()) {
            return tempBatCom.get();
        } else {
            throw new CommanderNotFoundException("Error battalion commander with email" + email  + " doesn't exist.");
        }
    }

    public BattalionCommander findBattalionCommanderById(Integer id) {
        Optional<BattalionCommander> tempBatCom = battalionCommanderRepository.findBattalionCommanderById(id);
        if (tempBatCom.isPresent()) {
            return tempBatCom.get();
        } else {
            throw new CommanderNotFoundException("Error battalion commander with id" + id + " doesn't exist.");
        }
    }

    public List<SupplyRequest> getBattalionRequests() {
        BattalionCommander battalionCommander = getAuthenticatedBattalionCommander();
        return supplyRequestService.getSupplyRequestsForBattalionByBattalionId(battalionCommander.getBattalionGroup().getId(), battalionCommander.getRole());
    }

    public List<SupplyRequest> getCompaniesRequests() {
        BattalionCommander battalionCommander = getAuthenticatedBattalionCommander();
        return supplyRequestService.getSupplyRequestsForCompaniesByBattalionId(battalionCommander.getBattalionGroup().getId(), Role.COMPANY_COMMANDER);
    }

    public BattalionGroupService getBattalionGroupService() {
        return battalionGroupService;
    }

    @Transactional
    public void save(BattalionCommander battalionCommander) {
        battalionCommanderRepository.save(battalionCommander);
    }

    public boolean askForResource(ResourcesRequestDTO resourcesRequestDTO) {
        BattalionCommander battalionCommander = getAuthenticatedBattalionCommander();
        ResourcesRequest resourcesRequest = ResourcesRequest.builder()
                .commanderId(battalionCommander.getId())
                .militaryGroupId(battalionCommander.getBattalionGroup().getId())
                .roleOfCommander(battalionCommander.getRole())
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
                .brigadeCommanderId(battalionCommander.getBrigadeCommander().getId())
                .seniorMilitaryGroupId(battalionCommander.getBrigadeGroup().getId())
                .commanderId(battalionCommander.getId())
                .militaryGroupId(battalionCommander.getBattalionGroup().getId())
                .roleOfCommander(battalionCommander.getRole())
                .dateOfRequest(LocalDate.now())
                .status(Status.NOT_PROCESSED)
                .build();
        try {
            resourcesRequestService.save(resourcesRequest);
        } catch (Exception e) {
            throw new ResourcesRequestCreationException("Something went wrong in creation resources request in battalion commander method ask for resources.");
        }
        resourcesRequest = resourcesRequestService.findResourcesRequestByCommanderIdAndMilitaryGroupIdAndExactTime(battalionCommander.getId(), battalionCommander.getBattalionGroup().getId(), resourcesRequest.getExactTime(), Role.BATTALION_COMMANDER);
        supplyRequest.setResourcesRequestId(resourcesRequest);
        try {
            supplyRequestService.save(supplyRequest);
        } catch (Exception e) {
            throw new SupplyRequestCreationException("Something went wrong in creation supply request in battalion commander method ask for resources.");
        }
        return true;
    }

    public boolean validateResources(BattalionGroupDTO battalionGroupDTO) {

        BattalionCommander battalionCommander = getAuthenticatedBattalionCommander();
        GivenResources givenResources = givenResourcesService.getGivenResources(
                battalionCommander.getId(), battalionCommander.getBattalionGroup().getId(), battalionCommander.getRole(), battalionCommander.getBrigadeCommander().getId(), ResourcesType.FOR_PERFORMING_A_MISSION
        );
        return battalionGroupDTO.getAmmo762PktCount() >= givenResources.getAmmo762PktCount() / 4 && battalionGroupDTO.getAmmo556x45ArCount() >= givenResources.getAmmo556x45ArCount() / 4
                && battalionGroupDTO.getAmmo545x39AkRpkCount() >= givenResources.getAmmo545x39AkRpkCount() / 4 && battalionGroupDTO.getAmmo762x39AkCount() >= givenResources.getAmmo762x39AkCount() / 4
                && battalionGroupDTO.getAmmo145KpvtCount() >= givenResources.getAmmo145KpvtCount() / 4 && battalionGroupDTO.getAmmo40mmGpCount() >= givenResources.getAmmo40mmGpCount() / 4
                && battalionGroupDTO.getAmmo40mmRpgCount() >= givenResources.getAmmo40mmRpgCount() / 4 && battalionGroupDTO.getBodyArmorCount() >= givenResources.getBodyArmorCount()
                && battalionGroupDTO.getHelmetsCount() >= givenResources.getHelmetsCount() && battalionGroupDTO.getApcCount() >= givenResources.getApcCount();

    }


    public ResourcesUpdateResponse updateBattalionResources(BattalionGroupDTO battalionGroupDTO) {
        boolean validationResult;
        boolean updateResult;
        try {
            validationResult = !validateResources(battalionGroupDTO);
            updateResult = battalionGroupService.updateBattalionResources(battalionGroupDTO);
        } catch (Exception e) {
            throw new MilitaryGroupUpdateException("Something went wrong in updating battalion resources.", e);
        }

        return new ResourcesUpdateResponse(updateResult, validationResult);
    }

    public ResourcesUpdateResponse sendResourcesToCompany(SupplyRequest supplyRequest) {
        try {

            BattalionCommander battalionCommander = getAuthenticatedBattalionCommander();
            if (supplyRequest.getBrigadeCommanderId().equals(battalionCommander.getBrigadeCommander().getId()) && supplyRequest.getSeniorMilitaryGroupId().equals(battalionCommander.getBattalionGroup().getId())
                    && supplyRequest.getRoleOfCommander().equals(Role.COMPANY_COMMANDER)) {
                CompanyGroup companyGroup = companyGroupService.findCompanyGroupById(supplyRequest.getMilitaryGroupId());
                ResourcesUpdateResponse resourcesUpdateResponse = allocateResources(supplyRequest.getResourcesRequestId(), battalionCommander.getBattalionGroup(), battalionCommander, companyGroup);
                supplyRequest.setStatus(Status.EXECUTING);
                supplyRequest.setDateOfExecuting(LocalDate.now());
                supplyRequest.setExecutiveCommanderId(battalionCommander.getId());
                supplyRequest.setExecutiveGroupId(battalionCommander.getBattalionGroup().getId());
                supplyRequest.setRoleOfExecutiveCommander(Role.BATTALION_COMMANDER);
                supplyRequestService.save(supplyRequest);
                return resourcesUpdateResponse;
            }
        } catch (Exception e) {
            throw new CommanderSendingResourcesException("Error in sending resources to company.");
        }

        return new ResourcesUpdateResponse(false, false);
    }

    public ResourcesUpdateResponse allocateResources(ResourcesRequest resourcesRequest, BattalionGroup battalionGroup, BattalionCommander battalionCommander, CompanyGroup companyGroup) {
        boolean allocated = givenResourcesService.allocateResources(resourcesRequest, battalionGroup, companyGroup,
                companyGroup.getCompanyCommanderId().getId(), companyGroup.getCompanyCommanderId().getRole(),
                companyGroup.getCompanyCommanderId().getBrigadeCommanderId(), 4);
        battalionGroupService.save(battalionGroup);
        companyGroupService.save(companyGroup);

        return new ResourcesUpdateResponse(allocated, !validateResources(battalionGroupService.mapBattalionGroupToDTO(battalionGroup)));
    }

    public List<CompanyGroupDTO> getBattalionCompanyGroups(){
        BattalionCommander battalionCommander = getAuthenticatedBattalionCommander();
        List<CompanyGroupDTO> companyGroupDTOS = companyGroupService.findCompanyGroupsByBattalionGroupId(battalionCommander.getBattalionGroup());
        List<CompanyCommanderDTO> companyCommanderDTOS = getBattalionCompanyCommanders();
        for (CompanyGroupDTO companyGroupDTO : companyGroupDTOS) {
            for (CompanyCommanderDTO companyCommanderDTO : companyCommanderDTOS) {
                if (companyCommanderDTO.getCompanyGroupId().equals(companyGroupDTO.getId())) {
                    companyGroupDTO.setCompanyCommanderDTO(companyCommanderDTO);
                }
            }
        }
        return companyGroupDTOS;
    }

    public List<CompanyCommanderDTO> getBattalionCompanyCommanders(){
        BattalionCommander battalionCommander = getAuthenticatedBattalionCommander();
        List<CompanyCommanderDTO> companyCommanderDTOS = companyCommanderService.findCompanyCommanderByBattalionCommander(battalionCommander);
        return companyCommanderDTOS;
    }

    public BattalionGroupDTO getBattalionGroup() {
        BattalionCommander battalionCommander = getAuthenticatedBattalionCommander();
        BattalionGroup battalionGroup = battalionGroupService.findBattalionGroupByBattalionCommander(battalionCommander);
        return battalionGroupService.mapBattalionGroupToDTO(battalionGroup);
    }

    public boolean confirmGettingOfResources(Integer supplyRequestId) {
        try {
            SupplyRequest supplyRequest = supplyRequestService.getSupplyRequestById(supplyRequestId);
            supplyRequest.setStatus(Status.FINISHED);
            supplyRequest.setExecutionСomplitionDate(LocalDate.now());
            supplyRequestService.save(supplyRequest);
            return true;
        } catch (Exception e) {
            throw new SupplyRequestUpdateException("Error in updating supply request in battalion commander service.");
        }
    }

    public List<BattalionCommanderDTO> findBattalionCommanderByBrigadeCommander(BrigadeCommander brigadeCommander) {
        List<BattalionCommander> battalionCommanders = battalionCommanderRepository.findBattalionCommanderByBrigadeCommander(brigadeCommander);
        List<BattalionCommanderDTO> battalionCommanderDTOS = new ArrayList<>();
        for (BattalionCommander battalionCommander : battalionCommanders) {
            battalionCommanderDTOS.add(mapBattalionCommanderToDTO(battalionCommander));
        }
        return battalionCommanderDTOS;
    }

    private BattalionCommanderDTO mapBattalionCommanderToDTO(BattalionCommander battalionCommander) {
        return BattalionCommanderDTO.builder()
                .id(battalionCommander.getId())
                .firstName(battalionCommander.getFirstName())
                .lastName(battalionCommander.getLastName())
                .secondName(battalionCommander.getSecondName())
                .email(battalionCommander.getEmail())
                .rank(battalionCommander.getRank())
                .role(battalionCommander.getRole())
                .passportNumber(battalionCommander.getPassportNumber())
                .battalionGroupId(battalionCommander.getBattalionGroup().getId())
                .build();
    }
}

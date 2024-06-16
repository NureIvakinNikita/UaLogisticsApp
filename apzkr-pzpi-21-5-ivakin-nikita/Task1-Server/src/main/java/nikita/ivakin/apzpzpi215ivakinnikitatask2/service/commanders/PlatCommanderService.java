package nikita.ivakin.apzpzpi215ivakinnikitatask2.service.commanders;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto.ResourcesRequestDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto.commanders.PlatCommanderDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto.groups.PlatGroupDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.resources.GivenResources;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.requests.ResourcesRequest;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.resources.ResourcesUpdateResponse;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.requests.SupplyRequest;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.commanders.CompanyCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.commanders.PlatCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.militaryGroups.PlatGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.enums.ResourcesType;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.enums.Role;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.enums.Status;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.exceptions.*;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.commanders.PlatCommanderRepository;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.GivenResourcesService;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.groups.PlatGroupService;
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
public class PlatCommanderService {


    private final PlatCommanderRepository platCommanderRepository;
    private final PlatGroupService platGroupService;
    private final ResourcesRequestService resourcesRequestService;
    private final SupplyRequestService supplyRequestService;
    private final GivenResourcesService givenResourcesService;

    public PlatCommander getAuthenticatedPlatCommander() {
        try {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String platCommanderEmail = authentication.getName();
        return findPlatCommanderByEmail(platCommanderEmail);
        } catch (Exception e) {
            throw new CommanderAuthenticationException("Error in getting authenticated brigade commander.");
        }
    }

    public PlatCommander findPlatCommanderById(Integer id){
        Optional<PlatCommander> tempPlatCom = platCommanderRepository.findPlatCommanderById(id);
        if (tempPlatCom.isPresent()) {
            return tempPlatCom.get();
        } else {
            throw new CommanderNotFoundException("Error plat commander with id" + id + " doesn't exist.");
        }
    }

    public PlatCommander findPlatCommanderByEmail(String email) {
        Optional<PlatCommander> tempPlatCom = platCommanderRepository.findPlatCommanderByEmail(email);
        if (tempPlatCom.isPresent()) {
            return tempPlatCom.get();
        } else {
            throw new CommanderNotFoundException("Error plat commander with email" + email  + " doesn't exist.");
        }
    }

    public PlatGroupDTO getPlatGroup(){
        PlatCommander platCommander = getAuthenticatedPlatCommander();
        PlatGroup platGroup = platGroupService.findPlatGroupByPlatCommander(platCommander);
        return platGroupService.mapPlatGroupToDTO(platGroup);
    }

    //Validation
    public boolean validateResources(PlatGroupDTO platGroupDTO) {
        PlatCommander platCommander = getAuthenticatedPlatCommander();
        GivenResources givenResources = givenResourcesService.getGivenResources(
                platCommander.getId(), platCommander.getPlatGroup().getId(), platCommander.getRole(), platCommander.getBrigadeCommanderId(), ResourcesType.FOR_PERFORMING_A_MISSION
        );
        return platGroupDTO.getAmmo762PktCount() >= givenResources.getAmmo762PktCount() / 5 && platGroupDTO.getAmmo556x45ArCount() >= givenResources.getAmmo556x45ArCount() / 5
                && platGroupDTO.getAmmo545x39AkRpkCount() >= givenResources.getAmmo545x39AkRpkCount() / 5 && platGroupDTO.getAmmo762x39AkCount() >= givenResources.getAmmo762x39AkCount() / 5
                && platGroupDTO.getAmmo145KpvtCount() >= givenResources.getAmmo145KpvtCount() / 5 && platGroupDTO.getAmmo40mmGpCount() >= givenResources.getAmmo40mmGpCount() / 5
                && platGroupDTO.getAmmo40mmRpgCount() >= givenResources.getAmmo40mmRpgCount() / 5 && platGroupDTO.getBodyArmorCount() >= givenResources.getBodyArmorCount()
                && platGroupDTO.getHelmetsCount() >= givenResources.getHelmetsCount() && platGroupDTO.getApcCount() >= givenResources.getApcCount();
    }

    //Add check of resources
    public ResourcesUpdateResponse updatePlatResources(PlatGroupDTO platGroupDTO) {
        try {
            boolean validationResult = !validateResources(platGroupDTO);
            boolean updateResult = platGroupService.updatePlatResources(platGroupDTO);
            return new ResourcesUpdateResponse(updateResult, validationResult);
        } catch (Exception e) {
            throw new MilitaryGroupUpdateException("Something went wrong in updating plat resources.");
        }
    }

    public boolean askForResources(ResourcesRequestDTO resourcesRequestDTO) {
        PlatCommander platCommander = getAuthenticatedPlatCommander();
        ResourcesRequest resourcesRequest = ResourcesRequest.builder()
                .commanderId(platCommander.getId())
                .militaryGroupId(platCommander.getPlatGroup().getId())
                .roleOfCommander(platCommander.getRole())
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
                .brigadeCommanderId(platCommander.getBrigadeCommanderId())
                .seniorMilitaryGroupId(platCommander.getCompanyGroup().getId())
                .commanderId(platCommander.getId())
                .militaryGroupId(platCommander.getPlatGroup().getId())
                .roleOfCommander(platCommander.getRole())
                .dateOfRequest(LocalDate.now())
                .status(Status.NOT_PROCESSED)
                .build();
        try {
            resourcesRequestService.save(resourcesRequest);
        } catch (Exception e) {
            throw new ResourcesRequestCreationException("Something went wrong in creation resources request in plat commander method ask for resources.");
        }
        resourcesRequest = resourcesRequestService.findResourcesRequestByCommanderIdAndMilitaryGroupIdAndExactTime(platCommander.getId(), platCommander.getPlatGroup().getId(), resourcesRequest.getExactTime(), Role.PLAT_COMMANDER);
        supplyRequest.setResourcesRequestId(resourcesRequest);
        //try {
            supplyRequestService.save(supplyRequest);
        /*} catch (Exception e) {
            throw new SupplyRequestCreationException("Something went wrong in creation supply request in plat commander method ask for resources.");
        }*/
        return true;
    }

    public List<PlatCommanderDTO> findPlatCommanderByCompanyCommander(CompanyCommander companyCommander) {
        List<PlatCommander> platCommanders = platCommanderRepository.findPlatCommanderByCompanyCommander(companyCommander);
        List<PlatCommanderDTO> platCommanderDTOS = new ArrayList<>();
        for (PlatCommander platCommander : platCommanders){
            platCommanderDTOS.add(mapPlatCommanderToDTO(platCommander));
        }
        return platCommanderDTOS;
    }

    private PlatCommanderDTO mapPlatCommanderToDTO(PlatCommander platCommander) {
        return PlatCommanderDTO.builder()
                .id(platCommander.getId())
                .firstName(platCommander.getFirstName())
                .lastName(platCommander.getLastName())
                .secondName(platCommander.getSecondName())
                .passportNumber(platCommander.getPassportNumber())
                .email(platCommander.getEmail())
                .rank(platCommander.getRank())
                .role(platCommander.getRole())
                .platGroupId(platCommander.getPlatGroup().getId())
                .build();
    }

    public List<SupplyRequest> getPlatRequests() {
        PlatCommander platCommander = getAuthenticatedPlatCommander();
        return supplyRequestService.getSupplyRequestsForPlatByPlatId(platCommander.getPlatGroup().getId(), platCommander.getRole());
    }

    @Transactional
    public void save(PlatCommander platCommander) {
        platCommanderRepository.save(platCommander);
    }


    public boolean confirmGettingOfResources(Integer supplyRequestId) {
        try {
            SupplyRequest supplyRequest = supplyRequestService.getSupplyRequestById(supplyRequestId);
            supplyRequest.setStatus(Status.FINISHED);
            supplyRequest.setExecutionСomplitionDate(LocalDate.now());
            supplyRequestService.save(supplyRequest);
            return true;
        } catch (Exception e) {
            throw new SupplyRequestUpdateException("Error in updating supply request in plat commander service.");
        }
    }
}

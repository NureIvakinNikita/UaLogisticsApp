package nikita.ivakin.apzpzpi215ivakinnikitatask2.service.groups;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto.groups.CompanyGroupDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.resources.GivenResources;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.commanders.BattalionCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.commanders.CompanyCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.militaryGroups.BattalionGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.militaryGroups.CompanyGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.enums.ResourcesType;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.enums.Role;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.exceptions.*;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.groups.CompanyGroupRepository;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.GivenResourcesService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class CompanyGroupService {

    private final CompanyGroupRepository companyGroupRepository;
    private final GivenResourcesService givenResourcesService;

    private void fillGivenResources(CompanyGroup companyGroup, BattalionCommander battalionCommander){
        GivenResources givenResources = GivenResources.builder()
                .militaryGroupId(companyGroup.getId())
                .brigadeCommanderId(battalionCommander.getBrigadeCommander().getId())
                .roleOfCommander(Role.COMPANY_COMMANDER)
                .issueDate(LocalDate.now())
                .allocationOfResources(ResourcesType.FOR_PERFORMING_A_MISSION)
                .ammo40mmGpCount(companyGroup.getAmmo40mmGpCount())
                .ammo40mmRpgCount(companyGroup.getAmmo40mmRpgCount())
                .ammo145KpvtCount(companyGroup.getAmmo145KpvtCount())
                .ammo545x39AkRpkCount(companyGroup.getAmmo545x39AkRpkCount())
                .ammo556x45ArCount(companyGroup.getAmmo556x45ArCount())
                .ammo762PktCount(companyGroup.getAmmo762PktCount())
                .ammo762x39AkCount(companyGroup.getAmmo762x39AkCount())
                .offensiveGrenadesCount(companyGroup.getOffensiveGrenadesCount())
                .defensiveGrenadesCount(companyGroup.getDefensiveGrenadesCount())
                .riflesCount(companyGroup.getRiflesCount())
                .machineGunsCount(companyGroup.getMachineGunsCount())
                .bodyArmorCount(companyGroup.getBodyArmorCount())
                .helmetsCount(companyGroup.getHelmetsCount())
                .apcCount(companyGroup.getApcCount())
                .tankCount(companyGroup.getTankCount())
                .foodCount(companyGroup.getFoodCount())
                .dryRationsCount(companyGroup.getDryRationsCount())
                .build();
        try {
            givenResourcesService.save(givenResources);
        } catch (Exception e) {
            throw new GivenResourcesCreationException("Error creating given resources entity.");
        }
    }

    public boolean addGivenResourcesForCompany(BattalionCommander battalionCommander){
        ArrayList<CompanyGroup> companyGroups = companyGroupRepository.findAll();
        if (companyGroups.size() > 0) {
            CompanyGroup companyGroup = companyGroups.get(companyGroups.size()-1);
            fillGivenResources(companyGroup, battalionCommander);
        } else {
            log.info("Error: there aren't any companies for giving resources.");
            throw new MilitaryGroupNotFoundException("There aren't any companies for giving resources.");
        }
        return true;
    }

    public boolean createCompanyGroup(CompanyGroupDTO companyGroupDTO, BattalionCommander battalionCommander) {
        CompanyGroup companyGroup = CompanyGroup.builder()
                .battalionGroup(battalionCommander.getBattalionGroup())
                .personnelCount(companyGroupDTO.getPersonnelCount())
                .ammo40mmGpCount(companyGroupDTO.getAmmo40mmGpCount())
                .ammo40mmRpgCount(companyGroupDTO.getAmmo40mmRpgCount())
                .ammo145KpvtCount(companyGroupDTO.getAmmo145KpvtCount())
                .ammo545x39AkRpkCount(companyGroupDTO.getAmmo545x39AkRpkCount())
                .ammo556x45ArCount(companyGroupDTO.getAmmo556x45ArCount())
                .ammo762PktCount(companyGroupDTO.getAmmo762PktCount())
                .ammo762x39AkCount(companyGroupDTO.getAmmo762x39AkCount())
                .offensiveGrenadesCount(companyGroupDTO.getOffensiveGrenadesCount())
                .defensiveGrenadesCount(companyGroupDTO.getDefensiveGrenadesCount())
                .riflesCount(companyGroupDTO.getRiflesCount())
                .machineGunsCount(companyGroupDTO.getMachineGunsCount())
                .bodyArmorCount(companyGroupDTO.getBodyArmorCount())
                .dryRationsCount(companyGroupDTO.getDryRationsCount())
                .foodCount(companyGroupDTO.getFoodCount())
                .helmetsCount(companyGroupDTO.getHelmetsCount())
                .apcCount(companyGroupDTO.getApcCount())
                .tankCount(companyGroupDTO.getTankCount())
                .build();
        try {
            save(companyGroup);
        } catch (Exception e) {
            throw new MilitaryGroupCreationException("Something went wrong while creating company group.");
        }

        return addGivenResourcesForCompany(battalionCommander);
    }

    public CompanyGroup findCompanyGroupById(Integer id) {
        Optional<CompanyGroup> tempCompGroup = companyGroupRepository.findCompanyGroupById(id);
        if (tempCompGroup.isPresent()) {
            return tempCompGroup.get();
        } else {
            throw new MilitaryGroupNotFoundException("Error company group with id" + id + " doesn't exist.");
        }
    }

    public CompanyGroup updateCompanyResources(CompanyGroupDTO companyGroupDTO) {
        CompanyGroup companyGroup = findCompanyGroupById(companyGroupDTO.getId());
        companyGroup.setAmmo40mmGpCount(companyGroupDTO.getAmmo40mmGpCount());
        companyGroup.setAmmo40mmRpgCount(companyGroupDTO.getAmmo40mmRpgCount());
        companyGroup.setAmmo145KpvtCount(companyGroupDTO.getAmmo145KpvtCount());
        companyGroup.setAmmo545x39AkRpkCount(companyGroupDTO.getAmmo545x39AkRpkCount());
        companyGroup.setAmmo762PktCount(companyGroupDTO.getAmmo762PktCount());
        companyGroup.setAmmo556x45ArCount(companyGroupDTO.getAmmo556x45ArCount());
        companyGroup.setAmmo762x39AkCount(companyGroupDTO.getAmmo762x39AkCount());
        companyGroup.setOffensiveGrenadesCount(companyGroupDTO.getOffensiveGrenadesCount());
        companyGroup.setDefensiveGrenadesCount(companyGroupDTO.getDefensiveGrenadesCount());
        companyGroup.setRiflesCount(companyGroupDTO.getRiflesCount());
        companyGroup.setMachineGunsCount(companyGroup.getMachineGunsCount());
        companyGroup.setBodyArmorCount(companyGroupDTO.getBodyArmorCount());
        companyGroup.setHelmetsCount(companyGroupDTO.getHelmetsCount());
        companyGroup.setApcCount(companyGroupDTO.getApcCount());
        companyGroup.setTankCount(companyGroupDTO.getTankCount());
        try {
            companyGroupRepository.save(companyGroup);
        } catch (Exception e) {
            throw new MilitaryGroupUpdateException("Something went wrong while updating company group with id "+ companyGroup.getId(), e);
        }
        return companyGroup;
    }

    public List<CompanyGroupDTO> findCompanyGroupsByBattalionGroupId(BattalionGroup battalionGroup) {
        try {
            List<CompanyGroup> companyGroups = companyGroupRepository.findAllByBattalionGroup(battalionGroup);
            List<CompanyGroupDTO> companyGroupDTOS = new ArrayList<>();
            for (CompanyGroup companyGroup : companyGroups) {
                companyGroupDTOS.add(mapCompanyGroupToDTO(companyGroup));
            }

            return companyGroupDTOS;
        } catch (Exception e) {
            throw new MilitaryGroupNotFoundException("Error in finding company groups with battalion group id " + battalionGroup.getId());
        }
    }

    public CompanyGroup findCompanyGroupByCompanyCommander(CompanyCommander companyCommander) {
        Optional<CompanyGroup> tempCompanyGroup = companyGroupRepository.findCompanyGroupByCompanyCommanderId(companyCommander);
        if (tempCompanyGroup.isPresent()) {
            return tempCompanyGroup.get();
        } else {
            throw new MilitaryGroupNotFoundException("Error company group with company commander id" + companyCommander.getId() + " doesn't exist.");
        }
    }

    public CompanyGroupDTO mapCompanyGroupToDTO(CompanyGroup companyGroup) {
        try {
            return CompanyGroupDTO.builder()
                    .id(companyGroup.getId())
                    .personnelCount(companyGroup.getPersonnelCount())
                    .ammo40mmGpCount(companyGroup.getAmmo40mmGpCount())
                    .ammo40mmRpgCount(companyGroup.getAmmo40mmRpgCount())
                    .ammo145KpvtCount(companyGroup.getAmmo145KpvtCount())
                    .ammo545x39AkRpkCount(companyGroup.getAmmo545x39AkRpkCount())
                    .ammo556x45ArCount(companyGroup.getAmmo556x45ArCount())
                    .ammo762PktCount(companyGroup.getAmmo762PktCount())
                    .ammo762x39AkCount(companyGroup.getAmmo762x39AkCount())
                    .offensiveGrenadesCount(companyGroup.getOffensiveGrenadesCount())
                    .defensiveGrenadesCount(companyGroup.getDefensiveGrenadesCount())
                    .riflesCount(companyGroup.getRiflesCount())
                    .machineGunsCount(companyGroup.getMachineGunsCount())
                    .dryRationsCount(companyGroup.getDryRationsCount())
                    .foodCount(companyGroup.getFoodCount())
                    .bodyArmorCount(companyGroup.getBodyArmorCount())
                    .helmetsCount(companyGroup.getHelmetsCount())
                    .apcCount(companyGroup.getApcCount())
                    .tankCount(companyGroup.getTankCount())
                    .build();
        } catch (Exception e) {
            throw new MilitaryGroupMappingToDtoException("Something went wrong in mapping company group to company group dto.");
        }
    }


    @Transactional
    public void save(CompanyGroup companyGroup) {
        companyGroupRepository.save(companyGroup);
    }



}

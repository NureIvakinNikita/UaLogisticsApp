package nikita.ivakin.apzpzpi215ivakinnikitatask2.service.groups;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto.groups.BattalionGroupDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.resources.GivenResources;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.commanders.BattalionCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.commanders.BrigadeCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.militaryGroups.BattalionGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.militaryGroups.BrigadeGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.enums.ResourcesType;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.enums.Role;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.exceptions.*;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.groups.BattalionGroupRepository;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.GivenResourcesService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class BattalionGroupService {

    private final BattalionGroupRepository battalionGroupRepository;
    private final GivenResourcesService givenResourcesService;

    private void fillGivenResources(BattalionGroup battalionGroup, BrigadeCommander brigadeCommander){
        GivenResources givenResources = GivenResources.builder()
                .militaryGroupId(battalionGroup.getId())
                .brigadeCommanderId(brigadeCommander.getId())
                .roleOfCommander(Role.BATTALION_COMMANDER)
                .issueDate(LocalDate.now())
                .allocationOfResources(ResourcesType.FOR_PERFORMING_A_MISSION)
                .ammo40mmGpCount(battalionGroup.getAmmo40mmGpCount())
                .ammo40mmRpgCount(battalionGroup.getAmmo40mmRpgCount())
                .ammo145KpvtCount(battalionGroup.getAmmo145KpvtCount())
                .ammo545x39AkRpkCount(battalionGroup.getAmmo545x39AkRpkCount())
                .ammo556x45ArCount(battalionGroup.getAmmo556x45ArCount())
                .ammo762PktCount(battalionGroup.getAmmo762PktCount())
                .ammo762x39AkCount(battalionGroup.getAmmo762x39AkCount())
                .offensiveGrenadesCount(battalionGroup.getOffensiveGrenadesCount())
                .defensiveGrenadesCount(battalionGroup.getDefensiveGrenadesCount())
                .riflesCount(battalionGroup.getRiflesCount())
                .machineGunsCount(battalionGroup.getMachineGunsCount())
                .bodyArmorCount(battalionGroup.getBodyArmorCount())
                .helmetsCount(battalionGroup.getHelmetsCount())
                .apcCount(battalionGroup.getApcCount())
                .tankCount(battalionGroup.getTankCount())
                .foodCount(battalionGroup.getFoodCount())
                .dryRationsCount(battalionGroup.getDryRationsCount())
                .build();
        try {
            givenResourcesService.save(givenResources);
        } catch (Exception e) {
            throw new GivenResourcesCreationException("Error creating given resources entity.");
        }
    }

    public boolean addGivenResourcesForBattalion(BrigadeCommander brigadeCommander){
        ArrayList<BattalionGroup> battalionGroups = battalionGroupRepository.findAll();
        if (battalionGroups.size() > 0) {
            BattalionGroup battalionGroup = battalionGroups.get(battalionGroups.size()-1);
            fillGivenResources(battalionGroup, brigadeCommander);
        } else {
            throw new MilitaryGroupNotFoundException("There aren't any battalions for giving resources.");
        }
        return true;
    }

    public boolean createBattalionGroup(BattalionGroupDTO battalionGroupDTO, BrigadeCommander brigadeCommander) {
        BattalionGroup battalionGroup = BattalionGroup.builder()
                .personnelCount(battalionGroupDTO.getPersonnelCount())
                .brigadeGroup(brigadeCommander.getBrigadeGroupId())
                .ammo40mmGpCount(battalionGroupDTO.getAmmo40mmGpCount())
                .ammo40mmRpgCount(battalionGroupDTO.getAmmo40mmRpgCount())
                .ammo145KpvtCount(battalionGroupDTO.getAmmo145KpvtCount())
                .ammo545x39AkRpkCount(battalionGroupDTO.getAmmo545x39AkRpkCount())
                .ammo556x45ArCount(battalionGroupDTO.getAmmo556x45ArCount())
                .ammo762PktCount(battalionGroupDTO.getAmmo762PktCount())
                .ammo762x39AkCount(battalionGroupDTO.getAmmo762x39AkCount())
                .offensiveGrenadesCount(battalionGroupDTO.getOffensiveGrenadesCount())
                .defensiveGrenadesCount(battalionGroupDTO.getDefensiveGrenadesCount())
                .riflesCount(battalionGroupDTO.getRiflesCount())
                .machineGunsCount(battalionGroupDTO.getMachineGunsCount())
                .bodyArmorCount(battalionGroupDTO.getBodyArmorCount())
                .helmetsCount(battalionGroupDTO.getHelmetsCount())
                .dryRationsCount(battalionGroupDTO.getDryRationsCount())
                .foodCount(battalionGroupDTO.getFoodCount())
                .apcCount(battalionGroupDTO.getApcCount())
                .tankCount(battalionGroupDTO.getTankCount())
                .build();

        try {
            save(battalionGroup);
        } catch (Exception e) {
            throw new MilitaryGroupCreationException("Something went wrong while creating battalion group.");
        }

        return addGivenResourcesForBattalion(brigadeCommander);

    }


    public BattalionGroup findBattalionGroupById(Integer id) {
        Optional<BattalionGroup> tempBatGroup = battalionGroupRepository.findBattalionGroupById(id);
        if (tempBatGroup.isPresent()) {
            return tempBatGroup.get();
        } else {
            throw new MilitaryGroupNotFoundException("Error battalion group with id" + id + " doesn't exist.");
        }
    }

    public boolean updateBattalionResources(BattalionGroupDTO battalionGroupDTO) {
        BattalionGroup battalionGroup = findBattalionGroupById(battalionGroupDTO.getId());
        battalionGroup.setAmmo40mmGpCount(battalionGroupDTO.getAmmo40mmGpCount());
        battalionGroup.setAmmo40mmRpgCount(battalionGroupDTO.getAmmo40mmRpgCount());
        battalionGroup.setAmmo145KpvtCount(battalionGroupDTO.getAmmo145KpvtCount());
        battalionGroup.setAmmo545x39AkRpkCount(battalionGroupDTO.getAmmo545x39AkRpkCount());
        battalionGroup.setAmmo762PktCount(battalionGroupDTO.getAmmo762PktCount());
        battalionGroup.setAmmo556x45ArCount(battalionGroupDTO.getAmmo556x45ArCount());
        battalionGroup.setAmmo762x39AkCount(battalionGroupDTO.getAmmo762x39AkCount());
        battalionGroup.setOffensiveGrenadesCount(battalionGroupDTO.getOffensiveGrenadesCount());
        battalionGroup.setDefensiveGrenadesCount(battalionGroupDTO.getDefensiveGrenadesCount());
        battalionGroup.setRiflesCount(battalionGroupDTO.getRiflesCount());
        battalionGroup.setMachineGunsCount(battalionGroup.getMachineGunsCount());
        battalionGroup.setBodyArmorCount(battalionGroupDTO.getBodyArmorCount());
        battalionGroup.setHelmetsCount(battalionGroupDTO.getHelmetsCount());
        battalionGroup.setApcCount(battalionGroupDTO.getApcCount());
        battalionGroup.setTankCount(battalionGroupDTO.getTankCount());
        try {
            battalionGroupRepository.save(battalionGroup);
        } catch (Exception e) {
            throw new MilitaryGroupUpdateException("Something went wrong while updating battalion group with id "+ battalionGroup.getId(), e);
        }
        return true;
    }

    public List<BattalionGroup> findBattalionsByBrigadeGroupId(BrigadeGroup brigadeGroup) {
        try {
            return battalionGroupRepository.findAllByBrigadeGroup(brigadeGroup);
        } catch (Exception e) {
            throw new MilitaryGroupNotFoundException("Error in finding battalion groups with brigade group id " + brigadeGroup.getId());
        }
    }

    public BattalionGroup findBattalionGroupByBattalionCommander(BattalionCommander battalionCommander) {
        Optional<BattalionGroup> tempBattalionGroup = battalionGroupRepository.findBattalionGroupByBattalionCommanderId(battalionCommander);
        if (tempBattalionGroup.isPresent()) {
            return tempBattalionGroup.get();
        } else {
            throw new MilitaryGroupNotFoundException("Error battalion group with battalion commander id" + battalionCommander.getId() + " doesn't exist.");
        }
    }

    public BattalionGroupDTO mapBattalionGroupToDTO(BattalionGroup battalionGroup) {
        try {
            return BattalionGroupDTO.builder()
                    .id(battalionGroup.getId())
                    .personnelCount(battalionGroup.getPersonnelCount())
                    .ammo40mmGpCount(battalionGroup.getAmmo40mmGpCount())
                    .ammo40mmRpgCount(battalionGroup.getAmmo40mmRpgCount())
                    .ammo145KpvtCount(battalionGroup.getAmmo145KpvtCount())
                    .ammo545x39AkRpkCount(battalionGroup.getAmmo545x39AkRpkCount())
                    .ammo556x45ArCount(battalionGroup.getAmmo556x45ArCount())
                    .ammo762PktCount(battalionGroup.getAmmo762PktCount())
                    .ammo762x39AkCount(battalionGroup.getAmmo762x39AkCount())
                    .offensiveGrenadesCount(battalionGroup.getOffensiveGrenadesCount())
                    .defensiveGrenadesCount(battalionGroup.getDefensiveGrenadesCount())
                    .riflesCount(battalionGroup.getRiflesCount())
                    .machineGunsCount(battalionGroup.getMachineGunsCount())
                    .dryRationsCount(battalionGroup.getDryRationsCount())
                    .foodCount(battalionGroup.getFoodCount())
                    .bodyArmorCount(battalionGroup.getBodyArmorCount())
                    .helmetsCount(battalionGroup.getHelmetsCount())
                    .apcCount(battalionGroup.getApcCount())
                    .tankCount(battalionGroup.getTankCount())
                    .build();
        } catch (Exception e) {
            throw new MilitaryGroupMappingToDtoException("Something went wrong in mapping battalion group to battalion group dto.");
        }


    }

    public List<BattalionGroupDTO> findBattalionGroupsByBrigadeGroupId(BrigadeGroup brigadeGroupId) {
        try {
            List<BattalionGroup> battalionGroups = battalionGroupRepository.findAllByBrigadeGroup(brigadeGroupId);
            List<BattalionGroupDTO> battalionGroupDTOS = new ArrayList<>();
            for (BattalionGroup battalionGroup : battalionGroups) {
                battalionGroupDTOS.add(mapBattalionGroupToDTO(battalionGroup));
            }

            return battalionGroupDTOS;
        } catch (Exception e) {
            throw new MilitaryGroupNotFoundException("Error in finding battalion groups with brigade group id " + brigadeGroupId.getId());
        }
    }


    @Transactional
    public void save(BattalionGroup battalionGroup) {
        battalionGroupRepository.save(battalionGroup);
    }



}


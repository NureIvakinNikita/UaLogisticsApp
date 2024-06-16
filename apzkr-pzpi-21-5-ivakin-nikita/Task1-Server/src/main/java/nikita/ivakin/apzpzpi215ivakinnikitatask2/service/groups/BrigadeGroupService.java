package nikita.ivakin.apzpzpi215ivakinnikitatask2.service.groups;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto.groups.BrigadeGroupDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.commanders.BrigadeCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.militaryGroups.BrigadeGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.exceptions.MilitaryGroupCreationException;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.exceptions.MilitaryGroupMappingToDtoException;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.exceptions.MilitaryGroupNotFoundException;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.exceptions.MilitaryGroupUpdateException;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.groups.BrigadeGroupRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class BrigadeGroupService {


    private final BrigadeGroupRepository brigadeGroupRepository;


    public boolean createBrigadeGroup(BrigadeGroupDTO brigadeGroupDTO, BrigadeCommander brigadeCommander) {
        BrigadeGroup brigadeGroup = BrigadeGroup.builder()
                .personnelCount(brigadeGroupDTO.getPersonnelCount())
                .brigadeCommanderId(brigadeCommander)
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
            save(brigadeGroup);
        } catch (Exception e) {
            throw new MilitaryGroupCreationException("Something went wrong while creating brigade group.");
        }

        return true;
    }

    public BrigadeGroup findBrigadeGroupByBrigadeCommander(BrigadeCommander brigadeCommanderId) {

        Optional<BrigadeGroup> tempBrigGroup = brigadeGroupRepository.findBrigadeGroupByBrigadeCommanderId(brigadeCommanderId);
        if (tempBrigGroup.isPresent()) {
            return tempBrigGroup.get();
        } else {
            throw new MilitaryGroupNotFoundException("Error brigade group with brigade commander id " + brigadeCommanderId.getId() + " doesn't exist.");
        }
    }

    @Transactional
    public void save(BrigadeGroup brigadeGroup) {
            brigadeGroupRepository.save(brigadeGroup);
    }

    public BrigadeGroup findBrigadeGroupById(Integer id) {
        Optional<BrigadeGroup> tempBrigGroup = brigadeGroupRepository.findBrigadeGroupById(id);
        if (tempBrigGroup.isPresent()) {
            return tempBrigGroup.get();
        } else {
            throw new MilitaryGroupNotFoundException("Error brigade group with id" + id + " doesn't exist.");
        }
    }

    public boolean updateBrigadeResources(BrigadeGroupDTO brigadeGroupDTO) {
        BrigadeGroup brigadeGroup = findBrigadeGroupById(brigadeGroupDTO.getId());
        brigadeGroup.setAmmo40mmGpCount(brigadeGroupDTO.getAmmo40mmGpCount());
        brigadeGroup.setAmmo40mmRpgCount(brigadeGroupDTO.getAmmo40mmRpgCount());
        brigadeGroup.setAmmo145KpvtCount(brigadeGroupDTO.getAmmo145KpvtCount());
        brigadeGroup.setAmmo545x39AkRpkCount(brigadeGroupDTO.getAmmo545x39AkRpkCount());
        brigadeGroup.setAmmo762PktCount(brigadeGroupDTO.getAmmo762PktCount());
        brigadeGroup.setAmmo556x45ArCount(brigadeGroupDTO.getAmmo556x45ArCount());
        brigadeGroup.setAmmo762x39AkCount(brigadeGroupDTO.getAmmo762x39AkCount());
        brigadeGroup.setOffensiveGrenadesCount(brigadeGroupDTO.getOffensiveGrenadesCount());
        brigadeGroup.setDefensiveGrenadesCount(brigadeGroupDTO.getDefensiveGrenadesCount());
        brigadeGroup.setRiflesCount(brigadeGroupDTO.getRiflesCount());
        brigadeGroup.setMachineGunsCount(brigadeGroup.getMachineGunsCount());
        brigadeGroup.setBodyArmorCount(brigadeGroupDTO.getBodyArmorCount());
        brigadeGroup.setHelmetsCount(brigadeGroupDTO.getHelmetsCount());
        brigadeGroup.setApcCount(brigadeGroupDTO.getApcCount());
        brigadeGroup.setTankCount(brigadeGroupDTO.getTankCount());
        try {
            brigadeGroupRepository.save(brigadeGroup);
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new MilitaryGroupUpdateException("Something went wrong while updating brigade group with id "+ brigadeGroup.getId(), e);
        }
        return true;
    }

    public BrigadeGroupDTO mapBrigadeGroupToDTO(BrigadeGroup brigadeGroup) {
        try {
            return BrigadeGroupDTO.builder()
                    .id(brigadeGroup.getId())
                    .personnelCount(brigadeGroup.getPersonnelCount())
                    .ammo40mmGpCount(brigadeGroup.getAmmo40mmGpCount())
                    .ammo40mmRpgCount(brigadeGroup.getAmmo40mmRpgCount())
                    .ammo145KpvtCount(brigadeGroup.getAmmo145KpvtCount())
                    .ammo545x39AkRpkCount(brigadeGroup.getAmmo545x39AkRpkCount())
                    .ammo556x45ArCount(brigadeGroup.getAmmo556x45ArCount())
                    .ammo762PktCount(brigadeGroup.getAmmo762PktCount())
                    .ammo762x39AkCount(brigadeGroup.getAmmo762x39AkCount())
                    .offensiveGrenadesCount(brigadeGroup.getOffensiveGrenadesCount())
                    .defensiveGrenadesCount(brigadeGroup.getDefensiveGrenadesCount())
                    .riflesCount(brigadeGroup.getRiflesCount())
                    .machineGunsCount(brigadeGroup.getMachineGunsCount())
                    .dryRationsCount(brigadeGroup.getDryRationsCount())
                    .foodCount(brigadeGroup.getFoodCount())
                    .bodyArmorCount(brigadeGroup.getBodyArmorCount())
                    .helmetsCount(brigadeGroup.getHelmetsCount())
                    .apcCount(brigadeGroup.getApcCount())
                    .tankCount(brigadeGroup.getTankCount())
                    .build();
        } catch (Exception e) {
            throw new MilitaryGroupMappingToDtoException("Something went wrong in mapping brigade group to brigade group dto.");
        }
    }
}

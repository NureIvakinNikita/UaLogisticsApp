package nikita.ivakin.apzpzpi215ivakinnikitatask2.service.groups;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto.groups.LogisticCompanyDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.commanders.LogisticCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.resources.GivenResources;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.commanders.BrigadeCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.militaryGroups.LogisticCompany;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.enums.ResourcesType;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.enums.Role;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.exceptions.GivenResourcesCreationException;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.exceptions.MilitaryGroupCreationException;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.exceptions.MilitaryGroupNotFoundException;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.groups.LogisticCompanyRepository;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.GivenResourcesService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class LogisticCompanyService {


    private final LogisticCompanyRepository logisticCompanyRepository;
    private final GivenResourcesService givenResourcesService;

    public boolean createLogisticCompany(LogisticCompanyDTO logisticCompanyDTO, BrigadeCommander brigadeCommander) {
        LogisticCompany logisticCompany = LogisticCompany.builder()
                .personnelCount(logisticCompanyDTO.getPersonnelCount())
                .brigadeGroup(brigadeCommander.getBrigadeGroupId())
                .ammo40mmGpCount(logisticCompanyDTO.getAmmo40mmGpCount())
                .ammo40mmRpgCount(logisticCompanyDTO.getAmmo40mmRpgCount())
                .ammo145KpvtCount(logisticCompanyDTO.getAmmo145KpvtCount())
                .ammo545x39AkRpkCount(logisticCompanyDTO.getAmmo545x39AkRpkCount())
                .ammo556x45ArCount(logisticCompanyDTO.getAmmo556x45ArCount())
                .ammo762PktCount(logisticCompanyDTO.getAmmo762PktCount())
                .ammo762x39AkCount(logisticCompanyDTO.getAmmo762x39AkCount())
                .offensiveGrenadesCount(logisticCompanyDTO.getOffensiveGrenadesCount())
                .defensiveGrenadesCount(logisticCompanyDTO.getDefensiveGrenadesCount())
                .riflesCount(logisticCompanyDTO.getRiflesCount())
                .machineGunsCount(logisticCompanyDTO.getMachineGunsCount())
                .bodyArmorCount(logisticCompanyDTO.getBodyArmorCount())
                .helmetsCount(logisticCompanyDTO.getHelmetsCount())
                .apcCount(logisticCompanyDTO.getApcCount())
                .tankCount(logisticCompanyDTO.getTankCount())
                .foodCount(logisticCompanyDTO.getFoodCount())
                .dryRationsCount(logisticCompanyDTO.getDryRationsCount())
                .build();

        try {
            save(logisticCompany);
        } catch (Exception e) {
            throw new MilitaryGroupCreationException("Something went wrong while creating logistic company.");
        }

        return addGivenResourcesForLogisticCompany(brigadeCommander);
    }

    public boolean addGivenResourcesForLogisticCompany(BrigadeCommander brigadeCommander){
        List<LogisticCompany> logisticCompanies = logisticCompanyRepository.findAll();
        if (logisticCompanies.size() > 0) {
            LogisticCompany logisticCompany = logisticCompanies.get(logisticCompanies.size()-1);
            fillGivenResources(logisticCompany, brigadeCommander);
        } else {
            throw new MilitaryGroupNotFoundException("There aren't any battalions for giving resources.");
        }
        return true;
    }

    private void fillGivenResources(LogisticCompany logisticCompany, BrigadeCommander brigadeCommander){
        GivenResources givenResources = GivenResources.builder()
                .militaryGroupId(logisticCompany.getId())
                .brigadeCommanderId(brigadeCommander.getId())
                .roleOfCommander(Role.LOGISTIC_COMMANDER)
                .issueDate(LocalDate.now())
                .allocationOfResources(ResourcesType.FOR_PERFORMING_A_MISSION)
                .ammo40mmGpCount(logisticCompany.getAmmo40mmGpCount())
                .ammo40mmRpgCount(logisticCompany.getAmmo40mmRpgCount())
                .ammo145KpvtCount(logisticCompany.getAmmo145KpvtCount())
                .ammo545x39AkRpkCount(logisticCompany.getAmmo545x39AkRpkCount())
                .ammo556x45ArCount(logisticCompany.getAmmo556x45ArCount())
                .ammo762PktCount(logisticCompany.getAmmo762PktCount())
                .ammo762x39AkCount(logisticCompany.getAmmo762x39AkCount())
                .offensiveGrenadesCount(logisticCompany.getOffensiveGrenadesCount())
                .defensiveGrenadesCount(logisticCompany.getDefensiveGrenadesCount())
                .riflesCount(logisticCompany.getRiflesCount())
                .machineGunsCount(logisticCompany.getMachineGunsCount())
                .bodyArmorCount(logisticCompany.getBodyArmorCount())
                .helmetsCount(logisticCompany.getHelmetsCount())
                .apcCount(logisticCompany.getApcCount())
                .tankCount(logisticCompany.getTankCount())
                .foodCount(logisticCompany.getFoodCount())
                .dryRationsCount(logisticCompany.getDryRationsCount())
                .build();
        try {
            givenResourcesService.save(givenResources);
        } catch (Exception e) {
            throw new GivenResourcesCreationException("Error creating given resources entity.");
        }
    }


    public LogisticCompany findLogisticCompanyById(Integer id) {
        Optional<LogisticCompany> tempLogGCom = logisticCompanyRepository.findLogisticCompanyById(id);
        if (tempLogGCom.isPresent()) {
            return tempLogGCom.get();
        } else {
            throw new MilitaryGroupNotFoundException("Error logistic company with id" + id + " doesn't exist.");
        }
    }

    public LogisticCompany findLogisticCompanyByBrigadeCommander(BrigadeCommander brigadeCommander) {
        Optional<LogisticCompany> tempLogComp = logisticCompanyRepository.findLogisticCompanyByBrigadeGroup(brigadeCommander.getBrigadeGroupId());
        if (tempLogComp.isPresent()) {
            return tempLogComp.get();
        } else {
            throw new MilitaryGroupNotFoundException("Error logistic company with brigade commander id" + brigadeCommander.getId() + " doesn't exist.");
        }
    }

    public LogisticCompany findLogisticCompanyByLogisticCommander(LogisticCommander logisticCommander) {
        Optional<LogisticCompany> tempLogComp = logisticCompanyRepository.findLogisticCompanyByLogisticCommanderId(logisticCommander);
        if (tempLogComp.isPresent()) {
            return tempLogComp.get();
        } else {
            throw new MilitaryGroupNotFoundException("Error logistic company with logistic commander id" + logisticCommander.getId() + " doesn't exist.");
        }
    }


    @Transactional
    public void save(LogisticCompany logisticCompany) {
        logisticCompanyRepository.save(logisticCompany);
    }


}

package nikita.ivakin.apzpzpi215ivakinnikitatask2.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.resources.GivenResources;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.requests.ResourcesRequest;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.militaryGroups.CompanyGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.militaryGroups.MilitaryGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.militaryGroups.PlatGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.enums.ResourcesType;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.enums.Role;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.exceptions.GivenResourcesCreationException;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.exceptions.GivenResourcesNotFoundException;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.GivenResourcesRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Service
@Slf4j
@AllArgsConstructor
public class GivenResourcesService {

    private final GivenResourcesRepository givenResourcesRepository;

    public void assignCommander(Integer commanderId, Integer militaryGroupId, Integer brigadeCommanderId, Role roleOfCommander) {
        GivenResources givenResources = findGivenResources(militaryGroupId, brigadeCommanderId, roleOfCommander);
        givenResources.setCommanderId(commanderId);
        try {
            save(givenResources);
        } catch (Exception e) {
            throw new GivenResourcesCreationException("Something went wrong in saving given resources");
        }
    }

    private GivenResources findGivenResources(Integer militaryGroupId, Integer brigadeCommanderId, Role roleOfCommander) {
        Optional<GivenResources> tempGivenResources = givenResourcesRepository.findGivenResourcesByMilitaryGroupIdAndRoleOfCommanderAndBrigadeCommanderId(
                militaryGroupId, roleOfCommander, brigadeCommanderId
        );
        if (tempGivenResources.isPresent()){
            return tempGivenResources.get();
        } else {
            throw new GivenResourcesNotFoundException("There aren't any given resources for military group with id " +
                    militaryGroupId + ", and commander role " + roleOfCommander );
        }
    }

    public GivenResources getGivenResources(Integer commanderId, Integer militaryGroupId, Role roleOfCommander, Integer brigadeCommanderId, ResourcesType allocationOfResources) {
        Optional<GivenResources> tempGivenResources = givenResourcesRepository.findGivenResourcesByCommanderIdAndMilitaryGroupIdAndRoleOfCommanderAndBrigadeCommanderIdAndAllocationOfResources(
                commanderId, militaryGroupId, roleOfCommander, brigadeCommanderId, allocationOfResources
        );
        if (tempGivenResources.isPresent()) {
            return tempGivenResources.get();
        } else {
            throw new GivenResourcesNotFoundException("Error given resources entity for this battle group doesn't exist.");
        }
    }

    public boolean allocateResources(ResourcesRequest resourcesRequest, MilitaryGroup givingMilitaryGroup, MilitaryGroup gettingMilitaryGroup,
                                     Integer commanderId, Role roleOfCommander, Integer brigadeCommanderId, Integer part) {
        GivenResources givenResources = new GivenResources();
        countResources(givingMilitaryGroup, gettingMilitaryGroup, givenResources, resourcesRequest.getAmmo762PktCount(),
                givingMilitaryGroup::getAmmo762PktCount, givingMilitaryGroup::setAmmo762PktCount,
                gettingMilitaryGroup::getAmmo762PktCount, gettingMilitaryGroup::setAmmo762PktCount,
                givenResources::setAmmo762PktCount);


        countResources(givingMilitaryGroup, gettingMilitaryGroup, givenResources, resourcesRequest.getAmmo556x45ArCount(),
                givingMilitaryGroup::getAmmo556x45ArCount, givingMilitaryGroup::setAmmo556x45ArCount,
                gettingMilitaryGroup::getAmmo556x45ArCount, gettingMilitaryGroup::setAmmo556x45ArCount,
                givenResources::setAmmo556x45ArCount);

        countResources(givingMilitaryGroup, gettingMilitaryGroup, givenResources, resourcesRequest.getAmmo545x39AkRpkCount(),
                givingMilitaryGroup::getAmmo545x39AkRpkCount, givingMilitaryGroup::setAmmo545x39AkRpkCount,
                gettingMilitaryGroup::getAmmo545x39AkRpkCount, gettingMilitaryGroup::setAmmo545x39AkRpkCount,
                givenResources::setAmmo545x39AkRpkCount);
        countResources(givingMilitaryGroup, gettingMilitaryGroup, givenResources, resourcesRequest.getAmmo762x39AkCount(),
                givingMilitaryGroup::getAmmo762x39AkCount, givingMilitaryGroup::setAmmo762x39AkCount,
                gettingMilitaryGroup::getAmmo762x39AkCount, gettingMilitaryGroup::setAmmo762x39AkCount,
                givenResources::setAmmo762x39AkCount);

        countResources(givingMilitaryGroup, gettingMilitaryGroup, givenResources, resourcesRequest.getAmmo145KpvtCount(),
                givingMilitaryGroup::getAmmo145KpvtCount, givingMilitaryGroup::setAmmo145KpvtCount,
                gettingMilitaryGroup::getAmmo145KpvtCount, gettingMilitaryGroup::setAmmo145KpvtCount,
                givenResources::setAmmo145KpvtCount);

        countResources(givingMilitaryGroup, gettingMilitaryGroup, givenResources, resourcesRequest.getAmmo40mmGpCount(),
                givingMilitaryGroup::getAmmo40mmGpCount, givingMilitaryGroup::setAmmo40mmGpCount,
                gettingMilitaryGroup::getAmmo40mmGpCount, gettingMilitaryGroup::setAmmo40mmGpCount,
                givenResources::setAmmo40mmGpCount);

        countResources(givingMilitaryGroup, gettingMilitaryGroup, givenResources, resourcesRequest.getAmmo40mmRpgCount(),
                givingMilitaryGroup::getAmmo40mmRpgCount, givingMilitaryGroup::setAmmo40mmRpgCount,
                gettingMilitaryGroup::getAmmo40mmRpgCount, gettingMilitaryGroup::setAmmo40mmRpgCount,
                givenResources::setAmmo40mmRpgCount);

        countResources(givingMilitaryGroup, gettingMilitaryGroup, givenResources, resourcesRequest.getBodyArmorCount(),
                givingMilitaryGroup::getBodyArmorCount, givingMilitaryGroup::setBodyArmorCount,
                gettingMilitaryGroup::getBodyArmorCount, gettingMilitaryGroup::setBodyArmorCount,
                givenResources::setBodyArmorCount);

        countResources(givingMilitaryGroup, gettingMilitaryGroup, givenResources, resourcesRequest.getHelmetsCount(),
                givingMilitaryGroup::getHelmetsCount, givingMilitaryGroup::setHelmetsCount,
                gettingMilitaryGroup::getHelmetsCount, gettingMilitaryGroup::setHelmetsCount,
                givenResources::setHelmetsCount);

        countResources(givingMilitaryGroup, gettingMilitaryGroup, givenResources, resourcesRequest.getApcCount(),
                givingMilitaryGroup::getApcCount, givingMilitaryGroup::setApcCount,
                gettingMilitaryGroup::getApcCount, gettingMilitaryGroup::setApcCount,
                givenResources::setApcCount);

        givenResources.setCommanderId(commanderId);
        givenResources.setMilitaryGroupId(gettingMilitaryGroup.getId());
        givenResources.setRoleOfCommander(roleOfCommander);
        givenResources.setBrigadeCommanderId(brigadeCommanderId);
        givenResources.setIssueDate(LocalDate.now());
        givenResources.setAllocationOfResources(ResourcesType.FOR_CLOSING_DEFICIENCY);
        try {
            save(givenResources);
        } catch (Exception e) {
            throw new GivenResourcesCreationException("Something went wrong in creation given resources entity.", e);
        }

        return true;
    }

    public void allocateResourceForPlat(CompanyGroup companyGroup, PlatGroup platGroup, GivenResources givenResources, int requestedAmount,
                                        Supplier<Integer> getCompanyCount, Consumer<Integer> setCompanyCount,
                                        Supplier<Integer> getPlatCount, Consumer<Integer> setPlatCount,
                                        Consumer<Integer> setGivenResourcesCount) {
        int companyCount = getCompanyCount.get();
        int allocatedAmount = Math.min(companyCount, requestedAmount);
        setPlatCount.accept(getPlatCount.get() + allocatedAmount);
        setCompanyCount.accept(companyCount - allocatedAmount);
        setGivenResourcesCount.accept(allocatedAmount);
    }

    public void countResources(MilitaryGroup givingMilitaryGroup, MilitaryGroup gettingMilitaryGroup, GivenResources givenResources, int requestedAmount,
                               Supplier<Integer> getCompanyCount, Consumer<Integer> setCompanyCount,
                               Supplier<Integer> getPlatCount, Consumer<Integer> setPlatCount,
                               Consumer<Integer> setGivenResourcesCount) {
        int companyCount = getCompanyCount.get();
        int allocatedAmount = Math.min(companyCount, requestedAmount);
        setPlatCount.accept(getPlatCount.get() + allocatedAmount);
        setCompanyCount.accept(companyCount - allocatedAmount);
        setGivenResourcesCount.accept(allocatedAmount);
    }

    public void checkAmountOfResources(){

    }

    @Transactional
    public void save(GivenResources givenResources) {
        givenResourcesRepository.save(givenResources);
    }


}

package nikita.ivakin.apzpzpi215ivakinnikitatask2.service.requests;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.requests.ResourcesRequest;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.enums.Role;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.exceptions.ResourcesRequestNotFoundException;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.requests.ResourcesRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class ResourcesRequestService {

    @Autowired
    private final ResourcesRequestRepository resourcesRequestRepository;

    @Transactional
    public void save(ResourcesRequest resourcesRequest) {
        resourcesRequestRepository.save(resourcesRequest);
    }


    public ResourcesRequest findResourcesRequestByCommanderIdAndMilitaryGroupIdAndExactTime(Integer commanderId, Integer militaryGroupId, LocalDateTime exactTime, Role roleOfCommander) {
        Optional<ResourcesRequest> tempResReq = resourcesRequestRepository.findResourcesRequestByCommanderIdAndMilitaryGroupIdAndExactTimeAndRoleOfCommander(commanderId, militaryGroupId, exactTime, roleOfCommander);
        if (tempResReq.isPresent()) {
            return tempResReq.get();
        } else {
            throw new ResourcesRequestNotFoundException("Error resources request with commander id " +commanderId+ " and military group id " + militaryGroupId+ " doesn't exist.");
        }
    }
}

package nikita.ivakin.apzpzpi215ivakinnikitatask2.controller.commanders;

import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto.ResourcesRequestDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto.groups.PlatGroupDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto.vlidation.UpdateGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.resources.ResourcesUpdateResponse;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.requests.SupplyRequest;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.commanders.PlatCommanderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/plat-com")
@RequiredArgsConstructor
public class PlatCommanderController {

    private final PlatCommanderService platCommanderService;

    //Create Service for messages with ask for resources
    //checked
    @PreAuthorize("hasAnyRole('PLAT_COMMANDER', 'ADMIN')")
    @GetMapping("/get-plat-resources")
    public ResponseEntity<PlatGroupDTO> getPlatsResources(){
        PlatGroupDTO platGroupDTO = platCommanderService.getPlatGroup();
        return new ResponseEntity<>(platGroupDTO, HttpStatus.OK);
    }

    //checked
    //For one Plat to see if it was done
    @PreAuthorize("hasAnyRole('PLAT_COMMANDER', 'ADMIN')")
    @GetMapping("/get/plat-requests")
    public ResponseEntity<List<SupplyRequest>> getPlatRequests(){
        List<SupplyRequest> supplyRequests = platCommanderService.getPlatRequests();
        log.info("error");
        return new ResponseEntity<>(supplyRequests, HttpStatus.OK);
    }

    //checked
    @PreAuthorize("hasAnyRole('PLAT_COMMANDER', 'ADMIN')")
    @PutMapping("/update/plat-resources")
    public ResponseEntity<ResourcesUpdateResponse> updatePlatResources(@Validated({Default.class, UpdateGroup.class})@RequestBody PlatGroupDTO platGroupDTO) {
        ResourcesUpdateResponse resourcesUpdateResponse = platCommanderService.updatePlatResources(platGroupDTO);
        return new ResponseEntity<>(resourcesUpdateResponse, HttpStatus.OK);
    }

    //checked
    @PreAuthorize("hasAnyRole('PLAT_COMMANDER', 'ADMIN')")
    @PostMapping("/ask/for-resources")
    public ResponseEntity<Boolean> askForResources(@Valid @RequestBody ResourcesRequestDTO resourcesRequestDTO) {
        boolean result = platCommanderService.askForResources(resourcesRequestDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //checked
    @PreAuthorize("hasAnyRole('PLAT_COMMANDER', 'ADMIN')")
    @PutMapping("/confirm/getting-of-resources")
    public ResponseEntity<Boolean> confirmGettingOfResources(@RequestParam Integer supplyRequestId){
        boolean result = platCommanderService.confirmGettingOfResources(supplyRequestId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}

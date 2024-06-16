package nikita.ivakin.apzpzpi215ivakinnikitatask2.controller.commanders;

import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto.ResourcesRequestDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto.groups.BattalionGroupDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto.groups.BrigadeGroupDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto.groups.LogisticCompanyDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto.vlidation.CreateGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto.vlidation.UpdateGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.resources.ResourcesUpdateResponse;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.requests.SupplyRequest;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.commanders.BrigadeCommanderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/brig-com")
@RequiredArgsConstructor
public class BrigadeCommanderController {


    private final BrigadeCommanderService brigadeCommanderService;

    //checked
    @PreAuthorize("hasAnyRole('BRIGADE_COMMANDER', 'ADMIN')")
    @GetMapping("/get-brigade-resources")
    public ResponseEntity<BrigadeGroupDTO> getBrigadeResources() {
        BrigadeGroupDTO brigadeGroupDTO = brigadeCommanderService.getBrigadeGroup();
        return new ResponseEntity<>(brigadeGroupDTO, HttpStatus.OK);
    }

    //checked
    @PreAuthorize("hasAnyRole('BRIGADE_COMMANDER', 'ADMIN')")
    @GetMapping("/brigade-battalion-groups")
    public ResponseEntity<List<BattalionGroupDTO>> getBattalionGroups() {
        List<BattalionGroupDTO> battalionGroups = brigadeCommanderService.getBrigadeBattalionGroups();
        return new ResponseEntity<>(battalionGroups, HttpStatus.OK);
    }

    //checked
    @PreAuthorize("hasAnyRole('BRIGADE_COMMANDER', 'ADMIN')")
    @PostMapping("/create/brigade")
    public ResponseEntity<Boolean> createBrigade(@Validated({Default.class, CreateGroup.class}) @RequestBody BrigadeGroupDTO brigadeGroupDTO) {
        boolean result = brigadeCommanderService.createBrigade(brigadeGroupDTO);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    //checked
    @PreAuthorize("hasAnyRole('BRIGADE_COMMANDER', 'ADMIN')")
    @PutMapping("/update/brigade-resources")
    public ResponseEntity<ResourcesUpdateResponse> updateBrigadeResources(@Validated({Default.class, UpdateGroup.class})@RequestBody BrigadeGroupDTO brigadeGroupDTO) {
        ResourcesUpdateResponse resourcesUpdateResponse = brigadeCommanderService.updateBrigadeResources(brigadeGroupDTO);
        return new ResponseEntity<>(resourcesUpdateResponse, HttpStatus.OK);
    }

    //checked
    @PreAuthorize("hasAnyRole('BRIGADE_COMMANDER', 'ADMIN')")
    @PostMapping("/create/logistic-company")
    public ResponseEntity<Boolean> createLogisticCompany(@Validated({Default.class, CreateGroup.class}) @RequestBody LogisticCompanyDTO logisticCompanyDTO) {
        boolean result = brigadeCommanderService.createLogisticCompany(logisticCompanyDTO);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    //checked
    @PreAuthorize("hasAnyRole('BRIGADE_COMMANDER', 'ADMIN')")
    @PostMapping("/create/battalion")
    public ResponseEntity<Boolean> createBattalion(@Validated({Default.class, CreateGroup.class}) @RequestBody BattalionGroupDTO battalionGroupDTO) {
        boolean result = brigadeCommanderService.createBattalion(battalionGroupDTO);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    //checked
    @PreAuthorize("hasAnyRole('BRIGADE_COMMANDER', 'ADMIN')")
    @PutMapping("/assign/battalion-commander")
    public ResponseEntity<Boolean> assignBattalionCommander(@RequestParam Integer battalionCommanderId, @RequestParam Integer battalionGroupId) {
        boolean result = brigadeCommanderService.assignBattalionCommander(battalionCommanderId, battalionGroupId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('BRIGADE_COMMANDER', 'ADMIN')")
    @PutMapping("/assign/logistic-commander")
    public ResponseEntity<Boolean> assignLogisticCommander(@RequestParam Integer logisticCommanderId, @RequestParam Integer logisticCompanyId) {
        boolean result = brigadeCommanderService.assignLogisticCompanyCommander(logisticCommanderId, logisticCompanyId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //checked
    @PreAuthorize("hasAnyRole('BRIGADE_COMMANDER', 'ADMIN')")
    @GetMapping("/get/brigade-requests")
    public ResponseEntity<List<SupplyRequest>> getBrigadeRequests(){
        List<SupplyRequest> supplyRequests = brigadeCommanderService.getBrigadeRequests();
        return new ResponseEntity<>(supplyRequests, HttpStatus.OK);
    }

    //checked
    @PreAuthorize("hasAnyRole('BRIGADE_COMMANDER', 'ADMIN')")
    @GetMapping("/get/battalion-requests")
    public ResponseEntity<List<SupplyRequest>> getBattalionsRequests(){
        List<SupplyRequest> supplyRequests = brigadeCommanderService.getBattalionRequests();
        return new ResponseEntity<>(supplyRequests, HttpStatus.OK);
    }

    //checked
    @PreAuthorize("hasAnyRole('BRIGADE_COMMANDER', 'ADMIN')")
    @PostMapping("/send/resources-to-battalion")
    public ResponseEntity<ResourcesUpdateResponse> sendResources(@RequestBody SupplyRequest supplyRequest) {
        ResourcesUpdateResponse resourcesUpdateResponse = brigadeCommanderService.sendResourcesToBattalion(supplyRequest);
        return new ResponseEntity<>(resourcesUpdateResponse, HttpStatus.OK);
    }

    //checked
    @PreAuthorize("hasAnyRole('BRIGADE_COMMANDER', 'ADMIN')")
    @PostMapping("/ask/for-resources")
    public ResponseEntity<Boolean> askForResources(@Valid @RequestBody ResourcesRequestDTO resourcesRequestDTO){
        boolean result = brigadeCommanderService.askForResources(resourcesRequestDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //checked
    @PreAuthorize("hasAnyRole('BRIGADE_COMMANDER', 'ADMIN')")
    @PutMapping("/confirm/getting-of-resources")
    public ResponseEntity<Boolean> confirmGettingOfResources(@RequestParam Integer supplyRequestId){
        boolean result = brigadeCommanderService.confirmGettingOfResources(supplyRequestId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}


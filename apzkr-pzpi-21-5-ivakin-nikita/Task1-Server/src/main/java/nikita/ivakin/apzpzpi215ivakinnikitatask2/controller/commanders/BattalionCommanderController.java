package nikita.ivakin.apzpzpi215ivakinnikitatask2.controller.commanders;

import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto.ResourcesRequestDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto.groups.BattalionGroupDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto.groups.CompanyGroupDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto.vlidation.CreateGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto.vlidation.UpdateGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.resources.ResourcesUpdateResponse;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.requests.SupplyRequest;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.commanders.BattalionCommanderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/bat-com")
@RequiredArgsConstructor
public class BattalionCommanderController {

    private final BattalionCommanderService battalionCommanderService;

    //checked
    @PreAuthorize("hasAnyRole('BATTALION_COMMANDER', 'ADMIN')")
    @GetMapping("/get-battalion-resources")
    public ResponseEntity<BattalionGroupDTO> getBattalionResources() {
        BattalionGroupDTO battalionGroupDTO = battalionCommanderService.getBattalionGroup();
        return new ResponseEntity<>(battalionGroupDTO, HttpStatus.OK);
    }

    //checked
    @PreAuthorize("hasAnyRole('BATTALION_COMMANDER', 'ADMIN')")
    @GetMapping("/battalion-company-groups")
    public ResponseEntity<List<CompanyGroupDTO>> getBattalionCompanyGroups(){
        List<CompanyGroupDTO> battalionMilitaryGroups = battalionCommanderService.getBattalionCompanyGroups();
        return new ResponseEntity<>(battalionMilitaryGroups, HttpStatus.OK);
    }

    //checked
    @PreAuthorize("hasAnyRole('BATTALION_COMMANDER', 'ADMIN')")
    @PostMapping("/create/company")
    public ResponseEntity<Boolean> createCompany(@Validated({Default.class, CreateGroup.class}) @RequestBody CompanyGroupDTO companyGroupDTO){
        boolean result = battalionCommanderService.createCompany(companyGroupDTO);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    //checked
    @PreAuthorize("hasAnyRole('BATTALION_COMMANDER', 'ADMIN')")
    @PutMapping("/assign/company-commander")
    public ResponseEntity<Boolean> assignCompanyCommander(@RequestParam Integer companyCommanderId, @RequestParam Integer companyGroupId) {
        boolean result = battalionCommanderService.assignCompanyCommander(companyCommanderId, companyGroupId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //checked
    @PreAuthorize("hasAnyRole('BATTALION_COMMANDER', 'ADMIN')")
    @GetMapping("/get/battalion-requests")
    public ResponseEntity<List<SupplyRequest>> getBattalionRequests(){
        List<SupplyRequest> supplyRequests = battalionCommanderService.getBattalionRequests();
        return new ResponseEntity<>(supplyRequests, HttpStatus.OK);
    }

    //checked
    @PreAuthorize("hasAnyRole('BATTALION_COMMANDER', 'ADMIN')")
    @GetMapping("/get/company-requests")
    public ResponseEntity<List<SupplyRequest>> getCompaniesRequests(){
        List<SupplyRequest> supplyRequests = battalionCommanderService.getCompaniesRequests();
        return new ResponseEntity<>(supplyRequests, HttpStatus.OK);
    }

    //checked
    @PreAuthorize("hasAnyRole('BATTALION_COMMANDER', 'ADMIN')")
    @PutMapping("/update/battalion-resources")
    public ResponseEntity<ResourcesUpdateResponse> updateBattalionResources(@Validated({Default.class, UpdateGroup.class})@RequestBody BattalionGroupDTO battalionGroupDTO) {
        ResourcesUpdateResponse resourcesUpdateResponse = battalionCommanderService.updateBattalionResources(battalionGroupDTO);
        return new ResponseEntity<>(resourcesUpdateResponse, HttpStatus.OK);
    }

    //checked
    @PreAuthorize("hasAnyRole('BATTALION_COMMANDER', 'ADMIN')")
    @PostMapping("/send/resources-to-company")
    public ResponseEntity<ResourcesUpdateResponse> sendResources(@RequestBody SupplyRequest supplyRequest) {
        ResourcesUpdateResponse resourcesUpdateResponse = battalionCommanderService.sendResourcesToCompany(supplyRequest);
        return new ResponseEntity<>(resourcesUpdateResponse, HttpStatus.OK);
    }

    //checked
    @PreAuthorize("hasAnyRole('BATTALION_COMMANDER', 'ADMIN')")
    @PostMapping("/ask/for-resources")
    public ResponseEntity<Boolean> askForResources(@Valid @RequestBody ResourcesRequestDTO resourcesRequestDTO){
        boolean result = battalionCommanderService.askForResource(resourcesRequestDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //checked
    @PreAuthorize("hasAnyRole('BATTALION_COMMANDER', 'ADMIN')")
    @PutMapping("/confirm/getting-of-resources")
    public ResponseEntity<Boolean> confirmGettingOfResources(@RequestParam Integer supplyRequestId){
        boolean result = battalionCommanderService.confirmGettingOfResources(supplyRequestId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

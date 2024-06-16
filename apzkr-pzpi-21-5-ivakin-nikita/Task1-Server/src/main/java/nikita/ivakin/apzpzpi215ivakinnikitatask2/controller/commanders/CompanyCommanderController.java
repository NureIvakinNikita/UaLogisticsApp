package nikita.ivakin.apzpzpi215ivakinnikitatask2.controller.commanders;

import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto.ResourcesRequestDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto.groups.CompanyGroupDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto.groups.PlatGroupDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto.vlidation.CreateGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto.vlidation.UpdateGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.resources.ResourcesUpdateResponse;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.requests.SupplyRequest;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.commanders.CompanyCommanderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/com-com")
@RequiredArgsConstructor
public class CompanyCommanderController {

    private final CompanyCommanderService companyCommanderService;

    //checked
    @PreAuthorize("hasAnyRole('COMPANY_COMMANDER', 'ADMIN')")
    @GetMapping("/get-company-resources")
    public ResponseEntity<CompanyGroupDTO> getCompanyResources() {
        CompanyGroupDTO companyGroupDTO = companyCommanderService.getCompanyGroup();
        return new ResponseEntity<>(companyGroupDTO, HttpStatus.OK);
    }

    //checked
    @PreAuthorize("hasAnyRole('COMPANY_COMMANDER', 'ADMIN')")
    @GetMapping("/company-plat-groups")
    public ResponseEntity<List<PlatGroupDTO>> getCompanyPlatGroups() {
        List<PlatGroupDTO> platGroups = companyCommanderService.getCompanyPlatGroups();
        return new ResponseEntity<>(platGroups, HttpStatus.OK);
    }

    //checked
    @PreAuthorize("hasAnyRole('COMPANY_COMMANDER', 'ADMIN')")
    @PostMapping("/create/plat")
    public ResponseEntity<Boolean> createPlat(@Validated({Default.class, CreateGroup.class})@RequestBody PlatGroupDTO platGroupDTO) {
        boolean result = companyCommanderService.createPlat(platGroupDTO);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    //checked
    @PreAuthorize("hasAnyRole('COMPANY_COMMANDER', 'ADMIN')")
    @PutMapping("/update/company-resources")
    public ResponseEntity<ResourcesUpdateResponse> updateCompanyResources(@Validated({Default.class, UpdateGroup.class})@RequestBody CompanyGroupDTO companyGroupDTO) {
        ResourcesUpdateResponse resourcesUpdateResponse = companyCommanderService.updateCompanyResources(companyGroupDTO);
        return new ResponseEntity<>(resourcesUpdateResponse, HttpStatus.OK);
    }

    //checked
    @PreAuthorize("hasAnyRole('COMPANY_COMMANDER', 'ADMIN')")
    @PutMapping("/assign/plat-commander")
    public ResponseEntity<Boolean> assignPlatCommander(@RequestParam Integer platCommanderId, @RequestParam Integer platGroupId) {
        boolean result = companyCommanderService.assignPlatCommander(platCommanderId, platGroupId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //checked
    @PreAuthorize("hasAnyRole('COMPANY_COMMANDER', 'ADMIN')")
    @GetMapping("/get/company-requests")
    public ResponseEntity<List<SupplyRequest>> getCompanyRequests(){
        List<SupplyRequest> supplyRequests = companyCommanderService.getCompanyRequests();
        return new ResponseEntity<>(supplyRequests, HttpStatus.OK);
    }

    //checked
    @PreAuthorize("hasAnyRole('COMPANY_COMMANDER', 'ADMIN')")
    @GetMapping("/get/plat-requests")
    public ResponseEntity<List<SupplyRequest>> getPlatsRequests(){
        List<SupplyRequest> supplyRequests = companyCommanderService.getPlatsRequests();
        return new ResponseEntity<>(supplyRequests, HttpStatus.OK);
    }

    //checked
    @PreAuthorize("hasAnyRole('COMPANY_COMMANDER', 'ADMIN')")
    @PostMapping("/send/resources-to-plat")
    public ResponseEntity<ResourcesUpdateResponse> sendResources(@RequestBody SupplyRequest supplyRequest) {
        ResourcesUpdateResponse resourcesUpdateResponse = companyCommanderService.sendResourcesToPlat(supplyRequest);
        return new ResponseEntity<>(resourcesUpdateResponse, HttpStatus.OK);
    }

    //checked
    @PreAuthorize("hasAnyRole('COMPANY_COMMANDER', 'ADMIN')")
    @PostMapping("/ask/for-resources")
    public ResponseEntity<Boolean> askForResources(@Valid @RequestBody ResourcesRequestDTO resourcesRequestDTO){
        boolean result = companyCommanderService.askForResources(resourcesRequestDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //checked
    @PreAuthorize("hasAnyRole('COMPANY_COMMANDER', 'ADMIN')")
    @PutMapping("/confirm/getting-of-resources")
    public ResponseEntity<Boolean> confirmGettingOfResources(@RequestParam Integer supplyRequestId){
        boolean result = companyCommanderService.confirmGettingOfResources(supplyRequestId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

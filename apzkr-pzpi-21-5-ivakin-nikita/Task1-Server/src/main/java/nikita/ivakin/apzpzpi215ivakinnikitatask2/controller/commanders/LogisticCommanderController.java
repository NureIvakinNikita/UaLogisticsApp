package nikita.ivakin.apzpzpi215ivakinnikitatask2.controller.commanders;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto.CarCheckDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto.SupplyCarDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.CarCheck;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.requests.SupplyRequest;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.CarCheckService;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.ScanningDeviceService;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.commanders.LogisticCommanderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/log-com")
@AllArgsConstructor
public class LogisticCommanderController {

    private final LogisticCommanderService logisticCommanderService;
    private final CarCheckService carCheckService;
    private final ScanningDeviceService scanningDeviceService;

    //checked
    @PreAuthorize("hasAnyRole('LOGISTIC_COMMANDER', 'ADMIN')")
    @GetMapping("/get/all-requests")
    public ResponseEntity<List<SupplyRequest>> getAllRequests(){
        List<SupplyRequest> supplyRequests = logisticCommanderService.getAllRequests();
        return new ResponseEntity<>(supplyRequests, HttpStatus.OK);
    }

    //checked
    //Change status of request and sign who is going to do it and when
    @PreAuthorize("hasAnyRole('LOGISTIC_COMMANDER', 'ADMIN')")
    @PostMapping("/take/execution-of-supply-request/{id}")
    public ResponseEntity<Boolean> takeExecutionOfRequest(@PathVariable Integer id){
        boolean result = logisticCommanderService.takeExecutionOfRequest(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //checked
    @PreAuthorize("hasAnyRole('LOGISTIC_COMMANDER', 'ADMIN')")
    @PutMapping("/confirm/delivery-of-supply-request/{id}")
    public ResponseEntity<Boolean> confirmDeliveryOfRequest(@PathVariable Integer id){
        boolean result = logisticCommanderService.confirmDeliveryOfRequest(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('LOGISTIC_COMMANDER', 'ADMIN')")
    @PostMapping("/create/supply-car")
    public ResponseEntity<Boolean> createSupplyCar(@RequestBody SupplyCarDTO supplyCarDTO){
        boolean result = logisticCommanderService.createSupplyCar(supplyCarDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('LOGISTIC_COMMANDER', 'ADMIN')")
    @GetMapping("/get/car-checks/{id}")
    public ResponseEntity<List<CarCheckDTO>> getCarChecks(@PathVariable Integer id){
        List<CarCheckDTO> carCheckDTOs = scanningDeviceService.findCarChecksForCar(id);
        return new ResponseEntity<>(carCheckDTOs, HttpStatus.OK);
    }
}

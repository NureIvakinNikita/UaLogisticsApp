package nikita.ivakin.apzpzpi215ivakinnikitatask2.controller.commanders;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto.ScanRequest;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.service.ScanningDeviceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/device")
@AllArgsConstructor
public class ScanningDeviceController {

    private final ScanningDeviceService scanningDeviceService;

    @PreAuthorize("hasAnyRole('SCANNING_DEVICE')")
    @PostMapping("/scan-car")
    public ResponseEntity<String> scanCar(@RequestBody ScanRequest scanRequest){
        String result = scanningDeviceService.scanCar(scanRequest.getSupplyCarId(), scanRequest.getScanningDeviceId());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}

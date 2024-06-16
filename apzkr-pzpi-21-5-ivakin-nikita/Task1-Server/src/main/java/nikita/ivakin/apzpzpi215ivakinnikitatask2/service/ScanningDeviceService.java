package nikita.ivakin.apzpzpi215ivakinnikitatask2.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.auth.AuthenticationService;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.auth.RegisterRequest;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto.CarCheckDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto.ScanningDeviceDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.CarCheck;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.Post;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.ScanningDevice;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.SupplyCar;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.requests.SupplyRequest;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.enums.CarStatus;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.enums.RANK;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.enums.Role;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.exceptions.ScanningDeviceCreationException;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.exceptions.ScanningDeviceNotFound;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.ScanningDeviceRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class ScanningDeviceService {

    private final ScanningDeviceRepository scanningDeviceRepository;
    private final SupplyCarService supplyCarService;
    private final CarCheckService carCheckService;
    private final AuthenticationService authenticationService;
    private final PostService postService;


    public ScanningDevice findScanningDeviceByPost(Post post) {
        Optional<ScanningDevice> tempScanningDevice = scanningDeviceRepository.findScanningDeviceByPost(post);
        if (tempScanningDevice.isPresent()){
            return tempScanningDevice.get();
        } else {
            throw new ScanningDeviceNotFound("Device with such post doesn't exist.");
        }
    }

    public String scanCar(Integer supplyCarId, Integer scanningDeviceId) {
        SupplyCar supplyCar = supplyCarService.findSupplyCarById(supplyCarId);
        ScanningDevice scanningDevice = findScanningDeviceById(scanningDeviceId);

        if (scanningDevice.getTier() == 30) {
            carCheckService.checkCar(supplyCar, scanningDevice, CarStatus.FULL_CHECK);
            return supplyCar.getSupplyRequest().toString();
        } else if (supplyCar.getTier() < scanningDevice.getTier()) {
            carCheckService.checkCar(supplyCar, scanningDevice, CarStatus.RESOURCES_CHECK);
            return supplyCar.getSupplyRequest().getResourcesRequestId().toString();
        } else {
            carCheckService.checkCar(supplyCar, scanningDevice, CarStatus.TRAVEL_CHECK);
            return  "Car checked. Scanning went well, but getting data not allowed.";
        }
    }



    public ScanningDevice findScanningDeviceById(Integer id){
        Optional<ScanningDevice> tempScanningDevice = scanningDeviceRepository.findScanningDeviceById(id);
        if (tempScanningDevice.isPresent()) {
            return tempScanningDevice.get();
        } else {
            throw new ScanningDeviceNotFound("Scanning device with id " + id + " doesn't exist");
        }
    }

    @Transactional
    public ScanningDevice save(ScanningDevice scanningDevice) {
        try {
            RegisterRequest registerRequest = RegisterRequest.builder()
                    .email("scanning.device"+ scanningDevice.getPost().getId()+"@gmail.com")
                    .firstName("Scanning"+scanningDevice.getPost().getId())
                    .lastName("Device"+scanningDevice.getPost().getId())
                    .secondName("ScanningDevice"+scanningDevice.getPost().getId())
                    .password("ScanningDevice"+scanningDevice.getPost().getId())
                    .passportNumber(scanningDevice.getPost().getId()+"33456789")
                    .role(Role.SCANNING_DEVICE)
                    .rank(RANK.SCANNING_DEVICE)
                    .build();
            authenticationService.register(registerRequest);
        } catch (Exception e) {
            throw new ScanningDeviceCreationException("Something went wrong in creation of device.");
        }

        return scanningDeviceRepository.save(scanningDevice);
    }


    public ResponseEntity<SupplyRequest> getCarSupplies(Integer supplyCarId) {
        return null;
    }

    public ScanningDeviceDTO mpaScanningDeviceToDto(ScanningDevice scanningDevice) {
        ScanningDeviceDTO scanningDeviceDTO = ScanningDeviceDTO.builder()
                .id(scanningDevice.getId())
                .postDTO(postService.mapPostToDto(scanningDevice.getPost()))
                .tier(scanningDevice.getTier()).build();
        return scanningDeviceDTO;
    }

    private static final String IOT_SERVER_URL = "http://localhost:8081/get-info";


    public List<CarCheckDTO> findCarChecksForCar(Integer id) {
        List<CarCheck> carChecks = carCheckService.findCarChecksForCar(id);
        List<CarCheckDTO> carCheckDTOS = new ArrayList<>();
        for (CarCheck carCheck : carChecks) {
            carCheckDTOS.add(carCheckService.mapCarCheckToDto(carCheck, mpaScanningDeviceToDto(carCheck.getScanningDevice())));
        }
        return carCheckDTOS;
    }
}


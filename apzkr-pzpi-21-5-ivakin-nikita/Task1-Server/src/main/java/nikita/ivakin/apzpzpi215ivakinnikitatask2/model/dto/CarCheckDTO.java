package nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto;

import lombok.Builder;
import lombok.Data;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.ScanningDevice;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.enums.CarStatus;

import java.time.LocalDateTime;

@Data
@Builder
public class CarCheckDTO {

    private Integer id;

    private ScanningDeviceDTO scanningDeviceDTO;

    private Integer supplyCarId;

    private CarStatus carStatus;

    private LocalDateTime localDateTime;
}

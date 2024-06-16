package nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto;

import lombok.Builder;
import lombok.Data;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.ScanningDevice;

@Data
@Builder
public class PostDTO {

    private Integer id;

    private String location;

    private Integer scanningDeviceId;

}

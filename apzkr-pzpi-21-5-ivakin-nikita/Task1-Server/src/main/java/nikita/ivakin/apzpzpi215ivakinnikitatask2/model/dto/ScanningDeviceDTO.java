package nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ScanningDeviceDTO {

    private Integer id;

    private PostDTO postDTO;

    private Integer tier;
}

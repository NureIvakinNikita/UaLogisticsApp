package nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScanRequest {
    @JsonProperty("supplyCarId")
    private Integer supplyCarId;
    @JsonProperty("scanningDeviceId")
    private Integer scanningDeviceId;

}

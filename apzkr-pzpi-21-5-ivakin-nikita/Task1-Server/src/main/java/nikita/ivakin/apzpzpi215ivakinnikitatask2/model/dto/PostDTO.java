package nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.ScanningDevice;

@Data
@Builder
public class PostDTO {

    private Integer id;

    @NotEmpty(message = "Location can't be empty!")
    @NotNull(message = "Location can't be empty!")
    private String location;

    private Integer scanningDeviceId;

}

package nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.resources;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ResourcesUpdateResponse {

    private boolean updated;
    private boolean needForSupply;
}

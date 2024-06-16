package nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto.commanders;

import lombok.Builder;
import lombok.Data;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto.groups.PlatGroupDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.enums.POST;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.enums.RANK;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.enums.Role;

@Data
@Builder
public class PlatCommanderDTO {

    private Integer id;

    private String firstName;

    private String lastName;

    private String secondName;

    private String passportNumber;

    private RANK rank;

    private POST post;

    private Role role;

    private Integer age;

    private String email;

    private Integer platGroupId;

    private PlatGroupDTO platGroup;
}

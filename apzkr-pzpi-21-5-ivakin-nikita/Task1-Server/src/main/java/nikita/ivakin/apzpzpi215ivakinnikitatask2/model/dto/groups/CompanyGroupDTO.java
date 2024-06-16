package nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto.groups;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Data;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto.commanders.CompanyCommanderDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto.vlidation.CreateGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto.vlidation.UpdateGroup;

@Data
@Builder
public class CompanyGroupDTO {
    private Integer id;

    @Min(value = 160, message = "Personnel count in company have to be 160 - 200.", groups = {CreateGroup.class})
    @Max(value = 200, message = "Personnel count in company have to be 160 - 200.", groups = {CreateGroup.class})
    @Min(value = 0, message = "Personnel count in company should be bigger or equal 0.", groups = {UpdateGroup.class})
    private int personnelCount;


    @Min(value = 0, message = "Amount of ammo should be bigger or equal 0.", groups = {CreateGroup.class, UpdateGroup.class})
    private int ammo556x45ArCount;
    @Min(value = 0, message = "Amount of ammo should be bigger or equal 0.", groups = {CreateGroup.class, UpdateGroup.class})
    private int ammo545x39AkRpkCount;
    @Min(value = 0, message = "Amount of ammo should be bigger or equal 0.", groups = {CreateGroup.class, UpdateGroup.class})
    private int ammo762x39AkCount;
    @Min(value = 0, message = "Amount of ammo should be bigger or equal 0.", groups = {CreateGroup.class, UpdateGroup.class})
    private int ammo145KpvtCount;
    @Min(value = 0, message = "Amount of ammo should be bigger or equal 0.", groups = {CreateGroup.class, UpdateGroup.class})
    private int ammo40mmRpgCount;
    @Min(value = 0, message = "Amount of ammo should be bigger or equal 0.", groups = {CreateGroup.class, UpdateGroup.class})
    private int ammo40mmGpCount;
    @Min(value = 0, message = "Amount of ammo should be bigger or equal 0.", groups = {CreateGroup.class, UpdateGroup.class})
    private int ammo762PktCount;
    @Min(value = 0, message = "Amount of grenades should be bigger or equal 0.", groups = {CreateGroup.class, UpdateGroup.class})
    private int defensiveGrenadesCount;
    @Min(value = 0, message = "Amount of grenades should be bigger or equal 0.", groups = {CreateGroup.class, UpdateGroup.class})
    private int offensiveGrenadesCount;

    @Min(value = 250, message = "Body Armor count in company have to be at least 250.", groups = {CreateGroup.class})
    @Min(value = 0, message = "Body Armor count in company have to be at least 0.", groups = UpdateGroup.class)
    private int bodyArmorCount;
    @Min(value = 250, message = "Helmets count in company have to be at least 250.", groups = {CreateGroup.class})
    @Min(value = 0, message = "Helmets count in company have to be at least 0.", groups = UpdateGroup.class)
    private int helmetsCount;

    @Min(value = 213, message = "Rifles count in company have to be at least 213.", groups = {CreateGroup.class})
    @Min(value = 0, message = "Rifles count in company have to be at least 0.", groups = UpdateGroup.class)
    private int riflesCount;
    @Min(value = 34, message = "Machine guns count in company have to be at least 34.", groups = {CreateGroup.class})
    @Min(value = 0, message = "Machine guns in company have to be at least 0.", groups = UpdateGroup.class)
    private int machineGunsCount;
    @Min(value = 600, message = "Dry rations count in company have to be at least 600.", groups = {CreateGroup.class})
    @Min(value = 0, message = "Dry rations in company have to be at least 0.", groups = UpdateGroup.class)
    private int dryRationsCount;
    @Min(value = 0, message = "Amount of shouldn't be 0", groups = {CreateGroup.class})
    @Min(value = 0, message = "Amount of shouldn't be less than 0", groups = {UpdateGroup.class})
    private int foodCount;
    private int tankCount;
    @Min(value = 0, message = "Amount of apc should be bigger or equal 0.", groups = {CreateGroup.class, UpdateGroup.class})
    private int apcCount;

    private CompanyCommanderDTO companyCommanderDTO;
}

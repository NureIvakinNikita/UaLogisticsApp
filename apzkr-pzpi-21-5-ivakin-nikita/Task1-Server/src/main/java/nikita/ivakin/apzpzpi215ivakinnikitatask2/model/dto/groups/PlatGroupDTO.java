package nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto.groups;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Data;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto.commanders.PlatCommanderDTO;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto.vlidation.CreateGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto.vlidation.UpdateGroup;

@Data
@Builder
public class PlatGroupDTO {

    private Integer id;
    @Min(value = 22, message = "Personnel count in plat have to be 22.", groups = {CreateGroup.class})
    @Max(value = 22, message = "Personnel count in plat have to be 22.", groups = {CreateGroup.class})
    @Min(value = 0, message = "Personnel count in plat should be bigger or equal 0.", groups = {UpdateGroup.class})
    private int personnelCount;


    @Min(value = 8550, message = "Amount of ammo should be bigger or equal 8550.", groups = {CreateGroup.class})
    @Min(value = 0, message = "Amount of ammo should be bigger or equal 0.", groups = {UpdateGroup.class})
    private int ammo556x45ArCount;
    @Min(value = 8550, message = "Amount of ammo should be bigger or equal 8550.", groups = {CreateGroup.class})
    @Min(value = 0, message = "Amount of ammo should be bigger or equal 0.", groups = {UpdateGroup.class})
    private int ammo545x39AkRpkCount;
    @Min(value = 8550, message = "Amount of ammo should be bigger or equal 8550.", groups = {CreateGroup.class})
    @Min(value = 0, message = "Amount of ammo should be bigger or equal 0.", groups = {UpdateGroup.class})
    private int ammo762x39AkCount;
    @Min(value = 3000, message = "Amount of ammo should be bigger or equal 3000.", groups = {CreateGroup.class})
    @Min(value = 0, message = "Amount of ammo should be bigger or equal 0.", groups = {UpdateGroup.class})
    private int ammo145KpvtCount;
    @Min(value = 120, message = "Amount of ammo should be bigger or equal 120.", groups = {CreateGroup.class})
    @Min(value = 0, message = "Amount of ammo should be bigger or equal 0.", groups = {UpdateGroup.class})
    private int ammo40mmRpgCount;
    @Min(value = 120, message = "Amount of ammo should be bigger or equal 120.", groups = {CreateGroup.class})
    @Min(value = 0, message = "Amount of ammo should be bigger or equal 0.", groups = {UpdateGroup.class})
    private int ammo40mmGpCount;
    @Min(value = 6000, message = "Amount of ammo should be bigger or equal 6000.", groups = {CreateGroup.class})
    @Min(value = 0, message = "Amount of ammo should be bigger or equal 0.", groups = {UpdateGroup.class})
    private int ammo762PktCount;
    @Min(value = 0, message = "Amount of grenades should be bigger or equal 0.", groups = {CreateGroup.class, UpdateGroup.class})
    private int defensiveGrenadesCount;
    @Min(value = 0, message = "Amount of grenades should be bigger or equal 0.", groups = {CreateGroup.class, UpdateGroup.class})
    private int offensiveGrenadesCount;

    @Min(value = 22, message = "Body Armor count in plat have to be at least 22.", groups = {CreateGroup.class})
    @Min(value = 0, message = "Body Armor count should be bigger or equal 0.", groups = {UpdateGroup.class})
    private int bodyArmorCount;

    @Min(value = 22, message = "Helmets count in plat have to be at least 22.", groups = {CreateGroup.class})
    @Min(value = 0, message = "Body Armor count should be bigger or equal 0.", groups = {UpdateGroup.class})
    private int helmetsCount;

    @Min(value = 19, message = "Rifles count in plat have to be at least 19.", groups = {CreateGroup.class})
    @Min(value = 0, message = "Rifles count should be bigger or equal 0.", groups = {UpdateGroup.class})
    private int riflesCount;
    @Min(value = 3, message = "Machine guns count in plat have to be at least 3.", groups = {CreateGroup.class})
    @Min(value = 0, message = "Machine guns count should be bigger or equal 0.", groups = {UpdateGroup.class})
    private int machineGunsCount;
    @Min(value = 66, message = "Dry rations count in plat have to be at least 66.", groups = {CreateGroup.class})
    @Min(value = 0, message = "Dry rations count should be bigger or equal 0.", groups = {UpdateGroup.class})
    private int dryRationsCount;
    @Min(value = 0, message = "Amount of shouldn't be 0", groups = {CreateGroup.class})
    @Min(value = 0, message = "Amount of shouldn't be less than 0", groups = {UpdateGroup.class})
    private int foodCount;
    private int tankCount;
    private int apcCount;

    private PlatCommanderDTO platCommanderDTO;


}

package nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto.groups;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Data;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto.vlidation.CreateGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto.vlidation.UpdateGroup;

@Data
@Builder
public class BrigadeGroupDTO {
    private Integer id;

    @Min(value = 7000, message = "Personnel count in brigade have to be 7000 - 8000.", groups = {CreateGroup.class})
    @Max(value = 8000, message = "Personnel count in brigade have to be 7000 - 8000.", groups = {CreateGroup.class})
    @Min(value = 0, message = "Personnel count in brigade should be bigger or equal 0.", groups = {UpdateGroup.class})
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

    @Min(value = 10000, message = "Body Armor count in brigade have to be at least 10000.")
    @Min(value = 0, message = "Body Armor count in brigade have to be at least 0.", groups = UpdateGroup.class)
    private int bodyArmorCount;
    @Min(value = 10000, message = "Helmets count in brigade have to be at least 10000.")
    @Min(value = 0, message = "Helmets count in brigade have to be at least 0.", groups = UpdateGroup.class)
    private int helmetsCount;

    @Min(value = 8337, message = "Rifles count in brigade have to be at least 8337.")
    @Min(value = 0, message = "Rifles count in brigade have to be at least 0.", groups = UpdateGroup.class)
    private int riflesCount;
    @Min(value = 1155, message = "Machine guns count in brigade have to be at least 1155.")
    @Min(value = 0, message = "Machine guns in brigade have to be at least 0.", groups = UpdateGroup.class)
    private int machineGunsCount;
    @Min(value = 24000, message = "Dry rations count in brigade have to be at least 24000.")
    @Min(value = 0, message = "Dry rations in brigade have to be at least 0.", groups = UpdateGroup.class)
    private int dryRationsCount;
    @Min(value = 0, message = "Amount of shouldn't be 0", groups = {CreateGroup.class})
    @Min(value = 0, message = "Amount of shouldn't be less than 0", groups = {UpdateGroup.class})
    private int foodCount;
    private int tankCount;
    @Min(value = 0, message = "Amount of apc should be bigger or equal 0.", groups = {CreateGroup.class, UpdateGroup.class})
    private int apcCount;
}

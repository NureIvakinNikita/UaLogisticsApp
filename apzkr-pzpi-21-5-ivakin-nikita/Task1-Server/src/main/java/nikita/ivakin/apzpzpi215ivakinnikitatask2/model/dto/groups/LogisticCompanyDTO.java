package nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto.groups;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto.vlidation.CreateGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto.vlidation.UpdateGroup;

@Data
@Builder
public class LogisticCompanyDTO {
    private Integer id;
    @Min(value = 160, message = "Personnel count in logistic company have to be 160 - 200.", groups = {CreateGroup.class})
    @Max(value = 200, message = "Personnel count in logistic company to be 160 - 200.", groups = {CreateGroup.class})
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

    @Min(value = 250, message = "Body Armor count in logistic company have to be at least 250.", groups = {CreateGroup.class})
    private int bodyArmorCount;
    @Min(value = 250, message = "Helmets count in  logistic company have to be at least 250.", groups = {CreateGroup.class})
    private int helmetsCount;

    @Min(value = 213, message = "Rifles count in logistic company have to be at least 213.", groups = {CreateGroup.class})
    private int riflesCount;

    @Min(value = 0, message = "Amount of machine guns count should be 0 or bigger.", groups = {CreateGroup.class})
    private int machineGunsCount;
    @Min(value = 600, message = "Dry rations count in logistic company have to be at least 600.",groups = {CreateGroup.class})
    private int dryRationsCount;
    @NotNull(message = "Amount of shouldn't be 0", groups = {CreateGroup.class})
    private int foodCount;
    private int tankCount;
    @Min(value = 0, message = "Amount of apc should be bigger or equal 0.", groups = {CreateGroup.class})
    private int apcCount;
}

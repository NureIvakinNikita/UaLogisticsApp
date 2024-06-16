package nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.enums.Role;

@Data
@Builder
public class ResourcesRequestDTO {

    @NotNull(message = "Commander id must be added.")
    private Integer commanderId;
    @NotNull(message = "Military group id must be added.")
    private Integer militaryGroupId;
    @NotNull(message = "Role of commander must be added.")
    private Role roleOfCommander;
    @Min(value = 0, message = "Amount of ammo should be bigger or equal 0.")
    private int ammo556x45ArCount;
    @Min(value = 0, message = "Amount of ammo should be bigger or equal 0.")
    private int ammo545x39AkRpkCount;
    @Min(value = 0, message = "Amount of ammo should be bigger or equal 0.")
    private int ammo762x39AkCount;
    @Min(value = 0, message = "Amount of ammo should be bigger or equal 0.")
    private int ammo145KpvtCount;
    @Min(value = 0, message = "Amount of ammo should be bigger or equal 0.")
    private int ammo40mmRpgCount;
    @Min(value = 0, message = "Amount of ammo should be bigger or equal 0.")
    private int ammo40mmGpCount;
    @Min(value = 0, message = "Amount of ammo should be bigger or equal 0.")
    private int ammo762PktCount;
    @Min(value = 0, message = "Amount of grenades should be bigger or equal 0.")
    private int defensiveGrenadesCount;
    @Min(value = 0, message = "Amount of grenades should be bigger or equal 0.")
    private int offensiveGrenadesCount;
    @Min(value = 0, message = "Amount of body armor should be bigger or equal 0.")
    private int bodyArmorCount;
    @Min(value = 0, message = "Amount of helmets should be bigger or equal 0.")
    private int helmetsCount;
    @Min(value = 0, message = "Amount of rifles should be bigger or equal 0.")
    private int riflesCount;
    @Min(value = 0, message = "Amount of machine guns should be bigger or equal 0.")
    private int machineGunsCount;
    @Min(value = 0, message = "Amount of dry rations should be bigger or equal 0.")
    private int dryRationsCount;
    @Min(value = 0, message = "Amount of food should be bigger or equal 0.")
    private int foodCount;
    @Min(value = 0, message = "Amount of tanks should be bigger or equal 0.")
    private int tankCount;
    @Min(value = 0, message = "Amount of apc should be bigger or equal 0.")
    private int apcCount;
}

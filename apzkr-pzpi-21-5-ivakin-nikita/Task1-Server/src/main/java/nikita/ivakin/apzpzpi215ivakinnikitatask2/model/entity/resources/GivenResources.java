package nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.resources;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.enums.ResourcesType;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.enums.Role;

import java.time.LocalDate;

@Entity
@Table(name = "given_resources", schema = "project")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class GivenResources {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "given_resources_id")
    private Integer id;

    private Integer commanderId;

    private Integer militaryGroupId;

    private Integer brigadeCommanderId;

    @Enumerated(EnumType.STRING)
    private Role roleOfCommander;

    private LocalDate issueDate;

    @Enumerated(EnumType.STRING)
    private ResourcesType allocationOfResources;

    @Column(name = "ammo_556x45ar_count")
    private int ammo556x45ArCount;

    @Column(name = "ammo_545x39ak_rpk_count")
    private int ammo545x39AkRpkCount;

    @Column(name = "ammo_762x39ak_count")
    private int ammo762x39AkCount;

    @Column(name = "ammo_145kpvt_count")
    private int ammo145KpvtCount;

    @Column(name = "ammo_40mm_rpg_count")
    private int ammo40mmRpgCount;

    @Column(name = "ammo_40mm_gp_count")
    private int ammo40mmGpCount;

    @Column(name = "ammo_762pkt_count")
    private int ammo762PktCount;
    private int defensiveGrenadesCount;
    private int offensiveGrenadesCount;
    private int bodyArmorCount;
    private int helmetsCount;
    private int riflesCount;
    private int machineGunsCount;
    private int dryRationsCount;
    private int foodCount;
    private int tankCount;
    private int apcCount;
}

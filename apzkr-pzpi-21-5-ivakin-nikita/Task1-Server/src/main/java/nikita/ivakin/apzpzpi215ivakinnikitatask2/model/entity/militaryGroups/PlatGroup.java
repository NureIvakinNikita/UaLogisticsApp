package nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.militaryGroups;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.commanders.PlatCommander;

@Entity
@Table(name = "plat_group", schema = "project")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "plat_group_id"))
public class PlatGroup extends MilitaryGroup {


    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "plat_commander_id", unique = true)
    private PlatCommander platCommanderId;


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "company_group_id", unique = true)
    private CompanyGroup companyGroup;

}

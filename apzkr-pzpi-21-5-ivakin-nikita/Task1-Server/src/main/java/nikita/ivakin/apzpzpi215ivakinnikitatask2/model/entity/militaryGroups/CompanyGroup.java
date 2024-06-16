package nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.militaryGroups;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.commanders.CompanyCommander;

import java.util.List;

@Entity
@Table(name = "company_group", schema = "project")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "company_group_id"))
public class CompanyGroup extends MilitaryGroup {



    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "company_commander_id", unique = true)
    private CompanyCommander companyCommanderId;


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "battalion_group_id", unique = true)
    private BattalionGroup battalionGroup;

    @OneToMany(mappedBy = "companyGroup",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.LAZY
    )
    private List<PlatGroup> platGroups;


}

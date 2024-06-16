package nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.militaryGroups;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.commanders.LogisticCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.SupplyCar;

import java.util.List;

@Entity
@Table(name = "logistic_company", schema = "project")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "logistic_company_id"))
public class LogisticCompany extends MilitaryGroup{


    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "logistic_commander_id")
    private LogisticCommander logisticCommanderId;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "brigade_group_id", unique = true)
    private BrigadeGroup brigadeGroup;

    @OneToMany(mappedBy = "logisticCompany",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.LAZY
    )
    private List<SupplyCar> supplyCars;

}





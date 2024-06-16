package nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.commanders;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.militaryGroups.BattalionGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.militaryGroups.BrigadeGroup;

import java.util.List;

@Entity
@Table(name = "battalion_commander", schema = "project")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "battalion_commander_id"))
public class BattalionCommander extends Commander{

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "battalion_group_id", unique = true)
    private BattalionGroup battalionGroup;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "brigade_commander_id", unique = true)
    private BrigadeCommander brigadeCommander;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "brigade_group_id", unique = true)
    private BrigadeGroup brigadeGroup;


    @OneToMany(mappedBy = "battalionCommander",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.LAZY
    )
    private List<CompanyCommander> companyCommanders;

}

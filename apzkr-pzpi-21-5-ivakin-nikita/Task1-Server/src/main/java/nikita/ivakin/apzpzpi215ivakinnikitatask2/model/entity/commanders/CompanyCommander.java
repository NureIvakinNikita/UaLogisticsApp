package nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.commanders;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.militaryGroups.BattalionGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.militaryGroups.CompanyGroup;

import java.util.List;

@Entity
@Table(name = "company_commander", schema = "project")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "company_commander_id"))
public class CompanyCommander extends Commander{


    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "company_group_id")
    private CompanyGroup companyGroup;


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "battalion_commander_id", unique = true)
    private BattalionCommander battalionCommander;


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "battalion_group_id", unique = true)
    private BattalionGroup battalionGroup;

    @OneToMany(mappedBy = "companyCommander",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.LAZY
    )
    private List<PlatCommander> platCommanders;

    @Column(name = "brigade_commander_id")
    private Integer brigadeCommanderId;



}

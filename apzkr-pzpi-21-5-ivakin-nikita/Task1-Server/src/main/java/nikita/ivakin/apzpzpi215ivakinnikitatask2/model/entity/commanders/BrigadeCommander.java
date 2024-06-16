package nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.commanders;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.militaryGroups.BrigadeGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.enums.POST;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.enums.RANK;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.enums.Role;

import java.util.List;

@Entity

@Table(name = "brigade_commander", schema = "project")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "brigade_commander_id"))
@SuperBuilder
public class BrigadeCommander extends Commander{

    public BrigadeCommander(Integer id, String firstName, String lastName, String secondName, RANK rank, POST post, Role role, Integer age, String email, String password, String passportNumber, BrigadeGroup brigadeGroupId) {
        super(id, firstName, lastName, secondName, rank, post, role, age, email, password, passportNumber);
        this.brigadeGroupId = brigadeGroupId;
    }

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "brigade_group_id", unique = true)
    private BrigadeGroup brigadeGroupId;

    @OneToMany(mappedBy = "brigadeCommander",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.LAZY
    )
    private List<BattalionCommander> battalionCommanders;


    @OneToMany(mappedBy = "brigadeCommander",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.LAZY
    )
    private List<LogisticCommander> logisticCommanders;


}

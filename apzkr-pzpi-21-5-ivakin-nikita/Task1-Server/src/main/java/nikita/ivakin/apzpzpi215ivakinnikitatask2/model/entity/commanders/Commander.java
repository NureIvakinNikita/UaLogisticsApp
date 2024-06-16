package nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.commanders;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.enums.POST;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.enums.RANK;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.enums.Role;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
public abstract class Commander {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "rank")
    private RANK rank;

    @Column(name = "post")
    private POST post;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Column(name = "age")
    private Integer age;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "passport_number")
    private String passportNumber;

    /*@Column(name = "last_name")
    private Long idOfGroup;*/
}
package nikita.ivakin.apzpzpi215ivakinnikitatask2.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.enums.POST;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.enums.RANK;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.enums.Role;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.token.Token;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user", schema = "project")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "user_seq_generator")
    @SequenceGenerator(name = "user_seq_generator", sequenceName = "project._user_id_seq", allocationSize = 1, initialValue = 1)
    private Integer id;

    @NotBlank(message = "First name cannot be blank")
    @Size(min = 2, max = 30, message = "First name must be between 2 and 30 characters")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    @Size(min = 2, max = 30, message = "Last name must be between 2 and 30 characters")
    @Column(name = "last_name")
    private String lastName;

    @Size(min = 2, max = 30, message = "Second name must be between 2 and 30 characters")
    @Column(name = "second_name")
    private String secondName;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email cannot be blank")
    @Column(name = "email")
    private String email;

    @NotBlank(message = "Passport number cannot be blank")
    @Size(min = 9, max = 9, message = "Passport number must be exactly 9 digits")
    @Pattern(regexp = "\\d{9}", message = "Passport number must contain only digits")
    @Column(name = "passport_number")
    private String passportNumber;

    @Column(name = "password")
    private String password;

    @NotNull(message = "Rank cannot be null")
    @Enumerated(EnumType.STRING)
    @Column(name = "rank")
    private RANK rank;

    @Enumerated(EnumType.STRING)
    @Column(name = "post")
    private POST post;

    @NotNull(message = "Role cannot be null")
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
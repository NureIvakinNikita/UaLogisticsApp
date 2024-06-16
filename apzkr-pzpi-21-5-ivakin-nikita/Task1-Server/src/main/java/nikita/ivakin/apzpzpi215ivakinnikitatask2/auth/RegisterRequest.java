package nikita.ivakin.apzpzpi215ivakinnikitatask2.auth;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.enums.POST;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.enums.RANK;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.enums.Role;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "First name cannot be blank")
    @Size(min = 2, max = 30, message = "First name must be between 2 and 30 characters")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    @Size(min = 2, max = 30, message = "Last name must be between 2 and 30 characters")
    private String lastName;

    @Size(min = 2, max = 30, message = "Second name must be between 2 and 30 characters")
    private String secondName;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @NotNull(message = "Rank cannot be null")
    @Enumerated(EnumType.STRING)
    private RANK rank;

    @Enumerated(EnumType.STRING)
    private POST post;

    @NotNull(message = "Role cannot be null")
    @Enumerated(EnumType.STRING)
    private Role role;

    private int age;

    @NotBlank(message = "Passport number cannot be blank")
    @Size(min = 9, max = 9, message = "Passport number must be exactly 9 digits")
    @Pattern(regexp = "^\\d{9}$", message = "Passport number must contain exactly 9 digits")
    private String passportNumber;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,20}$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, and one digit")
    private String password;


}

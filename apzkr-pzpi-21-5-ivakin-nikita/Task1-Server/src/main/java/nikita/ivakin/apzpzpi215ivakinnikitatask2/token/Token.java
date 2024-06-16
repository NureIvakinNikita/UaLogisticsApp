package nikita.ivakin.apzpzpi215ivakinnikitatask2.token;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.user.User;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "token", schema = "project")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "token_seq_generator")
    @SequenceGenerator(name = "token_seq_generator", sequenceName = "project.token_id_seq", allocationSize = 1, initialValue = 1)
    public Integer id;

    @Column(unique = true)
    public String token;

    @Enumerated(EnumType.STRING)
    public TokenType tokenType = TokenType.BEARER;

    public boolean revoked;

    public boolean expired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User user;
}

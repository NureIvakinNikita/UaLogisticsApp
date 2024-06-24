package nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "scanning_device", schema = "project")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class ScanningDevice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true,
             fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id", unique = true)
    private Post post;

    @Min(value=0, message = "Tier can must be 0 or bigger.")
    private Integer tier;
}

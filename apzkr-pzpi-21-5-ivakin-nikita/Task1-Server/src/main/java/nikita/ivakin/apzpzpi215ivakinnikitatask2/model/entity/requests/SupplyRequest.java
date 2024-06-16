package nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.requests;

import jakarta.persistence.*;
import lombok.*;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.enums.Role;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.enums.Status;

import java.time.LocalDate;

@Entity
@Table(name = "supply_request", schema = "project")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class SupplyRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private Integer requestId;

    private Integer executiveGroupId;

    private Integer executiveCommanderId;

    private Integer commanderId;

    private Integer militaryGroupId;

    private Integer seniorMilitaryGroupId;

    @Column(name = "brigade_commander_id")
    private Integer brigadeCommanderId;

    @Enumerated(EnumType.STRING)
    private Role roleOfCommander;

    @Enumerated(EnumType.STRING)
    private Role roleOfExecutiveCommander;

    @Column(name = "date_of_request")
    private LocalDate dateOfRequest;

    @Column(name = "date_of_executing")
    private LocalDate dateOfExecuting;

    @Column(name = "delivery_complition_date")
    private LocalDate deliveryComplitionDate;

    @Column(name = "execution_complition_date")
    private LocalDate executionСomplitionDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "resources_request_id", unique = true)
    private ResourcesRequest resourcesRequestId;

    @Override
    public String toString() {
        return "SupplyRequest{" +
                "requestId=" + requestId +"\n" +
                ", executiveGroupId=" + executiveGroupId +"\n" +
                ", executiveCommanderId=" + executiveCommanderId +"\n" +
                ", commanderId=" + commanderId +"\n" +
                ", militaryGroupId=" + militaryGroupId +"\n" +
                ", seniorMilitaryGroupId=" + seniorMilitaryGroupId +"\n" +
                ", brigadeCommanderId=" + brigadeCommanderId +"\n" +
                ", roleOfCommander=" + roleOfCommander +"\n" +
                ", roleOfExecutiveCommander=" + roleOfExecutiveCommander +"\n" +
                ", dateOfRequest=" + dateOfRequest +"\n" +
                ", dateOfExecuting=" + dateOfExecuting +"\n" +
                ", deliveryComplitionDate=" + deliveryComplitionDate +"\n" +
                ", executionСomplitionDate=" + executionСomplitionDate +"\n" +
                ", status=" + status +"\n" +
                ", resourcesRequestId=" + resourcesRequestId.toString() +
                '}';
    }
}

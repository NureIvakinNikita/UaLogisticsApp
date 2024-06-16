package nikita.ivakin.apzpzpi215ivakinnikitatask2.model.dto;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.requests.ResourcesRequest;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.enums.Role;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.enums.Status;

import java.time.LocalDate;

@Data
@Builder
public class SupplyRequestDTO {

    private Integer requestId;
    private Integer executiveGroupId;
    private Integer executiveCommanderId;
    private Integer commanderId;
    private Integer militaryGroupId;
    private Integer seniorMilitaryGroupId;
    @Enumerated(EnumType.STRING)
    private Role roleOfCommander;
    @Enumerated(EnumType.STRING)
    private Role roleOfExecutiveCommander;
    private LocalDate dateOfRequest;
    private LocalDate executionСomplitionDate;
    @Enumerated(EnumType.STRING)
    private Status status;
    private ResourcesRequest resourcesRequestId;
}

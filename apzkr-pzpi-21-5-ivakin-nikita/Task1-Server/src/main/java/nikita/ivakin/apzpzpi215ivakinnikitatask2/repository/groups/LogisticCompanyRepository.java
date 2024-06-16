package nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.groups;

import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.commanders.LogisticCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.militaryGroups.BrigadeGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.militaryGroups.LogisticCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LogisticCompanyRepository extends JpaRepository<LogisticCompany, Long> {

    Optional<LogisticCompany> findLogisticCompanyByBrigadeGroup(BrigadeGroup brigadeGroup);

    Optional<LogisticCompany> findLogisticCompanyById(Integer id);

    Optional<LogisticCompany> findLogisticCompanyByLogisticCommanderId(LogisticCommander logisticCommanderId);
}

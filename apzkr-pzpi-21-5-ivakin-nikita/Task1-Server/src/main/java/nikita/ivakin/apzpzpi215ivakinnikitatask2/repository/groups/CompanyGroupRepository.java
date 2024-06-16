package nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.groups;

import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.commanders.CompanyCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.militaryGroups.BattalionGroup;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.militaryGroups.CompanyGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyGroupRepository extends JpaRepository<CompanyGroup, Long> {

    Optional<CompanyGroup> findCompanyGroupById(Integer id);

    Optional<CompanyGroup> findCompanyGroupByCompanyCommanderId(CompanyCommander companyCommander);

    ArrayList<CompanyGroup> findAll();

    List<CompanyGroup> findAllByBattalionGroup(BattalionGroup battalionGroup);
}

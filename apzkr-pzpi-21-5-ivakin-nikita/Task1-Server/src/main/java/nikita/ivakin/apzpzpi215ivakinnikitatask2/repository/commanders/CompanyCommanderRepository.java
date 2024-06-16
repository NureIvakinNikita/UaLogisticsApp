package nikita.ivakin.apzpzpi215ivakinnikitatask2.repository.commanders;

import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.commanders.BattalionCommander;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.commanders.CompanyCommander;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyCommanderRepository extends JpaRepository<CompanyCommander, Long> {

    Optional<CompanyCommander> findCompanyCommanderById(Integer id);

    Optional<CompanyCommander> findCompanyCommanderByEmail(String email);

    List<CompanyCommander> findCompanyCommandersByBattalionCommander(BattalionCommander battalionCommander);
}

package nikita.ivakin.apzpzpi215ivakinnikitatask2.repository;

import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.SupplyCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplyCarRepository extends JpaRepository<SupplyCar, Integer> {
    Optional<SupplyCar> findSupplyCarById(Integer id);
}

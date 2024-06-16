package nikita.ivakin.apzpzpi215ivakinnikitatask2.repository;

import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.CarCheck;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.model.entity.SupplyCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarCheckRepository extends JpaRepository<CarCheck, Integer> {
    List<CarCheck> findBySupplyCar(SupplyCar supplyCar);
}

package nikita.ivakin.apzpzpi215ivakinnikitatask2.repository;

import nikita.ivakin.apzpzpi215ivakinnikitatask2.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<User, Integer> {
}

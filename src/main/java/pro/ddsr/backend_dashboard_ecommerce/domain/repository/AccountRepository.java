
package pro.ddsr.backend_dashboard_ecommerce.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.Account;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    // Define repository methods here

   Optional<Account> findByUsername(String username);
}

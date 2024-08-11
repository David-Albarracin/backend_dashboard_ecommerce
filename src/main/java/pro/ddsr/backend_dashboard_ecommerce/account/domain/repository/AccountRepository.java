
package pro.ddsr.backend_dashboard_ecommerce.account.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pro.ddsr.backend_dashboard_ecommerce.account.persistence.Account;

import java.util.Optional;


@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    // Define repository methods here

   Optional<Account> findByUsername(String username);
}

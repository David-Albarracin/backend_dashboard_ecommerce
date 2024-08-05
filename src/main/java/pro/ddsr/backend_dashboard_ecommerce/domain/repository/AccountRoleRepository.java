
package pro.ddsr.backend_dashboard_ecommerce.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.AccountRole;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.AccountRolePk;

import org.springframework.stereotype.Repository;

@Repository
public interface AccountRoleRepository extends JpaRepository<AccountRole, AccountRolePk> {
    // Define repository methods here
}


package pro.ddsr.backend_dashboard_ecommerce.role.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pro.ddsr.backend_dashboard_ecommerce.role.persistence.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    // Define repository methods here
}

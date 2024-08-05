
package pro.ddsr.backend_dashboard_ecommerce.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.ddsr.backend_dashboard_ecommerce.persistence.entity.Permission;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    // Define repository methods here
}

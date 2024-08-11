
package pro.ddsr.backend_dashboard_ecommerce.permission.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pro.ddsr.backend_dashboard_ecommerce.permission.persistence.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    // Define repository methods here
}
